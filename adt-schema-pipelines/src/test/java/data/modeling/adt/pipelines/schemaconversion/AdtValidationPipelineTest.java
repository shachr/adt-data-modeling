package data.modeling.adt.pipelines.schemaconversion;

import data.modeling.adt.messages.SchemaParsedMessage;
import data.modeling.adt.messages.SchemaParsingMessage;
import data.modeling.adt.messages.SchemaValidationMessage;
import data.modeling.adt.pipelines.schemaconvertion.SchemaConversionPipeline;
import data.modeling.adt.pipelines.schemaconvertion.converters.JsonSchemaConverter;
import data.modeling.adt.pipelines.schemaparsing.SchemaParsingPipeline;
import data.modeling.adt.pipelines.schemaparsing.parsers.JsonSchemaTypeParser;
import data.modeling.adt.pipelines.schemavalidation.SchemaValidationPipeline;
import data.modeling.adt.pipelines.schemavalidation.validators.JsonSchemaValidator;
import data.modeling.adt.util.LambdaExceptionUtil;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertEquals;

public class AdtValidationPipelineTest {
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

    @Test
    public void test() throws ExecutionException, InterruptedException {
        schemaParsingPipeline.registerParser(new JsonSchemaTypeParser());
        schemaConversionPipeline.registerConverter(new JsonSchemaConverter());
        schemaValidationPipeline.registerConverter(new JsonSchemaValidator());

        // parse
        CompletableFuture<SchemaValidationMessage> future = CompletableFuture.supplyAsync(LambdaExceptionUtil.supplier(this::parseSchema))
                // validate
                .thenCompose(parsedSchema -> CompletableFuture.supplyAsync(LambdaExceptionUtil.supplier(()-> validateSchema(parsedSchema))));

        SchemaValidationMessage schemaValidationMessage = future.get();
        assertEquals(2, schemaValidationMessage.getValidationErrors().size());
        assertEquals(404, schemaValidationMessage.getValidationErrors().get(0).getErrorCode());
        assertEquals(404, schemaValidationMessage.getValidationErrors().get(1).getErrorCode());
    }

//    private void convertSchema(SchemaValidationMessage schemaValidationMessage, Throwable ex) throws Exception {
//        SchemaContext schemaContext = schemaValidationMessage.getSchemaContext();
//        SchemaConvertedMessage schemaConvertedMessage = schemaConversionPipeline.apply(
//                new SchemaConvertionMessage(APPLICATION_SCHEMA_JSON, "foo", schemaContext));
//
//        System.out.println(schemaConvertedMessage.<JsonSchemaFile>getSchemaArtifactOf());
//
////        javaCodeGen(schemaContext);
//    }

    private static SchemaValidationMessage validateSchema(SchemaParsedMessage schemaParsedMessage) throws Exception {
        SchemaValidationMessage schemaValidationMessage = schemaValidationPipeline.apply(schemaParsedMessage);
        schemaValidationMessage.getValidationErrors().forEach(validationError -> {
            System.out.println(validationError.getJsonPointer() + ", "+ validationError.getErrorCode() + ", " + validationError.getMessage());
        });
        return schemaValidationMessage;
    }

    private SchemaParsedMessage parseSchema() throws Exception {
        SchemaParsingMessage schemaParsingMessage = new SchemaParsingMessage(APPLICATION_SCHEMA_JSON, SCHEMA_STRING);
        return schemaParsingPipeline.apply(schemaParsingMessage);
    }

}
