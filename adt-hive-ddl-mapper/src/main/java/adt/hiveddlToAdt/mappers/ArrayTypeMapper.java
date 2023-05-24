package adt.hiveddlToAdt.mappers;

import data.modeling.adt.exceptions.AdtException;
import data.modeling.adt.mappers.registries.ToAdtMapperRegistry;
import data.modeling.adt.typedefs.AnyType;
import data.modeling.adt.typedefs.NullValueType;
import org.apache.spark.sql.types.ArrayType;

public class ArrayTypeMapper extends DataTypeMapper<ArrayType, data.modeling.adt.typedefs.ListType> {

    private ToAdtMapperRegistry toAdtMapperRegistry;

    public ArrayTypeMapper(ToAdtMapperRegistry toAdtMapperRegistry){

        this.toAdtMapperRegistry = toAdtMapperRegistry;
    }

    @Override
    public boolean canMap(ArrayType value) {
        return true;
    }

    @Override
    public data.modeling.adt.typedefs.ListType toAdt(ArrayType value) throws AdtException {
        AnyType itemType = toAdtMapperRegistry.toAdt(value.elementType());
        if(value.containsNull())
            itemType = new NullValueType(itemType);
        return new data.modeling.adt.typedefs.ListType(itemType);
    }
}
