package data.modeling.adt.mappers.jsonschemadraft7FromAdt.mappers;

import data.modeling.adt.SchemaContext;
import data.modeling.adt.exceptions.AdtException;
import data.modeling.adt.mappers.jsonschemadraft7FromAdt.util.MapBuilder;
import data.modeling.adt.mappers.registries.FromAdtMapperRegistry;
import data.modeling.adt.typedefs.ComplexType;
import data.modeling.adt.typedefs.Definition;
import data.modeling.adt.typedefs.TypeDefinition;
import data.modeling.adt.typedefs.ReferencedDefinition;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Stream;

import static data.modeling.adt.util.StreamExtensions.toMap;

public class JsonSchemaRefMapper extends JsonSchemaMapper<ReferencedDefinition> {

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
    public boolean canMap(ReferencedDefinition value) {
        return true;
    }

    @Override
    public Stream<Map.Entry<String, Object>> fromAdt(ReferencedDefinition type) throws AdtException {
        Definition<ComplexType> typeDefinition = this.schemaContext.getNamedType(type.getReferenceName().replaceAll(prefix, ""));
        this.definitions.put(typeDefinition.getName(), toMap(this.fromAdtMapperRegistry.fromAdt(typeDefinition.getType())));

        MapBuilder mapBuilder = MapBuilder.create().put("$ref", prefix + typeDefinition.getName());
        return mapBuilder.build().entrySet().stream();
    }

    public Map<String, Object> getDefinitions() {
        return definitions;
    }
}
