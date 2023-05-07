package data.modeling.adt.typedefs;

import data.modeling.adt.abstraction.visitors.AdtVisitor;

public class DoubleType extends NumericType {
    public static ConstantPrimitiveType constantOf(double value){
        return new ConstantPrimitiveType(new DoubleType(), value);
    }

    @Override
    public boolean isValueOf(Object value) {
        return value instanceof Double;
    }
}
