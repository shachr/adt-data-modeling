package adt.hiveddlFromAdt.mappers;

import data.modeling.adt.typedefs.StringCharType;
import org.apache.spark.sql.types.CharType;

public class CharTypeMapper extends DataTypeMapper<StringCharType, CharType> {

    @Override
    public boolean canMap(StringCharType value) {
        return true;
    }

    @Override
    public CharType fromAdt(StringCharType value) {
        return new CharType(value.getLength());
    }
}
