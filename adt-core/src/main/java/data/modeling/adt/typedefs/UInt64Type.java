package data.modeling.adt.typedefs;

public class UInt64Type extends NumericType {

    public static ConstantPrimitiveType constantOf(long value){
        return new ConstantPrimitiveType(new UInt64Type(), value);
    }

    @Override
    public boolean isValueOf(Object value) {
        return value instanceof Long;
    }
}
