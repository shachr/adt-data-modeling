package data.modeling.adt;

import java.util.*;
import java.util.stream.Stream;

public class AdtDslContext {
    private final Set<SchemaContext> schemaContexts = new LinkedHashSet<>();
    private final Map<String, SchemaContext> schemaContextMap = new LinkedHashMap<>();

    private final SchemaContext defaultSchemaContext = new SchemaContext("default");
    private Optional<SchemaContext> activeSchemaContext = Optional.empty();

    public void add(SchemaContext schemaContext){
        schemaContexts.add(schemaContext);
        schemaContextMap.put(schemaContext.getName(), schemaContext);
        activeSchemaContext = Optional.of(schemaContext);
    }

    public Stream<SchemaContext> stream(){
        return Stream.concat(Stream.of(defaultSchemaContext), schemaContexts.stream());
    }

    public SchemaContext getActiveSchemaContext(){
        return activeSchemaContext.orElse(defaultSchemaContext);
    }
}