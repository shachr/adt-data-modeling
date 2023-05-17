package data.modeling.adt;

import data.modeling.adt.exceptions.AdtException;
import data.modeling.adt.mappers.javabeansFromAdt.artifacts.JavaFile;
import data.modeling.adt.mappers.javabeansFromAdt.util.JavaJarUtil;
import data.modeling.adt.messages.*;
import data.modeling.adt.pipelines.schemaconvertion.SchemaConversionPipeline;
import data.modeling.adt.pipelines.schemaconvertion.converters.AdtToIDL;
import data.modeling.adt.pipelines.schemaconvertion.converters.JavaConverter;
import data.modeling.adt.pipelines.schemaconvertion.converters.JsonSchemaConverter;
import data.modeling.adt.pipelines.schemaconvertion.converters.JsonSchemaFile;
import data.modeling.adt.pipelines.schemaparsing.SchemaParsingPipeline;
import data.modeling.adt.pipelines.schemaparsing.parsers.JsonSchemaTypeParser;
import data.modeling.adt.pipelines.schemavalidation.SchemaValidationPipeline;
import data.modeling.adt.pipelines.schemavalidation.validators.JsonSchemaValidator;
import data.modeling.adt.util.FSUtil;
import data.modeling.adt.util.LambdaExceptionUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.CompletableFuture;

public class Main {
    final static String APPLICATION_SCHEMA_JSON = "application/schema+json";
    final static String BINARY_JAVA = "binary/java";

    public static final String SCHEMA_STRING = "{\n" +
            "  \"$id\": \"com.shachar.foo\",\n" +
            "  \"$schema\": \"http://json-schema.org/draft-07/schema#\",\n" +
            "  \"type\": \"object\",\n" +
            "  \"definitions\": {\n" +
            "    \"ColorEnum\": {\n" +
            "      \"description\": \"the color\",\n" +
            "      \"x-data-handling-classification\": \"PUBLIC\",\n" +
            "      \"type\": \"string\",\n" +
            "      \"enum\": [\"red\", \"blue\", \"green\"],\n" +
            "      \"default\": \"blue\"\n" +
            "    },\n" +
            "    \"Entity\": {\n" +
            "      \"type\": \"object\",\n" +
            "      \"properties\": {\n" +
            "        \"id\": {\n" +
            "          \"description\": \"the identifier of foo\",\n" +
            "          \"type\": \"integer\"\n" +
            "        }\n" +
            "      }\n" +
            "    }\n" +
            "  },\n" +
            "  \"allOf\": [\n" +
            "    { \"$ref\": \"#/definitions/Entity\" },\n" +
            "    {\n" +
            "      \"type\": \"object\",\n" +
            "      \"properties\": {\n" +
            "        \"name\": {\n" +
            "          \"x-data-handling-classification\": \"PUBLIC\",\n" +
            "          \"type\": \"string\"\n" +
            "        },\n" +
            "        \"color\": {\n" +
            "          \"$ref\": \"#/definitions/ColorEnum\"\n" +
            "        }\n" +
            "      },\n" +
            "      \"required\": [\n" +
            "        \"id\",\n" +
            "        \"name\"\n" +
            "      ]\n" +
            "    }\n" +
            "  ]\n" +
            "}";


    static SchemaParsingPipeline schemaParsingPipeline = new SchemaParsingPipeline();
    static SchemaConversionPipeline schemaConversionPipeline = new SchemaConversionPipeline();
    static SchemaValidationPipeline schemaValidationPipeline = new SchemaValidationPipeline();

    public static void main(String[] args) throws Exception {

        schemaParsingPipeline.registerParser(new JsonSchemaTypeParser());
        schemaConversionPipeline.registerConverter(new JsonSchemaConverter());
        schemaConversionPipeline.registerConverter(new JavaConverter());
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
        SchemaContext schemaContext = schemaValidationMessage.getSchemaContext();
        SchemaConvertedMessage schemaConvertedMessage = schemaConversionPipeline.apply(
                new SchemaConvertionMessage(APPLICATION_SCHEMA_JSON, "com.shachar.foo", schemaContext));

        System.out.println(schemaConvertedMessage.<JsonSchemaFile>getSchemaArtifactOf());


        // convert to map of java file/java content
        schemaContext.setName("com.shachar");
        schemaContext = new AdtToIDL(schemaContext).apply();
        schemaConvertedMessage = schemaConversionPipeline.apply(
                new SchemaConvertionMessage(BINARY_JAVA, "com.shachar.foo", schemaContext));

        schemaConvertedMessage.<JavaFile>getSchemaArtifactOf().forEach(javaFile -> {

            System.out.println(javaFile.fileName());
            System.out.println(javaFile.content());
            System.out.println("---");
        });
//        javaCodeGen(schemaContext);
    }

    private static void javaCodeGen(SchemaContext schemaContext) throws Exception {
        SchemaConvertedMessage schemaConvertedMessage;
        // convert to map of java file/java content
        schemaConvertedMessage = schemaConversionPipeline.apply(
                new SchemaConvertionMessage(BINARY_JAVA, "com.shachar.foo", schemaContext));


        // remove any existing content from the target path
        Path targetPath = Paths.get("./");
        FSUtil.recursivelyForceDeleteDirectory(targetPath);

        // create .java files from map
        try {

            schemaConvertedMessage.<JavaFile>getSchemaArtifactOf().forEach(LambdaExceptionUtil.consumer(entry -> {
                Path filePath = Paths.get(targetPath.toString(), entry.fileName().replace(".", "/") + ".java");
                Files.createDirectories(filePath.getParent());
                Files.writeString(filePath, entry.getValue(), StandardOpenOption.CREATE, StandardOpenOption.WRITE);

            }));
        } catch(Exception e){
            throw new AdtException("failed to generate .java files", e);
        }


        // compile and jar
        try{
            JavaJarUtil javaJarUtil = new JavaJarUtil();
            javaJarUtil.toJar(targetPath, schemaContext.getName());
        } catch (IOException | InterruptedException e) {
            throw new AdtException("failed to compile and create a jar", e);
        }

        // todo: load jar and check instantiate, then validate
    }

    private static SchemaValidationMessage validateSchema(SchemaParsedMessage schemaParsedMessage) throws Exception {
        SchemaValidationMessage schemaValidationMessage = schemaValidationPipeline.apply(schemaParsedMessage);
        schemaValidationMessage.getValidationErrors().forEach(validationError -> {
            System.out.println(validationError.getJsonPointer() + ", "+ validationError.getErrorCode() + ", " + validationError.getMessage());
        });
        return schemaValidationMessage;
    }

    public static SchemaParsedMessage parseSchema() throws Exception {
        SchemaParsingMessage schemaParsingMessage = new SchemaParsingMessage(APPLICATION_SCHEMA_JSON, SCHEMA_STRING);
        return schemaParsingPipeline.apply(schemaParsingMessage);
    }
}