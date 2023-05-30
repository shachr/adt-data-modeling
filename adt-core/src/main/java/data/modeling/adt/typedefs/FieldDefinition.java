package data.modeling.adt.typedefs;

import data.modeling.adt.abstraction.annotations.Annotation;
import data.modeling.adt.abstraction.visitors.AdtVisitor;
import data.modeling.adt.exceptions.AdtException;
import data.modeling.adt.util.FieldTypeBuilder;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class FieldDefinition implements Definition<AnyType>, Comparable<FieldDefinition> {
    private String name;
    private AnyType type;
    private int index = 0;
    private Boolean isRequired=false;
    private final Set<Annotation<?>> annotations;

    public FieldDefinition(String name, AnyType type) {
        this(name, type, new HashSet<>());
    }
    public FieldDefinition(String name, AnyType type, Set<Annotation<?>> annotations) {
        this.name = name;
        this.type = type;
        this.annotations = annotations;
    }

    public static FieldTypeBuilder builder(String name, AnyType type){
        return new FieldTypeBuilder(name, type);
    }

    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public Set<Annotation<?>> getAnnotations() {
        return annotations;
    }


    public AnyType getType(){
        return type;
    }

    public static FieldDefinition of(String name, AnyType type){
        return new FieldDefinition(name, type, new HashSet<>());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FieldDefinition fieldDefinition = (FieldDefinition) o;
        return index == fieldDefinition.index && name.equals(fieldDefinition.name)
                && type.equals(fieldDefinition.type)
                && annotations.equals(fieldDefinition.annotations)
                && isRequired.equals(fieldDefinition.isRequired);
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public FieldDefinition withIndex(int index) {
        this.setIndex(index);
        return this;
    }

    public Boolean isRequired() {
        return isRequired;
    }

    public void setRequired(boolean required) {
        isRequired = required;
    }

    @Override
    public void accept(AdtVisitor visitor) throws AdtException {
        visitor.enterLabeledType(this);
        Definition.super.accept(visitor);
        visitor.exitLabeledType(this);
    }

    @Override
    public void setType(AnyType anyType) {
        type = anyType;
    }

    @Override
    public int compareTo(FieldDefinition otherFieldDefinition) {
        return Objects.isNull(otherFieldDefinition)? 1 : otherFieldDefinition.hashCode()-this.hashCode();
    }

    @Override
    public String toString() {
        return "FieldType{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", index=" + index +
                ", isRequired=" + isRequired +
                ", annotations=" + annotations +
                '}';
    }
}
