package data.modeling.adt.mappers.jsonschemadraft7FromAdt.mappers;

import data.modeling.adt.SchemaContext;
import data.modeling.adt.exceptions.AdtException;
import data.modeling.adt.mappers.jsonschemadraft7FromAdt.util.MapBuilder;
import data.modeling.adt.mappers.jsonschemadraft7ToAdt.annotations.JsonSchemaAnnotation;
import data.modeling.adt.mappers.registries.FromAdtMapperRegistry;
import data.modeling.adt.typedefs.NamedType;

import static data.modeling.adt.util.StreamExtensions.toMap;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Stream;

public class JsonSchemaMainMapper extends JsonSchemaMapper<NamedType> {

    private FromAdtMapperRegistry fromAdtMapperRegistry;
    private SchemaContext schemaContext;

    public JsonSchemaMainMapper(FromAdtMapperRegistry fromAdtMapperRegistry, SchemaContext schemaContext) {

        this.fromAdtMapperRegistry = fromAdtMapperRegistry;
        this.schemaContext = schemaContext;
    }

    @Override
    public boolean canMap(NamedType value) {
        return false;
    }

    @Override
    public Stream<Map.Entry<String, Object>> fromAdt(NamedType type) throws AdtException {
        MapBuilder jsonSchemaMap = MapBuilder.create();
        type.getAnnotations().stream()
                .filter(annotation -> annotation instanceof JsonSchemaAnnotation)
                .map(annotation -> (JsonSchemaAnnotation)annotation)
                .forEach(jsonSchemaAnnotation -> {
                    jsonSchemaMap.put(jsonSchemaAnnotation.getName(), jsonSchemaAnnotation.getValue());
                });
        jsonSchemaMap.put("$id", type.getName());
        jsonSchemaMap.merge(toMap(fromAdtMapperRegistry.fromAdt(type.getType())));
        jsonSchemaMap.put("definitions", ((JsonSchemaRefMapper)fromAdtMapperRegistry.getMapper(JsonSchemaRefMapper.class)).getDefinitions());
        return jsonSchemaMap.build().entrySet().stream();
    }
}