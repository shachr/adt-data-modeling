package data.modeling.adt.typedefs;

import data.modeling.adt.SchemaContext;
import data.modeling.adt.abstraction.visitors.AdtVisitor;
import data.modeling.adt.exceptions.AdtException;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UnionType implements SumType {
    private Collection<? extends AnyType> types;

    // todo: ideally only ScalarType, ReferencedNamedType and NullValueType should be acceptable
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

    public static UnionType of(Stream<? extends AnyType> types) {
        return new UnionType(types.collect(Collectors.toCollection(LinkedHashSet::new)));
    }
}

