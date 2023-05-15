package data.modeling.adt.typedefs;

import data.modeling.adt.SchemaContext;
import data.modeling.adt.abstraction.visitors.AdtVisitor;
import data.modeling.adt.exceptions.AdtException;

import java.util.Collection;

public class UnionType implements SumType {
    private Collection<? extends AnyType> types;

    public UnionType(Collection<? extends AnyType> types) {
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
        // todo: resolve all product types within, recursively
        throw new AdtException("not implemented");
    }
}

