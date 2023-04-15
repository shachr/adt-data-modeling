package data.modeling.adt.typedefs;

public class DoubleType extends NumericType {
    public static ConstantPrimitiveType constantOf(double value){
        return new ConstantPrimitiveType(new DoubleType(), value);
    }
}
