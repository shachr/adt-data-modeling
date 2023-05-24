package adt.hiveddlToAdt.mappers;

import org.apache.spark.sql.types.TimestampType;

public class TimestampTypeMapper extends DataTypeMapper<TimestampType, data.modeling.adt.typedefs.DateTimeType> {

    @Override
    public boolean canMap(TimestampType value) {
        return true;
    }

    @Override
    public data.modeling.adt.typedefs.DateTimeType toAdt(TimestampType value) {
        return new data.modeling.adt.typedefs.DateTimeType();
    }
}
