package data.modeling.adt.mappers.jsonschemadraft7FromAdt;

import data.modeling.adt.SchemaContext;
import data.modeling.adt.abstraction.monads.SchemaTypeStream;
import data.modeling.adt.exceptions.AdtException;
import data.modeling.adt.mappers.jsonschemadraft7FromAdt.mappers.*;
import data.modeling.adt.mappers.registries.FromAdtMapperRegistry;
import data.modeling.adt.typedefs.NamedType;

import java.util.Map;
import java.util.stream.Stream;

import static data.modeling.adt.util.StreamExtensions.toMap;

public class JsonSchemaDraft7FromAdt implements SchemaTypeStream<Map<String, Object>> {

    private final NamedType namedType;
    protected SchemaContext schemaContext;
    private final FromAdtMapperRegistry fromAdtMapperRegistry = new FromAdtMapperRegistry();

    public JsonSchemaDraft7FromAdt(NamedType namedType, SchemaContext schemaContext) {
        this.namedType = namedType;
        this.schemaContext = schemaContext;
        fromAdtMapperRegistry.register(new JsonSchemaNullMapper(fromAdtMapperRegistry));
        fromAdtMapperRegistry.register(new JsonSchemaEnumMapper(fromAdtMapperRegistry));
        fromAdtMapperRegistry.register(new JsonSchemaAllOfMapper(fromAdtMapperRegistry));
        fromAdtMapperRegistry.register(new JsonSchemaAnyOfMapper(fromAdtMapperRegistry));
        //todo: initially the schema adapter was denormalizing the schema,
        // this should happen as part of schema processing
//        fromAdtMapperRegistry.register(new JsonSchemaProductTypeWithExtendingReferences(fromAdtMapperRegistry));
        fromAdtMapperRegistry.register(new JsonSchemaObjectMapper(fromAdtMapperRegistry));
        fromAdtMapperRegistry.register(new JsonSchemaOneOfMapper(fromAdtMapperRegistry));
        fromAdtMapperRegistry.register(new JsonSchemaScalarMapper());
        fromAdtMapperRegistry.register(new JsonSchemaArrayMapper(fromAdtMapperRegistry));
        fromAdtMapperRegistry.register(new JsonSchemaRefMapper(fromAdtMapperRegistry, schemaContext));
    }

    @Override
    public FromAdtMapperRegistry getMapperRegistry() {
        return fromAdtMapperRegistry;
    }

    @Override
    public Stream<Map<String, Object>> stream() throws AdtException {
        return Stream.of(toMap(new JsonSchemaMainMapper(fromAdtMapperRegistry, schemaContext).fromAdt(namedType)));
    }
}
