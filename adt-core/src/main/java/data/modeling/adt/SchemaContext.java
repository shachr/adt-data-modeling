package data.modeling.adt;

import data.modeling.adt.abstraction.annotations.Annotation;
import data.modeling.adt.abstraction.visitors.AdtVisitor;
import data.modeling.adt.typedefs.ComplexType;
import data.modeling.adt.typedefs.Definition;
import data.modeling.adt.typedefs.TypeDefinition;
import data.modeling.adt.util.LambdaExceptionUtil;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 */
public class SchemaContext {
    private final Map<String, Definition<ComplexType>> objectTypeMap;
    private final Map<String, Set<Annotation>> annotations;

    private final Set<SchemaContextSetting> settings = new LinkedHashSet<>();
    private String name;

    public SchemaContext() {
        this("");
    }

    public SchemaContext(String name) {
        this.name = name;
        this.objectTypeMap = new HashMap<>();
        this.annotations = new HashMap<>();
    }

    public SchemaContext(Stream<Definition<ComplexType>> stream) {
        this.objectTypeMap = stream.collect(Collectors.toMap(
                Definition::getName,
                namedType -> namedType
        ));
        this.annotations = new HashMap<>();
    }

    public void registerNamedType(Definition<ComplexType> definition)
    {
        objectTypeMap.put(definition.getName(), definition);
    }

    public void removeNamedType(Definition<ComplexType> definition)
    {
        objectTypeMap.remove(definition.getName());
    }

    public Definition<ComplexType> getNamedType(String name) {
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

    public static SchemaContext of(Stream<Definition<ComplexType>> namedTypeStream){
        return new SchemaContext(namedTypeStream);
    }

    public int size() {
        return objectTypeMap.size();
    }

    public Stream<Definition<ComplexType>> stream() {
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

    public void set(String settingName, Object value) {
        settings.add(new SchemaContextSetting(settingName, value));
    }

    public Set<SchemaContextSetting> getSettings(){
        return settings;
    }
}

