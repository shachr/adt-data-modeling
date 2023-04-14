package data.modeling.adt.abstraction.typedefs;

public class IntType extends NumericType {

    public static ConstantPrimitiveType constantOf(int value){
        return new ConstantPrimitiveType(new IntType(), value);
    }
}
