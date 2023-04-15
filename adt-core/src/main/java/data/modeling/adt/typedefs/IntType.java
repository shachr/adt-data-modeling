package data.modeling.adt.typedefs;

public class IntType extends NumericType {

    public static ConstantPrimitiveType constantOf(int value){
        return new ConstantPrimitiveType(new IntType(), value);
    }
}
