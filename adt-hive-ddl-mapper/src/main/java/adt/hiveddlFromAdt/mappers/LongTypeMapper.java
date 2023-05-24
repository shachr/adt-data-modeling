package adt.hiveddlFromAdt.mappers;

import data.modeling.adt.typedefs.Int64Type;
import data.modeling.adt.typedefs.NumericType;
import data.modeling.adt.typedefs.SInt64Type;
import data.modeling.adt.typedefs.UInt64Type;
import org.apache.spark.sql.types.LongType;

public class LongTypeMapper extends DataTypeMapper<NumericType, LongType> {

    @Override
    public boolean canMap(NumericType value) {
        return value instanceof SInt64Type || value instanceof UInt64Type || value instanceof Int64Type;
    }

    @Override
    public LongType fromAdt(NumericType value) {
        return new LongType();
    }
}
