package adt.hiveddlFromAdt.mappers;

import data.modeling.adt.exceptions.AdtException;
import data.modeling.adt.mappers.registries.FromAdtMapperRegistry;
import data.modeling.adt.typedefs.AnyType;
import data.modeling.adt.typedefs.FieldDefinition;
import data.modeling.adt.typedefs.NullValueType;
import org.apache.spark.sql.types.Metadata;
import org.apache.spark.sql.types.StructField;

public class StructFieldMapper extends DataTypeMapper<FieldDefinition, StructField> {

    private FromAdtMapperRegistry fromAdtMapperRegistry;

    public StructFieldMapper(FromAdtMapperRegistry fromAdtMapperRegistry){

        this.fromAdtMapperRegistry = fromAdtMapperRegistry;
    }

    @Override
    public boolean canMap(FieldDefinition value) {
        return true;
    }

    @Override
    public StructField fromAdt(FieldDefinition value) throws AdtException {
        // todo: default value
        AnyType itemType = value.getType();
        boolean nullable = itemType instanceof NullValueType;
        if(nullable){
            itemType = ((NullValueType)itemType).getItemType();
        }
        return new StructField(value.getName(), fromAdtMapperRegistry.fromAdt(itemType), nullable, Metadata.empty());
    }
}
