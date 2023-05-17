package data.modeling.adt.typedefs;

import data.modeling.adt.SchemaContext;
import data.modeling.adt.abstraction.visitors.AdtVisitor;
import data.modeling.adt.exceptions.AdtException;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AllOfType implements CompositionType {
    private Collection<? extends AnyType> types;

    public AllOfType(Stream<? extends AnyType> types) {
        this.types = types.collect(Collectors.toCollection(LinkedHashSet::new));
    }
    public AllOfType(Collection<? extends AnyType> types) {
        this.types = types;
    }

    public static AllOfType of(AnyType... types) {
        return new AllOfType(Arrays.stream(types));
    }

    public Collection<? extends AnyType> getTypes() {
        return types;
    }

    public void setTypes(Collection<? extends AnyType> types) {
        this.types = types;
    }

    public void setTypes(Stream<? extends AnyType> types) {
        this.types = types.collect(Collectors.toCollection(LinkedHashSet::new));
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AllOfType all = (AllOfType) o;
        return types.equals(all.types);
    }

    @Override
    public int hashCode() {
        return Objects.hash(types);
    }
}
