package data.modeling.adt.pipelines.schemaconversion;

import data.modeling.adt.SchemaContext;
import data.modeling.adt.annotations.datagovernance.DataHandlingClassification;
import data.modeling.adt.annotations.syntactic.DefaultValue;
import data.modeling.adt.annotations.documentation.Description;
import data.modeling.adt.enums.DataHandlingClassifications;
import data.modeling.adt.mappers.jsonschemadraft7ToAdt.annotations.JsonSchemaAnnotation;
import data.modeling.adt.messages.SchemaParsingMessage;
import data.modeling.adt.pipelines.schemaparsing.SchemaParsingPipeline;
import data.modeling.adt.pipelines.schemaparsing.parsers.JsonSchemaTypeParser;
import data.modeling.adt.typedefs.*;
import org.junit.Test;

import static org.junit.Assert.*;

public class JsonSchemaToAdtPipelineTest {
    final static String APPLICATION_SCHEMA_JSON = "application/schema+json";

    private static final NamedType expectedNamedType = NamedType.builder("foo", ProductType.of(
            FieldType.builder("id", new Int32Type()).withAnnotations(
                    new JsonSchemaAnnotation("description", "the identifier of foo"),
                    new Description("the identifier of foo")
            ).withIsRequired(true).build(),
            FieldType.builder("name", new StringType()).withAnnotations(
                    new JsonSchemaAnnotation("x-data-handling-classification", "PUBLIC"),
                    new DataHandlingClassification(DataHandlingClassifications.Public)
            ).withIsRequired(true).build(),
            FieldType.builder("color", EnumType.of(new StringType(),
                    new EnumType.EnumItemType("red", StringType.constantOf("red")),
                    new EnumType.EnumItemType("blue", StringType.constantOf("blue")),
                    new EnumType.EnumItemType("green", StringType.constantOf("green"))))
                .withAnnotations(
                    new JsonSchemaAnnotation("default", "blue"),
                    new DefaultValue("blue"),
                    new JsonSchemaAnnotation("description", "the color"),
                    new Description("the color"),
                    new JsonSchemaAnnotation("x-data-handling-classification", "PUBLIC"),
                    new DataHandlingClassification(DataHandlingClassifications.Public)
                ).build()
    )).withAnnotations(
        new JsonSchemaAnnotation("$id", "foo"),
        new JsonSchemaAnnotation("$schema", "http://json-schema.org/draft-07/schema#")
    ).build();

    private static final String inputJsonSchema = "{\n" +
            "  \"$id\": \"foo\",\n" +
            "  \"$schema\": \"http://json-schema.org/draft-07/schema#\",\n" +
            "  \"type\": \"object\",\n" +
            "  \"properties\": {\n" +
            "    \"id\": {\n" +
            "      \"description\": \"the identifier of foo\",\n" +
            "      \"type\": \"integer\"\n" +
            "    },\n" +
            "    \"name\": {\n" +
            "      \"x-data-handling-classification\": \"PUBLIC\",\n" +
            "      \"type\": \"string\"\n" +
            "    },\n" +
            "    \"color\": {\n" +
            "      \"description\": \"the color\",\n" +
            "      \"x-data-handling-classification\": \"PUBLIC\",\n" +
            "      \"type\": \"string\",\n" +
            "      \"enum\": [\"red\", \"blue\", \"green\"],\n" +
            "      \"default\": \"blue\"\n" +
            "    }\n" +
            "  },\n" +
            "  \"required\": [\n" +
            "    \"id\",\n" +
            "    \"name\"\n" +
            "  ]\n" +
            "}";

    static SchemaParsingPipeline schemaParsingPipeline = new SchemaParsingPipeline();

    @Test
    public void testJsonSchemaToAdt() throws Exception {
        schemaParsingPipeline.registerParser(new JsonSchemaTypeParser());

        // parse
        SchemaParsingMessage schemaParsingMessage = new SchemaParsingMessage(APPLICATION_SCHEMA_JSON, inputJsonSchema);
        SchemaContext schemaContext = schemaParsingPipeline.apply(schemaParsingMessage).getSchemaContext();
        assertNotNull(schemaContext);
        assertTrue(schemaContext.containsNamedType("foo"));
        NamedType namedType = schemaContext.getNamedType("foo");
        assertEquals(expectedNamedType, namedType);
    }
}
