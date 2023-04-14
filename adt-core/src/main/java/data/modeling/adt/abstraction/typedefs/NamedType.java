package data.modeling.adt.abstraction.typedefs;

import data.modeling.adt.abstraction.annotations.Annotation;
import data.modeling.adt.util.NamedTypeBuilder;

import java.util.HashSet;
import java.util.Set;

public class NamedType implements AnyType {
    private final String name;
    private AnyType type;
    private final Set<Annotation> annotations;

    public NamedType(String name, AnyType type) {
        this(name, type, new HashSet<>());
    }

    public NamedType(String name, AnyType type, Set<Annotation> annotations) {
        this.name = name;
        this.type = type;
        this.annotations = annotations;
    }

    public static NamedTypeBuilder builder(String name, AnyType type){
        return new NamedTypeBuilder(name, type);
    }

    public String getName() {
        return name;
    }

    public Set<Annotation> getAnnotations() {
        return annotations;
    }


    public AnyType getType(){
        return type;
    }
//    public abstract <T> T accept(NamedTypeVisitor<T> visitor);

    public static NamedType of(String name, AnyType type){
        return new NamedType(name, type, new HashSet<>());
    }

    @Override
    public int hashCode() {
        return this.name.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NamedType namedType = (NamedType) o;
        return name.equals(namedType.name) && type.equals(namedType.type) && annotations.equals(namedType.annotations);
    }
}
