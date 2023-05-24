package adt.hiveddlToAdt.mappers;

import org.apache.spark.sql.types.CharType;

public class CharTypeMapper extends DataTypeMapper<CharType, data.modeling.adt.typedefs.StringCharType> {

    @Override
    public boolean canMap(CharType value) {
        return true;
    }

    @Override
    public data.modeling.adt.typedefs.StringCharType toAdt(CharType value) {
        return new data.modeling.adt.typedefs.StringCharType(value.length());
    }
}
