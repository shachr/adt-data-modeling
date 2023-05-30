package data.modeling.adt.util;

import data.modeling.adt.typedefs.ReferencedDefinition;

import java.util.HashSet;
import java.util.Set;

public class ReferenceNamedTypeCollector<T> {
    private Set<ReferencedDefinition> deferredReferences = new HashSet<>();

    public void addReference(ReferencedDefinition reference) {
        deferredReferences.add(reference);
    }
}