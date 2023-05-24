package adt.hiveddlFromAdt.mappers;

import data.modeling.adt.typedefs.DateTimeType;
import org.apache.spark.sql.types.TimestampType;

public class TimestampTypeMapper extends DataTypeMapper<DateTimeType, TimestampType> {

    @Override
    public boolean canMap(DateTimeType value) {
        return true;
    }

    @Override
    public TimestampType fromAdt(DateTimeType value) {
        return new TimestampType();
    }
}
