package adt.hiveddlToAdt.mappers;

import org.apache.spark.sql.types.LongType;

public class LongTypeMapper extends DataTypeMapper<LongType, data.modeling.adt.typedefs.SInt64Type> {

    @Override
    public boolean canMap(LongType value) {
        return true;
    }

    @Override
    public data.modeling.adt.typedefs.SInt64Type toAdt(LongType value) {
        return new data.modeling.adt.typedefs.SInt64Type();
    }
}
