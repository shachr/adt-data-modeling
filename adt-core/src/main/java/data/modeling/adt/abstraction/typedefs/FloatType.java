package data.modeling.adt.abstraction.typedefs;

public class FloatType extends NumericType {
    public static ConstantPrimitiveType constantOf(float value){
        return new ConstantPrimitiveType(new FloatType(), value);
    }
}
