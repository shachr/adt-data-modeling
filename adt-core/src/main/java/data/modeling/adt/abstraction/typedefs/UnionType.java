package data.modeling.adt.abstraction.typedefs;

import java.util.Collection;
import java.util.List;

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
}

