package data.modeling.adt.mappers.jsonschemadraft7ToAdt;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import data.modeling.adt.SchemaContext;
import data.modeling.adt.exceptions.AdtException;
import data.modeling.adt.abstraction.monads.NamedTypeStream;
import data.modeling.adt.mappers.jsonschemadraft7ToAdt.exceptions.JsonSchemaJsonProcessingException;
import data.modeling.adt.mappers.registries.ToAdtMapperRegistry;
import data.modeling.adt.typedefs.NamedType;
import data.modeling.adt.mappers.jsonschemadraft7ToAdt.mappers.*;

import java.util.Map;
import java.util.stream.Stream;

public class JsonSchemaDraft7ToAdt implements NamedTypeStream {

    protected ToAdtMapperRegistry toAdtMapperRegistry = new ToAdtMapperRegistry();
    protected Map<String, Object> jsonSchemaMap;
    protected SchemaContext schemaContext;

    public JsonSchemaDraft7ToAdt(String jsonSchemaString) throws JsonSchemaJsonProcessingException {
        this(jsonSchemaString, new SchemaContext());
    }
    public JsonSchemaDraft7ToAdt(String jsonSchemaString, SchemaContext schemaContext) throws JsonSchemaJsonProcessingException {
        this.schemaContext = schemaContext;

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            this.jsonSchemaMap = objectMapper.readValue(jsonSchemaString, Map.class);
        } catch (JsonProcessingException e) {
            throw new JsonSchemaJsonProcessingException(e);
        }

        toAdtMapperRegistry.register(new JsonSchemaNullMapper(toAdtMapperRegistry));
        toAdtMapperRegistry.register(new JsonSchemaAllOfMapper(toAdtMapperRegistry, schemaContext));
        toAdtMapperRegistry.register(new JsonSchemaAnyOfMapper(toAdtMapperRegistry));
        toAdtMapperRegistry.register(new JsonSchemaArrayMapper(toAdtMapperRegistry, schemaContext));
        toAdtMapperRegistry.register(new JsonSchemaIntegerMapper());
        toAdtMapperRegistry.register(new JsonSchemaNumberMapper());
        toAdtMapperRegistry.register(new JsonSchemaBoolMapper());
        toAdtMapperRegistry.register(new JsonSchemaRefMapper(this.jsonSchemaMap, toAdtMapperRegistry, schemaContext));
        toAdtMapperRegistry.register(new JsonSchemaObjectMapper(toAdtMapperRegistry, schemaContext));
        toAdtMapperRegistry.register(new JsonSchemaOneOfMapper(toAdtMapperRegistry, schemaContext));
        toAdtMapperRegistry.register(new JsonSchemaEnumMapper(toAdtMapperRegistry));
        toAdtMapperRegistry.register(new JsonSchemaStringMapper());
    }

    @Override
    public Stream<NamedType> stream() throws AdtException {
        new JsonSchemaMainMapper(toAdtMapperRegistry, schemaContext).toAdt(jsonSchemaMap);
        return schemaContext.stream();
    }
}
