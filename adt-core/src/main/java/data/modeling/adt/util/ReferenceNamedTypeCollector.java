package data.modeling.adt.util;

import data.modeling.adt.typedefs.ReferenceNamedType;

import java.util.HashSet;
import java.util.Set;

public class ReferenceNamedTypeCollector<T> {
    private Set<ReferenceNamedType> deferredReferences = new HashSet<>();

    public void addReference(ReferenceNamedType reference) {
        deferredReferences.add(reference);
    }
}