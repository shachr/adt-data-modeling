package data.modeling.adt.mappers.jsonschemadraft7ToAdt.fixtures;

import data.modeling.adt.SchemaContext;
import data.modeling.adt.mappers.fixtures.ToAdtFixtureData;
import data.modeling.adt.mappers.jsonschemadraft7ToAdt.annotations.JsonSchemaAnnotation;
import data.modeling.adt.typedefs.*;

import java.util.LinkedHashSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        NamedType myDefinition = NamedType.builder("#/definitions/MyDefinition", ProductType.of(
                FieldType.builder("id", new StringType()).build(),
                FieldType.builder("name", new StringType()).build()
        )).build();
        expectedSchemaContext.registerNamedType(myDefinition);

        NamedType expectedNamedType = NamedType.builder(schemaNamespace, ProductType.of(
                Stream.of(new ReferenceObjectType("#/definitions/MyDefinition")).collect(Collectors.toCollection(LinkedHashSet::new)),
                Stream.of(
                        FieldType.builder("prop1", new StringType()).build(),
                        FieldType.builder("prop2", new DoubleType()).build(),
                        FieldType.builder("prop3", new BoolType()).build(),
                        FieldType.builder("prop4", new ListType(new StringType())).build()
                )

        )).build();
        expectedNamedType.getAnnotations().add(new JsonSchemaAnnotation("$schema", "http://json-schema.org/draft-07/schema#"));
        expectedNamedType.getAnnotations().add(new JsonSchemaAnnotation("$id", schemaNamespace));
        expectedNamedType.getAnnotations().add(new JsonSchemaAnnotation("title", "My Schema"));
        expectedNamedType.getAnnotations().add(new JsonSchemaAnnotation("description", "This is a sample schema that defines a data structure."));
        expectedNamedType.getAnnotations().add(new JsonSchemaAnnotation("x-data-handling-classification", "PUBLIC"));
        expectedNamedType.getAnnotations().add(new JsonSchemaAnnotation("x-data-compliances", 1));
        expectedNamedType.getAnnotations().add(new JsonSchemaAnnotation("x-data-is-personal", true));

        expectedSchemaContext.registerNamedType(expectedNamedType);
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
