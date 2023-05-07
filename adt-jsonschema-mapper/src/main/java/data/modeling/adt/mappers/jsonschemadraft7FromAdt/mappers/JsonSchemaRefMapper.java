package data.modeling.adt.mappers.jsonschemadraft7FromAdt.mappers;

import data.modeling.adt.SchemaContext;
import data.modeling.adt.exceptions.AdtException;
import data.modeling.adt.mappers.jsonschemadraft7FromAdt.util.MapBuilder;
import data.modeling.adt.mappers.jsonschemadraft7ToAdt.annotations.JsonSchemaAnnotation;
import data.modeling.adt.mappers.registries.FromAdtMapperRegistry;
import data.modeling.adt.typedefs.NamedType;
import data.modeling.adt.typedefs.ReferenceObjectType;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Stream;

import static data.modeling.adt.util.StreamExtensions.toMap;

public class JsonSchemaRefMapper extends JsonSchemaMapper<ReferenceObjectType> {

    private FromAdtMapperRegistry fromAdtMapperRegistry;
    private SchemaContext schemaContext;
    final Map<String, Object> definitions = new LinkedHashMap<>();
    final String prefix = "#/definitions/";

    public JsonSchemaRefMapper(FromAdtMapperRegistry fromAdtMapperRegistry, SchemaContext schemaContext) {

        this.fromAdtMapperRegistry = fromAdtMapperRegistry;
        this.schemaContext = schemaContext;
    }

    @Override
    public boolean canMap(ReferenceObjectType value) {
        return true;
    }

    @Override
    public Stream<Map.Entry<String, Object>> fromAdt(ReferenceObjectType type) throws AdtException {
        NamedType namedType = this.schemaContext.getNamedType(type.getReferenceName());
        this.addDefinition(
                removeDefinitionPrefix(namedType.getName()),
                toMap(this.fromAdtMapperRegistry.fromAdt(namedType.getType()))
        );

        MapBuilder mapBuilder = MapBuilder.create().put("$ref", type.getReferenceName());
        namedType.getAnnotations().stream()
                .filter(annotation -> annotation instanceof JsonSchemaAnnotation)
                .map(annotation -> (JsonSchemaAnnotation)annotation)
                .forEach(jsonSchemaAnnotation -> {
                    mapBuilder.put(jsonSchemaAnnotation.getName(), jsonSchemaAnnotation.getValue());
                });
        
        return mapBuilder.build().entrySet().stream();
    }

    private void addDefinition(String key, Map<String, Object> value){
        definitions.put(key, value);
    }

    private String removeDefinitionPrefix(String ref) {
        return ref.substring(prefix.length());
    }

    public Map<String, Object> getDefinitions() {
        return definitions;
    }
}
