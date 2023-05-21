package data.modeling.adt.typedefs;

public class Int64Type extends NumericType {

    public static ConstantPrimitiveType constantOf(long value){
        return new ConstantPrimitiveType(new Int64Type(), value);
    }

    @Override
    public boolean isValueOf(Object value) {
        return value instanceof Long;
    }
}
