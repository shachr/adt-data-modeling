package data.modeling.adt.typedefs;

import data.modeling.adt.abstraction.visitors.AdtVisitor;
import data.modeling.adt.exceptions.AdtException;
import data.modeling.adt.util.AdtVisitorUtil;

import java.util.Objects;

public class NullValueType implements TypeModifier {
    private final AnyType elementType;

    public NullValueType(AnyType elementType) {
        this.elementType = elementType;
    }

    public AnyType getItemType() {
        return elementType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NullValueType that = (NullValueType) o;
        return Objects.equals(elementType, that.elementType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(elementType);
    }

    public void accept(AdtVisitor visitor) throws AdtException {
        AdtVisitorUtil.visit(visitor, getItemType());
        visitor.visit(this);
    }
}

