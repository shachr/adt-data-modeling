package adt.hiveddlToAdt.mappers;

import org.apache.spark.sql.types.StringType;

public class StringTypeMapper extends DataTypeMapper<StringType, data.modeling.adt.typedefs.StringType> {

    @Override
    public boolean canMap(StringType value) {
        return true;
    }

    @Override
    public data.modeling.adt.typedefs.StringType toAdt(StringType value) {
        return new data.modeling.adt.typedefs.StringType();
    }
}
