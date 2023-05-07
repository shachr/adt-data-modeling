package data.modeling.adt;

import data.modeling.adt.messages.*;
import data.modeling.adt.pipelines.schemaconvertion.SchemaConversionPipeline;
import data.modeling.adt.pipelines.schemaconvertion.converters.JsonSchemaConverter;
import data.modeling.adt.pipelines.schemaparsing.SchemaParsingPipeline;
import data.modeling.adt.pipelines.schemaparsing.parsers.JsonSchemaTypeParser;
import data.modeling.adt.pipelines.schemavalidation.SchemaValidationPipeline;
import data.modeling.adt.pipelines.schemavalidation.validators.JsonSchemaValidator;
import data.modeling.adt.util.LambdaExceptionUtil;

import java.util.concurrent.CompletableFuture;

public class Main {
    final static String APPLICATION_SCHEMA_JSON = "application/schema+json";

    public static final String SCHEMA_STRING = "{\n" +
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
    static SchemaConversionPipeline schemaConversionPipeline = new SchemaConversionPipeline();
    static SchemaValidationPipeline schemaValidationPipeline = new SchemaValidationPipeline();

    public static void main(String[] args) throws Exception {

        schemaParsingPipeline.registerParser(new JsonSchemaTypeParser());
        schemaConversionPipeline.registerConverter(new JsonSchemaConverter());
        schemaValidationPipeline.registerConverter(new JsonSchemaValidator());

        // parse
        CompletableFuture<SchemaValidationMessage> future = CompletableFuture.supplyAsync(LambdaExceptionUtil.supplier(Main::parseSchema))
             // validate
            .thenCompose(parsedSchema -> CompletableFuture.supplyAsync(LambdaExceptionUtil.supplier(()-> validateSchema(parsedSchema)))
            // convert
            .whenComplete(LambdaExceptionUtil.biConsumer(Main::convertSchema)));

        future.get();
    }

    private static void convertSchema(SchemaValidationMessage schemaValidationMessage, Throwable ex) throws Exception {
        SchemaConvertionMessage schemaConvertionMessage = new SchemaConvertionMessage(APPLICATION_SCHEMA_JSON, "foo", schemaValidationMessage.getSchemaContext());
        SchemaConvertedMessage schemaConvertedMessage = schemaConversionPipeline.apply(schemaConvertionMessage);

        System.out.println(schemaValidationMessage.getSchemaContext().containsNamedType("foo"));
        System.out.println("--");
        System.out.println(schemaConvertedMessage.getSchemaContent());
    }

    private static SchemaValidationMessage validateSchema(SchemaParsedMessage schemaParsedMessage) throws Exception {
        SchemaValidationMessage schemaValidationMessage = schemaValidationPipeline.apply(schemaParsedMessage);
        schemaValidationMessage.getValidationErrors().forEach(validationError -> {
            System.out.println(validationError.getJsonPointer() + ", "+ validationError.getErrorCode() + ", " + validationError.getMessage());
        });
        return schemaValidationMessage;
    }

    private static SchemaParsedMessage parseSchema() throws Exception {
        SchemaParsingMessage schemaParsingMessage = new SchemaParsingMessage(APPLICATION_SCHEMA_JSON, SCHEMA_STRING);
        return schemaParsingPipeline.apply(schemaParsingMessage);
    }
}