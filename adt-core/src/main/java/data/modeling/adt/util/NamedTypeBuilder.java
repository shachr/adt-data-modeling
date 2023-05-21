package data.modeling.adt.util;

import data.modeling.adt.abstraction.annotations.Annotation;
import data.modeling.adt.typedefs.AnyType;
import data.modeling.adt.typedefs.ComplexType;
import data.modeling.adt.typedefs.CompositionType;
import data.modeling.adt.typedefs.NamedType;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class NamedTypeBuilder {
    private String name;
    private ComplexType type;

    private final Set<Annotation<?>> annotations = new LinkedHashSet<>();

    public NamedTypeBuilder(String name, ComplexType type) {
        this.name = name;
        this.type = type;
    }

    public NamedTypeBuilder withAnnotations(Annotation<?>... annotations){
        this.annotations.addAll(Arrays.stream(annotations).collect(Collectors.toCollection(LinkedHashSet::new)));
        return this;
    }

    public NamedType build() {
        return new NamedType(name, type, annotations);
    }
}
