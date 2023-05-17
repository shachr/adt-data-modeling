package data.modeling.adt.typedefs;

import data.modeling.adt.abstraction.annotations.Annotation;
import data.modeling.adt.abstraction.visitors.AdtVisitor;
import data.modeling.adt.exceptions.AdtException;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

public class ReferenceNamedType implements AnyType {
    private String refName;
    private final Set<Annotation<?>> annotations = new LinkedHashSet<>();

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

    public Set<Annotation<?>> getAnnotations() {
        return annotations;
    }

    @Override
    public int hashCode() {
        return Objects.hash(refName);
    }

    public void accept(AdtVisitor visitor) throws AdtException {
        visitor.visit(this);
    }
}
