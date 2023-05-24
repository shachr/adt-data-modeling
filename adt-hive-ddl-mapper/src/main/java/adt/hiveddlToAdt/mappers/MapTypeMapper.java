package adt.hiveddlToAdt.mappers;

import data.modeling.adt.exceptions.AdtException;
import data.modeling.adt.mappers.registries.ToAdtMapperRegistry;
import data.modeling.adt.typedefs.AnyType;
import data.modeling.adt.typedefs.NullValueType;
import org.apache.spark.sql.types.MapType;

public class MapTypeMapper extends DataTypeMapper<MapType, data.modeling.adt.typedefs.MapType> {

    private ToAdtMapperRegistry toAdtMapperRegistry;

    public MapTypeMapper(ToAdtMapperRegistry toAdtMapperRegistry){

        this.toAdtMapperRegistry = toAdtMapperRegistry;
    }

    @Override
    public boolean canMap(MapType value) {
        return true;
    }

    @Override
    public data.modeling.adt.typedefs.MapType toAdt(MapType value) throws AdtException {
        AnyType valueType = toAdtMapperRegistry.toAdt(value.valueType());
        if(value.valueContainsNull()){
            valueType = new NullValueType(valueType);
        }
        return new data.modeling.adt.typedefs.MapType(
                toAdtMapperRegistry.toAdt(value.keyType()),
                valueType
                );
    }
}
