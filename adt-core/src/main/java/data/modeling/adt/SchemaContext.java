package data.modeling.adt;

import data.modeling.adt.abstraction.annotations.Annotation;
import data.modeling.adt.abstraction.visitors.AdtVisitor;
import data.modeling.adt.typedefs.NamedType;
import data.modeling.adt.util.LambdaExceptionUtil;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 */
public class SchemaContext {
    private final Map<String, NamedType> objectTypeMap;
    private final Map<String, Set<Annotation>> annotations;
    private String name;

    public SchemaContext() {
        this("");
    }

    public SchemaContext(String name) {
        this.name = name;
        this.objectTypeMap = new HashMap<>();
        this.annotations = new HashMap<>();
    }

    public SchemaContext(Stream<NamedType> stream) {
        this.objectTypeMap = stream.collect(Collectors.toMap(
                NamedType::getName,
                namedType -> namedType
        ));
        this.annotations = new HashMap<>();
    }

    public void registerNamedType(NamedType namedType)
    {
        objectTypeMap.put(namedType.getName(), namedType);
    }

    public void removeNamedType(NamedType namedType)
    {
        objectTypeMap.remove(namedType.getName());
    }

    public NamedType getNamedType(String name) {
        return objectTypeMap.get(name);
    }

    public boolean containsNamedType(String name) {
        return objectTypeMap.containsKey(name);
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
        return objectTypeMap.size();
    }

    public Stream<NamedType> stream() {
        return objectTypeMap.values().stream();
    }

    public void accept(AdtVisitor visitor) {
        new LinkedHashSet<>(this.objectTypeMap.values()).forEach(LambdaExceptionUtil.consumer(objectType -> objectType.accept(visitor)));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

