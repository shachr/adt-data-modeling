package adt.hiveddlToAdt.mappers;

import org.apache.spark.sql.types.FloatType;

public class FloatTypeMapper extends DataTypeMapper<FloatType, data.modeling.adt.typedefs.FloatType> {

    @Override
    public boolean canMap(FloatType value) {
        return true;
    }

    @Override
    public data.modeling.adt.typedefs.FloatType toAdt(FloatType value) {
        return new data.modeling.adt.typedefs.FloatType();
    }
}
