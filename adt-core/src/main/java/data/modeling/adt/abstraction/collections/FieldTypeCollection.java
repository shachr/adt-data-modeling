package data.modeling.adt.abstraction.collections;

import data.modeling.adt.typedefs.FieldDefinition;

import java.util.*;
import java.util.stream.Collectors;

public class FieldTypeCollection  {
    private final LinkedHashSet<FieldDefinition> fieldDefinitions = new LinkedHashSet<>();
    private final Map<String, FieldDefinition> fieldsMap = new HashMap<>();

    public boolean add(FieldDefinition fieldDefinition) {
        fieldDefinition.setIndex(fieldDefinitions.size());
        fieldsMap.put(fieldDefinition.getName(), fieldDefinition);
        return fieldDefinitions.add(fieldDefinition);
    }

    public FieldDefinition get(String name){
        return fieldsMap.get(name);
    }

    public boolean containsName(String name){
        return fieldsMap.containsKey(name);
    }

    public void addAll(Collection<FieldDefinition> collection) {
        collection.forEach(this::add);
    }

    public Set<FieldDefinition> getFields(){
        return fieldDefinitions;
    }

    public Set<FieldDefinition> cloneFields(){
        return fieldDefinitions.stream().map(fieldType-> {
            FieldDefinition cloneFieldDefinition = new FieldDefinition(fieldType.getName(), fieldType.getType());
            cloneFieldDefinition.getAnnotations().addAll(fieldType.getAnnotations());
            return cloneFieldDefinition;
        }).collect(Collectors.toCollection(LinkedHashSet::new));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FieldTypeCollection that = (FieldTypeCollection) o;
        return fieldDefinitions.equals(that.fieldDefinitions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fieldDefinitions);
    }
}
