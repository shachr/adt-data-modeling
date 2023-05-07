package data.modeling.adt.typedefs;

public class LongType extends NumericType {

    public static ConstantPrimitiveType constantOf(long value){
        return new ConstantPrimitiveType(new LongType(), value);
    }

    @Override
    public boolean isValueOf(Object value) {
        return value instanceof Long;
    }
}
