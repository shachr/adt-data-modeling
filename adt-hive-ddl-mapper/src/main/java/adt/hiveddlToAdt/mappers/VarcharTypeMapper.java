package adt.hiveddlToAdt.mappers;

import org.apache.spark.sql.types.VarcharType;

public class VarcharTypeMapper extends DataTypeMapper<VarcharType, data.modeling.adt.typedefs.StringVarcharType> {

    @Override
    public boolean canMap(VarcharType value) {
        return true;
    }

    @Override
    public data.modeling.adt.typedefs.StringVarcharType toAdt(VarcharType value) {
        return new data.modeling.adt.typedefs.StringVarcharType(value.length());
    }
}
