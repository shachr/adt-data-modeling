package data.modeling.adt.mappers.jsonschemadraft7ToAdt.fixtures;

public class JsonSchemaSanityTestFixture {
    public static final String JSON_SCHEMA_STRING = "{\n" +
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
            "      \"type\": \"array\",\n" +
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
}
