package data.modeling.adt.typedefs;

import data.modeling.adt.abstraction.annotations.Annotation;
import data.modeling.adt.abstraction.visitors.AdtVisitor;
import data.modeling.adt.exceptions.AdtException;
import data.modeling.adt.util.NamedTypeBuilder;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class NamedType implements LabeledType<ComplexType> {
    private String name;
    private ComplexType type;
    private final Set<Annotation<?>> annotations;

    public NamedType(String name, ComplexType type) {
        this(name, type, new LinkedHashSet<>());
    }

    public NamedType(String name, ComplexType type, Set<Annotation<?>> annotations) {
        this.name = name;
        this.type = type;
        this.annotations = annotations;
    }

    public static NamedTypeBuilder builder(String name, ComplexType type){
        return new NamedTypeBuilder(name, type);
    }

    public String getName() {
        return name;
    }

    public void setName(String value) {
        name = value;
    }

    public Set<Annotation<?>> getAnnotations() {
        return annotations;
    }


    public ComplexType getType(){
        return type;
    }

    @Override
    public void setType(ComplexType anyType) {
        type = anyType;
    }

    public static NamedType of(String name, ComplexType type){
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

    @Override
    public void accept(AdtVisitor visitor) throws AdtException {
        visitor.enterLabeledType(this);
        LabeledType.super.accept(visitor);
        visitor.exitLabeledType(this);
    }
}
