package adt.hiveddlToAdt.mappers;

import data.modeling.adt.exceptions.AdtException;
import data.modeling.adt.mappers.registries.ToAdtMapperRegistry;
import data.modeling.adt.typedefs.AnyType;
import data.modeling.adt.typedefs.FieldDefinition;
import data.modeling.adt.typedefs.NullValueType;
import org.apache.spark.sql.types.StructField;

public class StructFieldMapper extends DataTypeMapper<StructField, FieldDefinition> {

    private ToAdtMapperRegistry toAdtMapperRegistry;

    public StructFieldMapper(ToAdtMapperRegistry toAdtMapperRegistry){

        this.toAdtMapperRegistry = toAdtMapperRegistry;
    }

    @Override
    public boolean canMap(StructField value) {
        return true;
    }

    @Override
    public FieldDefinition toAdt(StructField value) throws AdtException {
        AnyType fieldType = toAdtMapperRegistry.toAdt(value.dataType());
        if(value.nullable()) {
            fieldType = new NullValueType(fieldType);
        }
        // todo: default value
        return new FieldDefinition(value.name(), fieldType);
    }
}
