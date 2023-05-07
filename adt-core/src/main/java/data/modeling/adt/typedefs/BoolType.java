package data.modeling.adt.typedefs;

import data.modeling.adt.abstraction.visitors.AdtVisitor;

public class BoolType extends PrimitiveType {

    @Override
    public boolean isValueOf(Object value) {
        return value instanceof Boolean;
    }
}
