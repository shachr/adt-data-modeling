package data.modeling.adt.typedefs;

import data.modeling.adt.abstraction.visitors.AdtVisitor;

public class IntType extends NumericType {
    public static ConstantPrimitiveType constantOf(int value){
        return new ConstantPrimitiveType(new IntType(), value);
    }

    @Override
    public boolean isValueOf(Object value) {
        return value instanceof Integer;
    }
}
