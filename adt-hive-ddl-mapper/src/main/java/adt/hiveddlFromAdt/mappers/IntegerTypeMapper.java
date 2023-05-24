package adt.hiveddlFromAdt.mappers;

import data.modeling.adt.typedefs.Int32Type;
import data.modeling.adt.typedefs.NumericType;
import data.modeling.adt.typedefs.SInt32Type;
import data.modeling.adt.typedefs.UInt32Type;
import org.apache.spark.sql.types.IntegerType;

public class IntegerTypeMapper extends DataTypeMapper<NumericType, IntegerType> {

    @Override
    public boolean canMap(NumericType value) {
        return value instanceof SInt32Type || value instanceof Int32Type || value instanceof UInt32Type;
    }

    @Override
    public IntegerType fromAdt(NumericType value) {
        return new IntegerType();
    }
}
