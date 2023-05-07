package data.modeling.adt.typedefs;

import data.modeling.adt.abstraction.visitors.AdtVisitor;

import java.util.Collection;

public class UnionType implements SumType {
    private Collection<AnyType> types;

    public UnionType(Collection<AnyType> types) {
        this.types = types;
    }

    public Collection<AnyType> getTypes() {
        return types;
    }

    @Override
    public int size() {
        return getTypes().size();
    }

    // todo: consider adding a reference to a discriminator field,
    //  or add an annotation on the named type.
    public void accept(AdtVisitor visitor) {
        visitor.visit(this);
    }
}

