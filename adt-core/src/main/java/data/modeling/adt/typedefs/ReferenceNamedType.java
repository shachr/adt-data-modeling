package data.modeling.adt.typedefs;

import data.modeling.adt.abstraction.visitors.AdtVisitor;
import data.modeling.adt.exceptions.AdtException;

import java.util.Objects;

public class ReferenceNamedType implements AnyType {
    private String refName;

    public ReferenceNamedType(String refName) {
        this.refName = refName;
    }

    public String getReferenceName() {
        return refName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReferenceNamedType that = (ReferenceNamedType) o;
        return refName.equals(that.refName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(refName);
    }

    public void accept(AdtVisitor visitor) throws AdtException {
        visitor.visit(this);
    }
}
