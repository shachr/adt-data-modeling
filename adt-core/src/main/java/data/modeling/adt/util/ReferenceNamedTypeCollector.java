package data.modeling.adt.util;

import data.modeling.adt.typedefs.ReferenceObjectType;

import java.util.HashSet;
import java.util.Set;

public class ReferenceNamedTypeCollector<T> {
    private Set<ReferenceObjectType> deferredReferences = new HashSet<>();

    public void addReference(ReferenceObjectType reference) {
        deferredReferences.add(reference);
    }
}