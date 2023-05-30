package data.modeling.adt.mappers.jsonschemadraft7ToAdt.fixtures;

import data.modeling.adt.SchemaContext;
import data.modeling.adt.mappers.fixtures.ToAdtFixtureData;
import data.modeling.adt.mappers.jsonschemadraft7ToAdt.annotations.JsonSchemaAnnotation;
import data.modeling.adt.typedefs.*;

public class AllOfFixtureData implements ToAdtFixtureData<String> {

    private final String schemaNamespace = "http://example.com/allof.schema.json";

    private static final String JSON_SCHEMA_STRING = "{\n" +
            "  \"$schema\": \"http://json-schema.org/draft-07/schema#\",\n" +
            "  \"$id\": \"http://example.com/allof.schema.json\",\n" +
            "  \"title\": \"My Schema\",\n" +
            "  \"description\": \"This is a sample schema that defines a data structure.\",\n" +
            "  \"type\": \"object\",\n" +
            "      \"x-data-handling-classification\": \"PUBLIC\",\n" +
            "      \"x-data-compliances\": 1,\n" +
            "      \"x-data-is-personal\": true,\n" +
            "  \"allOf\": [\n" +
            "    {\n" +
            "      \"$ref\": \"#/definitions/MyDefinition\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"type\": \"object\",\n" +
            "      \"properties\": {\n" +
            "        \"prop1\": {\n" +
            "          \"type\": \"string\"\n" +
            "        },\n" +
            "        \"prop2\": {\n" +
            "          \"type\": \"number\"\n" +
            "        }\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"type\": \"object\",\n" +
            "      \"properties\": {\n" +
            "        \"prop3\": {\n" +
            "          \"type\": \"boolean\"\n" +
            "        },\n" +
            "        \"prop4\": {\n" +
            "          \"type\": \"array\",\n" +
            "          \"items\": {\n" +
            "            \"type\": \"string\"\n" +
            "          }\n" +
            "        }\n" +
            "      }\n" +
            "    }\n" +
            "  ],\n" +
            "  \"definitions\": {\n" +
            "    \"MyDefinition\": {\n" +
            "      \"type\": \"object\",\n" +
            "      \"properties\": {\n" +
            "        \"id\": {\n" +
            "          \"type\": \"string\"\n" +
            "        },\n" +
            "        \"name\": {\n" +
            "          \"type\": \"string\"\n" +
            "        }\n" +
            "      },\n" +
            "      \"required\": [\"id\", \"name\"]\n" +
            "    }\n" +
            "  }\n" +
            "}";
    @Override
    public String toString() {
        return "allOf test";
    }

    @Override
    public String getExpectedNamedTypeName() {
        return schemaNamespace;
    }

    @Override
    public SchemaContext getExpectedSchemaContext() {

        SchemaContext expectedSchemaContext = new SchemaContext();
        TypeDefinition myDefinition = TypeDefinition.builder(schemaNamespace + "_MyDefinition", ProductType.of(
                FieldDefinition.builder("id", new StringType()).build(),
                FieldDefinition.builder("name", new StringType()).build()
        )).build();
        expectedSchemaContext.registerNamedType(myDefinition);

        TypeDefinition expectedTypeDefinition = TypeDefinition.builder(schemaNamespace, AllOfType.of(
                new ReferencedDefinition(schemaNamespace + "_MyDefinition"),
                ProductType.of(
                        FieldDefinition.builder("prop1", new StringType()).build(),
                        FieldDefinition.builder("prop2", new DoubleType()).build()
                ),
                ProductType.of(
                        FieldDefinition.builder("prop3", new BoolType()).build(),
                        FieldDefinition.builder("prop4", new ListType(new StringType())).build()
                )

        )).build();
        expectedTypeDefinition.getAnnotations().add(new JsonSchemaAnnotation("$schema", "http://json-schema.org/draft-07/schema#"));
        expectedTypeDefinition.getAnnotations().add(new JsonSchemaAnnotation("$id", schemaNamespace));
        expectedTypeDefinition.getAnnotations().add(new JsonSchemaAnnotation("title", "My Schema"));
        expectedTypeDefinition.getAnnotations().add(new JsonSchemaAnnotation("description", "This is a sample schema that defines a data structure."));
        expectedTypeDefinition.getAnnotations().add(new JsonSchemaAnnotation("x-data-handling-classification", "PUBLIC"));
        expectedTypeDefinition.getAnnotations().add(new JsonSchemaAnnotation("x-data-compliances", 1));
        expectedTypeDefinition.getAnnotations().add(new JsonSchemaAnnotation("x-data-is-personal", true));

        expectedSchemaContext.registerNamedType(expectedTypeDefinition);
        return expectedSchemaContext;
    }

    @Override
    public int expectedSchemaContextSize() {
        return 2;
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
