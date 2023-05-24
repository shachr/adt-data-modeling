package data.modeling.adt.typedefs;

public class StringType extends StringBaseType {

    public static ConstantPrimitiveType constantOf(String value){
        return new ConstantPrimitiveType(new StringType(), value);
    }

    public StringType(){

    }

    @Override
    public boolean isValueOf(Object value) {
        return value instanceof String;
    }
}
