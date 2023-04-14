package data.modeling.adt.mappers.jsonschemadraft7.fixtures;

public class JsonSchemaAllOfTestFixture {
    public static final String JSON_SCHEMA_STRING = "{\n" +
            "  \"$schema\": \"http://json-schema.org/draft-07/schema#\",\n" +
            "  \"$id\": \"http://example.com/allof.schema.json\",\n" +
            "  \"title\": \"My Schema\",\n" +
            "  \"description\": \"This is a sample schema that defines a data structure.\",\n" +
            "  \"type\": \"object\",\n" +
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
            "      \"x-data-handling-classification\": \"PUBLIC\",\n" +
            "      \"x-data-compliances\": 1,\n" +
            "      \"x-data-is-personal-data\": true,\n" +
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
}
