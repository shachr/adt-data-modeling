package data.modeling.adt.addons.hivemetastore;

import org.apache.hadoop.hive.metastore.api.FieldSchema;
import org.apache.hadoop.hive.metastore.api.Table;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfo;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfoUtils;

import java.util.List;
import java.util.Map;

public class SchemaValidationAddon {

    public static void validateTable(Table existingTable, Table proposedTable) throws ValidationException {
        List<FieldSchema> existingColumns = existingTable.getSd().getCols();
        List<FieldSchema> proposedColumns = proposedTable.getSd().getCols();

        // Check that the proposed table contains all the columns in the existing table.
        for (FieldSchema existingColumn : existingColumns) {
            String existingColumnName = existingColumn.getName();
            FieldSchema proposedColumn = getFieldSchema(proposedColumns, existingColumnName);
            if (proposedColumn == null) {
                throw new ValidationException("Column " + existingColumnName + " is missing from the proposed table schema.");
            }
            try {
                TypeInfo existingTypeInfo = TypeInfoUtils.getTypeInfoFromTypeString(existingColumn.getType());
                TypeInfo proposedTypeInfo = TypeInfoUtils.getTypeInfoFromTypeString(proposedColumn.getType());
                if (!existingTypeInfo.equals(proposedTypeInfo)) {
                    throw new ValidationException("Column " + existingColumnName + " has a different type in the proposed table schema.");
                }
            } catch (Exception e) {
                throw new ValidationException("Error parsing column type for " + existingColumnName, e);
            }
            // Check that the attributes of the existing column are present in the proposed column.
            Map<String, String> existingAttributes = existingTable.getParameters();
            Map<String, String> proposedAttributes = proposedTable.getParameters();
            for (String attributeName : existingAttributes.keySet()) {
                String attributeValue = existingAttributes.get(attributeName);
                if (!attributeValue.equals(proposedAttributes.get(attributeName))) {
                    throw new ValidationException("Attribute " + attributeName + " has a different value in the proposed table schema for column " + existingColumnName);
                } else if (!attributeValue.equals(proposedAttributes.get(attributeName))) {
                    throw new ValidationException("Attribute " + attributeName + " has a different value in the proposed table schema for column " + existingColumnName);
                }
            }
        }

    }

    private static FieldSchema getFieldSchema(List<FieldSchema> columns, String columnName) {
        for (FieldSchema column : columns) {
            if (column.getName().equals(columnName)) {
                return column;
            }
        }
        return null;
    }

    public static class ValidationException extends Exception {
        public ValidationException(String message) {
            super(message);
        }

        public ValidationException(String message, Throwable cause) {
            super(message, cause);
        }
    }

}
