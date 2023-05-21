package data.modeling.adt.typedefs;

public class StringType extends ScalarType {
    public static ConstantPrimitiveType constantOf(String value){
        return new ConstantPrimitiveType(new StringType(), value);
    }

    @Override
    public boolean isValueOf(Object value) {
        return value instanceof String;
    }
}
