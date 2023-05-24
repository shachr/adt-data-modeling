package adt.hiveddlToAdt.mappers;

import org.apache.spark.sql.types.IntegerType;

public class IntegerTypeMapper extends DataTypeMapper<IntegerType, data.modeling.adt.typedefs.SInt32Type> {

    @Override
    public boolean canMap(IntegerType value) {
        return true;
    }

    @Override
    public data.modeling.adt.typedefs.SInt32Type toAdt(IntegerType value) {
        return new data.modeling.adt.typedefs.SInt32Type();
    }
}
