package data.modeling.adt.abstraction.collections;

import data.modeling.adt.abstraction.typedefs.FieldType;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

public class FieldTypeCollection  {
    private final LinkedHashSet linkedHashSet = new LinkedHashSet<FieldType>();

    public boolean add(FieldType fieldType) {
        fieldType.setIndex(linkedHashSet.size());
        return linkedHashSet.add(fieldType);
    }

    public void addAll(Collection<FieldType> collection) {
        collection.forEach(fieldType -> add(fieldType));
    }

    public Set<FieldType> getFields(){
        return linkedHashSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FieldTypeCollection that = (FieldTypeCollection) o;
        return linkedHashSet.equals(that.linkedHashSet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(linkedHashSet);
    }
}
