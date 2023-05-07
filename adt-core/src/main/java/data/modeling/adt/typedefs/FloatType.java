package data.modeling.adt.typedefs;

import data.modeling.adt.abstraction.visitors.AdtVisitor;

public class FloatType extends NumericType {
    public static ConstantPrimitiveType constantOf(float value){
        return new ConstantPrimitiveType(new FloatType(), value);
    }
    @Override
    public boolean isValueOf(Object value) {
        return value instanceof Float;
    }
}
