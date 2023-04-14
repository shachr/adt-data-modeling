package data.modeling.adt.util;

import data.modeling.adt.abstraction.typedefs.AnyType;
import data.modeling.adt.abstraction.typedefs.NamedType;

import java.util.HashSet;
import java.util.Set;

public class NamedTypeBuilder {
    private String name;
    private AnyType type;

    private Set annotations = new HashSet<>();

    public NamedTypeBuilder(String name, AnyType type) {
        this.name = name;
        this.type = type;
    }

    public NamedTypeBuilder withAnnotations(){
        return this;
    }

    public NamedType build() {
        return new NamedType(name, type, annotations);
    }
}
