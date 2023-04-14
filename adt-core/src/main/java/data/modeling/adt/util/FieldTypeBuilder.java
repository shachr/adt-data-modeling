package data.modeling.adt.util;

import data.modeling.adt.abstraction.typedefs.AnyType;
import data.modeling.adt.abstraction.typedefs.FieldType;

import java.util.HashSet;
import java.util.Set;

public class FieldTypeBuilder {
    private String name;
    private AnyType type;

    private Set annotations = new HashSet<>();

    public FieldTypeBuilder(String name, AnyType type) {
        this.name = name;
        this.type = type;
    }

    public FieldTypeBuilder withAnnotations(){
        return this;
    }

    public FieldType build(int index) {
        FieldType fieldType = new FieldType(name, type, annotations);
        fieldType.setIndex(index);
        return fieldType;
    }
}
