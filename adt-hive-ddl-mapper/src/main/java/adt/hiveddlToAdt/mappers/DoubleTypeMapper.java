package adt.hiveddlToAdt.mappers;

import org.apache.spark.sql.types.DoubleType;

public class DoubleTypeMapper extends DataTypeMapper<DoubleType, data.modeling.adt.typedefs.DoubleType> {

    @Override
    public boolean canMap(DoubleType value) {
        return true;
    }

    @Override
    public data.modeling.adt.typedefs.DoubleType toAdt(DoubleType value) {
        return new data.modeling.adt.typedefs.DoubleType();
    }
}
