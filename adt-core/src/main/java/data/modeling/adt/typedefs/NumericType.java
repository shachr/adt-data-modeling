package data.modeling.adt.typedefs;

import data.modeling.adt.abstraction.visitors.AdtVisitor;

public abstract class NumericType extends PrimitiveType{
    public void accept(AdtVisitor visitor) {
        visitor.visit(this);
    }
}
