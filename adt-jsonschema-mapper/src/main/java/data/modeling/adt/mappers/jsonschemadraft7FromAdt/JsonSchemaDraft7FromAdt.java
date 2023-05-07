package data.modeling.adt.mappers.jsonschemadraft7FromAdt;

import data.modeling.adt.SchemaContext;
import data.modeling.adt.abstraction.monads.SchemaTypeStream;
import data.modeling.adt.exceptions.AdtException;
import data.modeling.adt.mappers.jsonschemadraft7FromAdt.mappers.*;
import data.modeling.adt.mappers.registries.FromAdtMapperRegistry;
import data.modeling.adt.typedefs.NamedType;

import java.util.Map;
import java.util.stream.Stream;

public class JsonSchemaDraft7FromAdt implements SchemaTypeStream<Map.Entry<String, Object>> {

    protected FromAdtMapperRegistry fromAdtMapperRegistry = new FromAdtMapperRegistry();

    private NamedType namedType;
    protected SchemaContext schemaContext;

    public JsonSchemaDraft7FromAdt(NamedType namedType, SchemaContext schemaContext) {
        this.namedType = namedType;
        this.schemaContext = schemaContext;

        fromAdtMapperRegistry.register(new JsonSchemaNullMapper(fromAdtMapperRegistry));
        fromAdtMapperRegistry.register(new JsonSchemaEnumMapper(fromAdtMapperRegistry));
        fromAdtMapperRegistry.register(new JsonSchemaAllOfMapper(fromAdtMapperRegistry));
        fromAdtMapperRegistry.register(new JsonSchemaObjectMapper(fromAdtMapperRegistry));
        fromAdtMapperRegistry.register(new JsonSchemaOneOfMapper(fromAdtMapperRegistry));
        fromAdtMapperRegistry.register(new JsonSchemaPrimitiveMapper());
        fromAdtMapperRegistry.register(new JsonSchemaArrayMapper(fromAdtMapperRegistry));
        fromAdtMapperRegistry.register(new JsonSchemaRefMapper(fromAdtMapperRegistry, schemaContext));
    }

    @Override
    public Stream<Map.Entry<String, Object>> stream() throws AdtException {
        return new JsonSchemaMainMapper(fromAdtMapperRegistry, schemaContext).fromAdt(namedType);
    }
}
