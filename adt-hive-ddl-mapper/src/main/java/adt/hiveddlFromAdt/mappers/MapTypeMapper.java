package adt.hiveddlFromAdt.mappers;

import data.modeling.adt.exceptions.AdtException;
import data.modeling.adt.mappers.registries.FromAdtMapperRegistry;
import data.modeling.adt.typedefs.AnyType;
import data.modeling.adt.typedefs.NullValueType;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.MapType;

public class MapTypeMapper extends DataTypeMapper<data.modeling.adt.typedefs.MapType, MapType> {

    private FromAdtMapperRegistry fromAdtMapperRegistry;

    public MapTypeMapper(FromAdtMapperRegistry fromAdtMapperRegistry){

        this.fromAdtMapperRegistry = fromAdtMapperRegistry;
    }

    @Override
    public boolean canMap(data.modeling.adt.typedefs.MapType value) {
        return true;
    }

    @Override
    public MapType fromAdt(data.modeling.adt.typedefs.MapType value) throws AdtException {
        AnyType itemType = value.getItemType();
        boolean containsNull = value.getItemType() instanceof NullValueType;
        if(containsNull){
            NullValueType nullValueType = (NullValueType) value.getItemType();
            itemType = nullValueType.getItemType();
        }

        return new MapType(
                fromAdtMapperRegistry.fromAdt(value.getKeyType()),
                fromAdtMapperRegistry.fromAdt(itemType),
                containsNull);
    }
}
