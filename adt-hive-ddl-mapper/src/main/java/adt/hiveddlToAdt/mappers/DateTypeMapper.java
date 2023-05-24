package adt.hiveddlToAdt.mappers;

import org.apache.spark.sql.types.DateType;

public class DateTypeMapper extends DataTypeMapper<DateType, data.modeling.adt.typedefs.DateType> {

    @Override
    public boolean canMap(DateType value) {
        return true;
    }

    @Override
    public data.modeling.adt.typedefs.DateType toAdt(DateType value) {
        return new data.modeling.adt.typedefs.DateType();
    }
}
