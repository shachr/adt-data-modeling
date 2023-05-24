package adt.hiveddlFromAdt.mappers;

import data.modeling.adt.typedefs.StringVarcharType;
import org.apache.spark.sql.types.VarcharType;

public class VarcharTypeMapper extends DataTypeMapper<StringVarcharType, VarcharType> {

    @Override
    public boolean canMap(StringVarcharType value) {
        return true;
    }

    @Override
    public VarcharType fromAdt(StringVarcharType value) {
        return new VarcharType(value.getLength());
    }
}
