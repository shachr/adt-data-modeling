package adt.hiveddlToAdt.mappers;

import org.apache.spark.sql.types.TimestampNTZType;

public class TimestampNTZTypeMapper extends DataTypeMapper<TimestampNTZType, data.modeling.adt.typedefs.DateTimeLocalType> {

    @Override
    public boolean canMap(TimestampNTZType value) {
        return true;
    }

    @Override
    public data.modeling.adt.typedefs.DateTimeLocalType toAdt(TimestampNTZType value) {
        return new data.modeling.adt.typedefs.DateTimeLocalType();
    }
}
