package data.modeling.adt.util;

import data.modeling.adt.abstraction.annotations.Annotation;
import data.modeling.adt.typedefs.AnyType;
import data.modeling.adt.typedefs.FieldDefinition;

import java.util.*;
import java.util.stream.Collectors;

public class FieldTypeBuilder {
    private String name;
    private AnyType type;

    private Set<Annotation<?>> annotations = new LinkedHashSet<>();
    private boolean isRequired = false;

    public FieldTypeBuilder(String name, AnyType type) {
        this.name = name;
        this.type = type;
    }

    public FieldTypeBuilder withAnnotations(Annotation<?>... annotations){
        this.annotations.addAll(Arrays.stream(annotations).collect(Collectors.toCollection(LinkedHashSet::new)));
        return this;
    }

    public FieldTypeBuilder withIsRequired(boolean required) {
        isRequired = required;
        return this;
    }

    public FieldDefinition build() {
        FieldDefinition fieldDefinition = new FieldDefinition(name, type, annotations);
        fieldDefinition.setRequired(this.isRequired);
        return fieldDefinition;
    }
}
