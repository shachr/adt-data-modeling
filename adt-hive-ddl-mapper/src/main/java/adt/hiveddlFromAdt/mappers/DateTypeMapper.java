package adt.hiveddlFromAdt.mappers;


import org.apache.spark.sql.types.DateType;

public class DateTypeMapper extends DataTypeMapper<data.modeling.adt.typedefs.DateType, DateType> {

    @Override
    public boolean canMap(data.modeling.adt.typedefs.DateType value) {
        return true;
    }

    @Override
    public DateType fromAdt(data.modeling.adt.typedefs.DateType value) {
        return new DateType();
    }
}
