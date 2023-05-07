package data.modeling.adt.typedefs;

import data.modeling.adt.abstraction.annotations.Annotation;
import data.modeling.adt.abstraction.visitors.AdtVisitor;
import data.modeling.adt.util.FieldTypeBuilder;

import java.util.HashSet;
import java.util.Set;

public class FieldType implements LabeledType {
    private final String name;
    private AnyType type;
    private int index;
    private Boolean isRequired=false;
    private final Set<Annotation<?>> annotations;

    public FieldType(String name, AnyType type) {
        this(name, type, new HashSet<>());
    }
    public FieldType(String name, AnyType type, Set<Annotation<?>> annotations) {
        this.name = name;
        this.type = type;
        this.index = index;
        this.annotations = annotations;
    }

    public static FieldTypeBuilder builder(String name, AnyType type){
        return new FieldTypeBuilder(name, type);
    }

    public String getName() {
        return name;
    }

    public Set<Annotation<?>> getAnnotations() {
        return annotations;
    }


    public AnyType getType(){
        return type;
    }

    public static FieldType of(String name, AnyType type){
        return new FieldType(name, type, new HashSet<>());
    }

    @Override
    public int hashCode() {
        return this.index;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FieldType fieldType = (FieldType) o;
        return index == fieldType.index && name.equals(fieldType.name)
                && type.equals(fieldType.type)
                && annotations.equals(fieldType.annotations)
                && isRequired.equals(fieldType.isRequired);
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Boolean isRequired() {
        return isRequired;
    }

    public void setRequired(boolean required) {
        isRequired = required;
    }

    @Override
    public void accept(AdtVisitor visitor) {
        visitor.enterNamedType(this);
        LabeledType.super.accept(visitor);
        visitor.exitNamedType(this);
    }
}
