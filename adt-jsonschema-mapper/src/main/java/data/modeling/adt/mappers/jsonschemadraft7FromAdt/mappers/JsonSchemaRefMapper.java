package data.modeling.adt.mappers.jsonschemadraft7FromAdt.mappers;

import data.modeling.adt.SchemaContext;
import data.modeling.adt.exceptions.AdtException;
import data.modeling.adt.mappers.jsonschemadraft7FromAdt.util.MapBuilder;
import data.modeling.adt.mappers.jsonschemadraft7ToAdt.annotations.JsonSchemaAnnotation;
import data.modeling.adt.mappers.registries.FromAdtMapperRegistry;
import data.modeling.adt.typedefs.NamedType;
import data.modeling.adt.typedefs.ReferenceNamedType;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Stream;

import static data.modeling.adt.util.StreamExtensions.toMap;

public class JsonSchemaRefMapper extends JsonSchemaMapper<ReferenceNamedType> {

    private FromAdtMapperRegistry fromAdtMapperRegistry;
    private SchemaContext schemaContext;
    final Map<String, Object> definitions;
    final String prefix = "#/definitions/";

    public JsonSchemaRefMapper(FromAdtMapperRegistry fromAdtMapperRegistry, SchemaContext schemaContext) {

        this.fromAdtMapperRegistry = fromAdtMapperRegistry;
        this.schemaContext = schemaContext;
        this.definitions= new LinkedHashMap<>();
    }

    @Override
    public boolean canMap(ReferenceNamedType value) {
        return true;
    }

    @Override
    public Stream<Map.Entry<String, Object>> fromAdt(ReferenceNamedType type) throws AdtException {
        NamedType namedType = this.schemaContext.getNamedType(type.getReferenceName().replaceAll(prefix, ""));
        this.definitions.put(namedType.getName(), toMap(this.fromAdtMapperRegistry.fromAdt(namedType.getType())));

        MapBuilder mapBuilder = MapBuilder.create().put("$ref", prefix + namedType.getName());
        return mapBuilder.build().entrySet().stream();
    }

    public Map<String, Object> getDefinitions() {
        return definitions;
    }
}
