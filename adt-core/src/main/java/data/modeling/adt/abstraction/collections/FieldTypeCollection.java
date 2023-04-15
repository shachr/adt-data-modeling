package data.modeling.adt.abstraction.collections;

import data.modeling.adt.typedefs.FieldType;

import java.util.*;
import java.util.stream.Collectors;

public class FieldTypeCollection  {
    private final LinkedHashSet<FieldType> fieldTypes = new LinkedHashSet<>();
    private final Map<String, FieldType> fieldsMap = new HashMap<>();

    public boolean add(FieldType fieldType) {
        fieldType.setIndex(fieldTypes.size());
        fieldsMap.put(fieldType.getName(), fieldType);
        return fieldTypes.add(fieldType);
    }

    public FieldType get(String name){
        return fieldsMap.get(name);
    }

    public void addAll(Collection<FieldType> collection) {
        collection.forEach(fieldType -> add(fieldType));
    }

    public Set<FieldType> getFields(){
        return fieldTypes;
    }

    public Set<FieldType> cloneFields(){
        return fieldTypes.stream().map(fieldType-> {
            FieldType cloneFieldType = new FieldType(fieldType.getName(), fieldType.getType());
            cloneFieldType.getAnnotations().addAll(fieldType.getAnnotations());
            return cloneFieldType;
        }).collect(Collectors.toCollection(LinkedHashSet::new));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FieldTypeCollection that = (FieldTypeCollection) o;
        return fieldTypes.equals(that.fieldTypes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fieldTypes);
    }
}
