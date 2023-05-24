package adt.hiveddlFromAdt.mappers;

import org.apache.spark.sql.types.StringType;

public class StringTypeMapper extends DataTypeMapper<data.modeling.adt.typedefs.StringType, StringType> {

    @Override
    public boolean canMap(data.modeling.adt.typedefs.StringType value) {
        return true;
    }

    @Override
    public StringType fromAdt(data.modeling.adt.typedefs.StringType value) {
        return new StringType();
    }
}
