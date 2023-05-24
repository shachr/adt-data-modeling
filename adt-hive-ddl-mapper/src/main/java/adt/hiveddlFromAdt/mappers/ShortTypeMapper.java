package adt.hiveddlFromAdt.mappers;

import org.apache.spark.sql.types.ShortType;

public class ShortTypeMapper extends DataTypeMapper<data.modeling.adt.typedefs.ShortType, ShortType> {

    @Override
    public boolean canMap(data.modeling.adt.typedefs.ShortType value) {
        return true;
    }

    @Override
    public ShortType fromAdt(data.modeling.adt.typedefs.ShortType value) {
        return new ShortType();
    }
}
