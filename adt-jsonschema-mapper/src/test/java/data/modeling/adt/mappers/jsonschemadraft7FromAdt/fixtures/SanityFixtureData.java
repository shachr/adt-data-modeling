package data.modeling.adt.mappers.jsonschemadraft7FromAdt.fixtures;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import data.modeling.adt.SchemaContext;
import data.modeling.adt.mappers.fixtures.FromAdtFixtureData;
import data.modeling.adt.mappers.fixtures.ToAdtFixtureData;
import data.modeling.adt.mappers.jsonschemadraft7ToAdt.annotations.JsonSchemaAnnotation;
import data.modeling.adt.typedefs.*;
import data.modeling.adt.util.AnyTypeComparator;

import java.util.List;
import java.util.Map;

public class SanityFixtureData implements FromAdtFixtureData<Map<String, Object>> {
    final String schemaNamespace = "http://example.com/product.schema.json";
    private static final String JSON_SCHEMA_STRING = "{\n" +
            "  \"$schema\": \"http://json-schema.org/draft-07/schema#\",\n" +
            "  \"$id\": \"http://example.com/product.schema.json\",\n" +
            "  \"title\": \"Product\",\n" +
            "  \"description\": \"A product from Acme's catalog\",\n" +
            "  \"type\": \"object\",\n" +
            "  \"properties\": {\n" +
            "    \"productId\": {\n" +
            "      \"description\": \"The unique identifier for a product\",\n" +
            "      \"type\": \"integer\"\n" +
            "    },\n" +
            "    \"productName\": {\n" +
            "      \"description\": \"Name of the product\",\n" +
            "      \"type\": \"string\"\n" +
            "    },\n" +
            "    \"price\": {\n" +
            "      \"description\": \"The price of the product\",\n" +
            "      \"type\": \"number\",\n" +
            "      \"exclusiveMinimum\": 0\n" +
            "    },\n" +
            "    \"tags\": {\n" +
            "      \"description\": \"Tags for the product\",\n" +
            "      \"type\": [\"array\", \"null\"],\n" +
            "      \"items\": {\n" +
            "        \"type\": \"string\"\n" +
            "      },\n" +
            "      \"minItems\": 1,\n" +
            "      \"uniqueItems\": true\n" +
            "    },\n" +
            "    \"dimensions\": {\n" +
            "      \"description\": \"Product dimensions\",\n" +
            "      \"type\": \"object\",\n" +
            "      \"properties\": {\n" +
            "        \"length\": { \"type\": \"number\" },\n" +
            "        \"width\": { \"type\": \"number\" },\n" +
            "        \"height\": { \"type\": \"number\" }\n" +
            "      },\n" +
            "      \"required\": [\"length\", \"width\", \"height\"]\n" +
            "    },\n" +
            "    \"color\": {\n" +
            "      \"description\": \"Product color\",\n" +
            "      \"type\": \"string\",\n" +
            "      \"enum\": [\"red\", \"green\", \"blue\"]\n" +
            "    },\n" +
            "    \"isAvailable\": {\n" +
            "      \"description\": \"Product availability\",\n" +
            "      \"type\": \"boolean\"\n" +
            "    }\n" +
            "  },\n" +
            "  \"required\": [\"productId\", \"productName\", \"price\"]\n" +
            "}";

    @Override
    public String toString() {
        return "sanity test";
    }

    @Override
    public String getInputNamedTypeName() {
        return schemaNamespace;
    }

    @Override
    public SchemaContext getInputSchemaContext() {
        SchemaContext schemaContext = new SchemaContext();


        NamedType namedType = NamedType.builder(schemaNamespace, ProductType.of(
                FieldType.builder("productId", new IntType())
                        .withAnnotations(new JsonSchemaAnnotation("description", "The unique identifier for a product"))
                        .withIsRequired(true)
                        .build(),
                FieldType.builder("productName", new StringType())
                        .withAnnotations(new JsonSchemaAnnotation("description", "Name of the product"))
                        .withIsRequired(true)
                        .build(),
                FieldType.builder("price", new DoubleType())
                        .withAnnotations(new JsonSchemaAnnotation("description", "The price of the product"),
                                new JsonSchemaAnnotation("exclusiveMinimum", 0))
                        .withIsRequired(true)
                        .build(),
                FieldType.builder("tags", new NullValueType(new SetType(new StringType())))
                        .withAnnotations(
                                new JsonSchemaAnnotation("minItems", 1),
                                new JsonSchemaAnnotation("description", "Tags for the product")
                        )
                        .build(),
                FieldType.builder("dimensions", ProductType.of(
                                FieldType.builder("length", new DoubleType()).withIsRequired(true).build(),
                                FieldType.builder("width", new DoubleType()).withIsRequired(true).build(),
                                FieldType.builder("height", new DoubleType()).withIsRequired(true).build()
                        ))
                        .withAnnotations(new JsonSchemaAnnotation("description", "Product dimensions"))
                        .build(),
                FieldType.builder("color", EnumType.of(
                                new StringType(),
                                StringType.constantOf("red"),
                                StringType.constantOf("green"),
                                StringType.constantOf("blue")
                        ))
                        .withAnnotations(new JsonSchemaAnnotation("description", "Product color"))
                        .build(),
                FieldType.builder("isAvailable", new BoolType())
                        .withAnnotations(new JsonSchemaAnnotation("description", "Product availability"))
                        .build()
        )).build();

        namedType.getAnnotations().add(new JsonSchemaAnnotation("$schema", "http://json-schema.org/draft-07/schema#"));
        namedType.getAnnotations().add(new JsonSchemaAnnotation("$id", schemaNamespace));
        namedType.getAnnotations().add(new JsonSchemaAnnotation("title", "Product"));
        namedType.getAnnotations().add(new JsonSchemaAnnotation("description", "A product from Acme's catalog"));

        schemaContext.registerNamedType(namedType);
        return schemaContext;
    }

    @Override
    public Map<String, Object> getExpectedSchema() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(JSON_SCHEMA_STRING, Map.class);
    }

    @Override
    public List<AnyTypeComparator.Difference> getExpectedDifferences() {
        return null;
    }
}
