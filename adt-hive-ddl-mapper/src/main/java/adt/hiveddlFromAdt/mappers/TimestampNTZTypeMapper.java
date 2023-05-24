package adt.hiveddlFromAdt.mappers;

import data.modeling.adt.typedefs.DateTimeLocalType;
import org.apache.spark.sql.types.TimestampNTZType;

public class TimestampNTZTypeMapper extends DataTypeMapper<DateTimeLocalType, TimestampNTZType> {

    @Override
    public boolean canMap(DateTimeLocalType value) {
        return true;
    }

    @Override
    public TimestampNTZType fromAdt(DateTimeLocalType value) {
        return new TimestampNTZType();
    }
}
