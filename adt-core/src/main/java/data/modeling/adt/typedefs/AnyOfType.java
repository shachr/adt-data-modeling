package data.modeling.adt.typedefs;

import data.modeling.adt.SchemaContext;
import data.modeling.adt.abstraction.visitors.AdtVisitor;
import data.modeling.adt.exceptions.AdtException;

import java.util.Collection;
import java.util.Objects;

public class AnyOfType implements CompositionType {
    private Collection<? extends AnyType> types;

    public AnyOfType(Collection<? extends AnyType> types) {
        this.types = types;
    }

    public Collection<? extends AnyType> getTypes() {
        return types;
    }

    @Override
    public int size() {
        return getTypes().size();
    }

    // todo: consider adding a reference to a discriminator field,
    //  or add an annotation on the named type.
    public void accept(AdtVisitor visitor) throws AdtException {
        visitor.visit(this);
    }

    @Override
    public AnyType resolveSubSchemes(SchemaContext schemaContext) throws AdtException {
        throw new AdtException("not implemented");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnyOfType anyOfType = (AnyOfType) o;
        return types.equals(anyOfType.types);
    }

    @Override
    public int hashCode() {
        return Objects.hash(types);
    }
}
