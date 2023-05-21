package data.modeling.adt.typedefs;

public class SInt32Type extends NumericType {
    public static ConstantPrimitiveType constantOf(int value){
        return new ConstantPrimitiveType(new SInt32Type(), value);
    }

    @Override
    public boolean isValueOf(Object value) {
        return value instanceof Integer;
    }
}
