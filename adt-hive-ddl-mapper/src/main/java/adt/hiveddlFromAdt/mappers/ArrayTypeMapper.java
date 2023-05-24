package adt.hiveddlFromAdt.mappers;

import data.modeling.adt.exceptions.AdtException;
import data.modeling.adt.mappers.registries.FromAdtMapperRegistry;
import data.modeling.adt.typedefs.AnyType;
import data.modeling.adt.typedefs.ListType;
import data.modeling.adt.typedefs.NullValueType;
import org.apache.spark.sql.types.ArrayType;
import org.apache.spark.sql.types.DataType;

public class ArrayTypeMapper extends DataTypeMapper<ListType, ArrayType> {

    private FromAdtMapperRegistry fromAdtMapperRegistry;

    public ArrayTypeMapper(FromAdtMapperRegistry fromAdtMapperRegistry){

        this.fromAdtMapperRegistry = fromAdtMapperRegistry;
    }

    @Override
    public boolean canMap(ListType value) {
        return true;
    }

    @Override
    public ArrayType fromAdt(ListType value) throws AdtException {
        AnyType itemType = value.getItemType();
        boolean containsNull =  itemType instanceof NullValueType;
        if(containsNull) itemType = ((NullValueType)itemType).getItemType();

        DataType sparkItemType = fromAdtMapperRegistry.fromAdt(itemType);
        return new ArrayType(sparkItemType, containsNull);
    }
}
