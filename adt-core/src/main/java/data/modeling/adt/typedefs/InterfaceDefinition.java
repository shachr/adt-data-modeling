package data.modeling.adt.typedefs;

import data.modeling.adt.abstraction.annotations.Annotation;

import java.util.LinkedHashSet;
import java.util.Set;

public class InterfaceDefinition implements Definition<ComplexType>{

    private String name;
    private ComplexType type;
    private final Set<Annotation<?>> annotations;

    public InterfaceDefinition(String name, ComplexType type){
        this.name = name;
        this.type = type;
        annotations = new LinkedHashSet<>();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public ComplexType getType() {
        return type;
    }

    @Override
    public void setType(ComplexType anyType) {
        type = anyType;
    }

    @Override
    public Set<Annotation<?>> getAnnotations() {
        return annotations;
    }
}
