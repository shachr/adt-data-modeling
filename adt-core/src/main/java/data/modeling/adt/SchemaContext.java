package data.modeling.adt;

import data.modeling.adt.abstraction.annotations.Annotation;
import data.modeling.adt.abstraction.typedefs.NamedType;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 */
public class SchemaContext {
    private final Map<String, NamedType> namedTypes;
    private final Map<String, Set<Annotation>> annotations;

    public SchemaContext() {
        this.namedTypes = new HashMap<>();
        this.annotations = new HashMap<>();
    }

    public SchemaContext(Stream<NamedType> stream) {
        this.namedTypes = stream.collect(Collectors.toMap(
                NamedType::getName,
                namedType -> namedType
        ));
        this.annotations = new HashMap<>();
    }

    public void registerNamedType(NamedType namedType) {
        namedTypes.put(namedType.getName(), namedType);
    }

    public NamedType getNamedType(String name) {
        return namedTypes.get(name);
    }

    public boolean containsNamedType(String name) {
        return namedTypes.containsKey(name);
    }

    public void addAnnotation(String name, Annotation annotation) {
        annotations.computeIfAbsent(name, k -> new HashSet<>()).add(annotation);
    }

    public Set<Annotation> getAnnotations(String name) {
        return annotations.getOrDefault(name, Collections.emptySet());
    }

    public static SchemaContext of(Stream<NamedType> namedTypeStream){
        return new SchemaContext(namedTypeStream);
    }

    public int size() {
        return namedTypes.size();
    }

    public Stream<NamedType> stream() {
        return namedTypes.values().stream();
    }
}

