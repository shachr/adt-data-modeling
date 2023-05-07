package data.modeling.adt.typedefs;

import data.modeling.adt.abstraction.visitors.AdtVisitor;

public abstract class PrimitiveType implements AnyType {
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        return true;
    }
    public void accept(AdtVisitor visitor) {
        visitor.visit(this);
    }

    public abstract boolean isValueOf(Object value);
}
