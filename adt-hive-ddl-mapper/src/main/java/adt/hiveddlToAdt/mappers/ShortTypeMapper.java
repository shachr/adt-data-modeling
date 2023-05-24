package adt.hiveddlToAdt.mappers;

import org.apache.spark.sql.types.ShortType;

public class ShortTypeMapper extends DataTypeMapper<ShortType, data.modeling.adt.typedefs.ShortType> {

    @Override
    public boolean canMap(ShortType value) {
        return true;
    }

    @Override
    public data.modeling.adt.typedefs.ShortType toAdt(ShortType value) {
        return new data.modeling.adt.typedefs.ShortType();
    }
}
