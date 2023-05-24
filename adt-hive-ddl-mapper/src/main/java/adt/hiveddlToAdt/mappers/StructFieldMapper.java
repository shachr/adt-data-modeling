package adt.hiveddlToAdt.mappers;

import data.modeling.adt.exceptions.AdtException;
import data.modeling.adt.mappers.registries.ToAdtMapperRegistry;
import data.modeling.adt.typedefs.AnyType;
import data.modeling.adt.typedefs.NullValueType;
import org.apache.spark.sql.types.StructField;

public class StructFieldMapper extends DataTypeMapper<StructField, data.modeling.adt.typedefs.FieldType> {

    private ToAdtMapperRegistry toAdtMapperRegistry;

    public StructFieldMapper(ToAdtMapperRegistry toAdtMapperRegistry){

        this.toAdtMapperRegistry = toAdtMapperRegistry;
    }

    @Override
    public boolean canMap(StructField value) {
        return true;
    }

    @Override
    public data.modeling.adt.typedefs.FieldType toAdt(StructField value) throws AdtException {
        AnyType fieldType = toAdtMapperRegistry.toAdt(value.dataType());
        if(value.nullable()) {
            fieldType = new NullValueType(fieldType);
        }
        // todo: default value
        return new data.modeling.adt.typedefs.FieldType(value.name(), fieldType);
    }
}
