package data.modeling.adt.typedefs;

import data.modeling.adt.abstraction.visitors.AdtVisitor;
import data.modeling.adt.exceptions.AdtException;

public abstract class ScalarType implements AnyType {
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        return true;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public void accept(AdtVisitor visitor) throws AdtException {
        visitor.visit(this);
    }

    public abstract boolean isValueOf(Object value);
}
