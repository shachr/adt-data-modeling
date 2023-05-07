package data.modeling.adt.typedefs;

import data.modeling.adt.abstraction.visitors.AdtVisitor;

import java.util.Objects;

public class ReferenceObjectType implements AnyType {
    private String refName;

    public ReferenceObjectType(String refName) {
        this.refName = refName;
    }

    public String getReferenceName() {
        return refName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReferenceObjectType that = (ReferenceObjectType) o;
        return refName.equals(that.refName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(refName);
    }

    public void accept(AdtVisitor visitor) {
        visitor.visit(this);
    }
}
