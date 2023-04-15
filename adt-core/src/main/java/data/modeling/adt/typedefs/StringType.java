package data.modeling.adt.typedefs;

public class StringType extends PrimitiveType {
    public static ConstantPrimitiveType constantOf(String value){
        return new ConstantPrimitiveType(new StringType(), value);
    }
}
