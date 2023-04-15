package data.modeling.adt.typedefs;

public class LongType extends NumericType {

    public static ConstantPrimitiveType constantOf(long value){
        return new ConstantPrimitiveType(new LongType(), value);
    }
}
