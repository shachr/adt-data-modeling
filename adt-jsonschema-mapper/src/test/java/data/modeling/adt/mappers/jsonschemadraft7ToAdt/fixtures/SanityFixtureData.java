package data.modeling.adt.mappers.jsonschemadraft7ToAdt.fixtures;

import data.modeling.adt.SchemaContext;
import data.modeling.adt.mappers.fixtures.ToAdtFixtureData;
import data.modeling.adt.mappers.jsonschemadraft7ToAdt.annotations.JsonSchemaAnnotation;
import data.modeling.adt.typedefs.*;

public class SanityFixtureData implements ToAdtFixtureData<String> {
    private final String schemaNamespace = "http://example.com/product.schema.json";
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
            "  \"required\": [\"productId\", \"productName\", \"price\"],\n" +
            "  \"additionalProperties\": {\"type\": \"boolean\"}\n" +
            "}";

    @Override
    public String toString() {
        return "sanity test";
    }

    @Override
    public String getExpectedNamedTypeName() {
        return schemaNamespace;
    }

    @Override
    public SchemaContext getExpectedSchemaContext() {
        SchemaContext schemaContext = new SchemaContext();

        TypeDefinition typeDefinition = TypeDefinition.builder(schemaNamespace, ProductType.of(
                FieldDefinition.builder("productId", new Int32Type())
                        .withAnnotations(new JsonSchemaAnnotation("description", "The unique identifier for a product"))
                        .withIsRequired(true)
                        .build(),
                FieldDefinition.builder("productName", new StringType())
                        .withAnnotations(new JsonSchemaAnnotation("description", "Name of the product"))
                        .withIsRequired(true)
                        .build(),
                FieldDefinition.builder("price", new DoubleType())
                        .withAnnotations(new JsonSchemaAnnotation("description", "The price of the product"),
                                new JsonSchemaAnnotation("exclusiveMinimum", 0))
                        .withIsRequired(true)
                        .build(),
                FieldDefinition.builder("tags", new NullValueType(new SetType(new StringType())))
                        .withAnnotations(
                                new JsonSchemaAnnotation("minItems", 1),
                                new JsonSchemaAnnotation("description", "Tags for the product")
                        )
                        .build(),
                FieldDefinition.builder("dimensions", ProductType.of(
                                FieldDefinition.builder("length", new DoubleType()).withIsRequired(true).build(),
                                FieldDefinition.builder("width", new DoubleType()).withIsRequired(true).build(),
                                FieldDefinition.builder("height", new DoubleType()).withIsRequired(true).build()
                        ))
                        .withAnnotations(new JsonSchemaAnnotation("description", "Product dimensions"))
                        .build(),
                FieldDefinition.builder("color", EnumType.of(
                                new StringType(),
                                new EnumType.EnumItemType("red", StringType.constantOf("red")),
                                new EnumType.EnumItemType("green", StringType.constantOf("green")),
                                new EnumType.EnumItemType("blue", StringType.constantOf("blue"))
                        ))
                        .withAnnotations(new JsonSchemaAnnotation("description", "Product color"))
                        .build(),
                FieldDefinition.builder("isAvailable", new BoolType())
                        .withAnnotations(new JsonSchemaAnnotation("description", "Product availability"))
                        .build(),
                new FieldAdditionalDefinition(ProductType.of(new FieldDefinition("additionalProperties", new MapType(new StringType(), new BoolType()))))
        )).build();

        typeDefinition.getAnnotations().add(new JsonSchemaAnnotation("$schema", "http://json-schema.org/draft-07/schema#"));
        typeDefinition.getAnnotations().add(new JsonSchemaAnnotation("$id", schemaNamespace));
        typeDefinition.getAnnotations().add(new JsonSchemaAnnotation("title", "Product"));
        typeDefinition.getAnnotations().add(new JsonSchemaAnnotation("description", "A product from Acme's catalog"));

        schemaContext.registerNamedType(typeDefinition);
        return schemaContext;
    }

    @Override
    public int expectedSchemaContextSize() {
        return 1;
    }

    @Override
    public int expectedDiffSize() {
        return 0;
    }

    @Override
    public String getInputSchema() {
        return JSON_SCHEMA_STRING;
    }
}
