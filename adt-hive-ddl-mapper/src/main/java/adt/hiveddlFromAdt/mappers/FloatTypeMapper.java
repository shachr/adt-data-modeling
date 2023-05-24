package adt.hiveddlFromAdt.mappers;

import org.apache.spark.sql.types.FloatType;

public class FloatTypeMapper extends DataTypeMapper<data.modeling.adt.typedefs.FloatType, FloatType> {

    @Override
    public boolean canMap(data.modeling.adt.typedefs.FloatType value) {
        return true;
    }

    @Override
    public FloatType fromAdt(data.modeling.adt.typedefs.FloatType value) {
        return new FloatType();
    }
}
