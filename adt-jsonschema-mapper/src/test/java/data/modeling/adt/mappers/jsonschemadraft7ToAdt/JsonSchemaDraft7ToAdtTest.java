package data.modeling.adt.mappers.jsonschemadraft7ToAdt;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import data.modeling.adt.SchemaContext;
import data.modeling.adt.abstraction.annotations.Annotation;
import data.modeling.adt.annotations.golden.DataCompliance;
import data.modeling.adt.annotations.golden.DataHandlingClassification;
import data.modeling.adt.annotations.golden.IsPersonalData;
import data.modeling.adt.enums.DataCompliances;
import data.modeling.adt.enums.DataHandlingClassifications;
import data.modeling.adt.exceptions.AdtException;
import data.modeling.adt.mappers.jsonschemadraft7FromAdt.JsonSchemaDraft7FromAdt;
import data.modeling.adt.mappers.jsonschemadraft7ToAdt.annotations.JsonSchemaAnnotation;
import data.modeling.adt.mappers.jsonschemadraft7ToAdt.fixtures.JsonSchemaAllOfTestFixture;
import data.modeling.adt.mappers.jsonschemadraft7ToAdt.fixtures.JsonSchemaSanityTestFixture;
import data.modeling.adt.typedefs.*;
import data.modeling.adt.util.AnyTypeComparator;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonSchemaDraft7ToAdtTest {

    @Test
    public void testJsonSchemaDraft7ToAdtSanity() throws JsonProcessingException, AdtException {
        data.modeling.adt.mappers.jsonschemadraft7ToAdt.JsonSchemaDraft7ToAdt mapper = new data.modeling.adt.mappers.jsonschemadraft7ToAdt.JsonSchemaDraft7ToAdt(JsonSchemaSanityTestFixture.JSON_SCHEMA_STRING);
        String schemaNamespace = "http://example.com/product.schema.json";

        NamedType expectedNamedType = NamedType.builder(schemaNamespace, ProductType.of(
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

        expectedNamedType.getAnnotations().add(new JsonSchemaAnnotation("$schema", "http://json-schema.org/draft-07/schema#"));
        expectedNamedType.getAnnotations().add(new JsonSchemaAnnotation("$id", schemaNamespace));
        expectedNamedType.getAnnotations().add(new JsonSchemaAnnotation("title", "Product"));
        expectedNamedType.getAnnotations().add(new JsonSchemaAnnotation("description", "A product from Acme's catalog"));

        // Convert JSON schema string to stream of ADT named types
        var namedTypes = mapper.stream().toList();

        // Verify that the expected named type was generated
        assertEquals(1, namedTypes.size());
        var namedType = namedTypes.get(0);

        AnyTypeComparator.compare(expectedNamedType, namedType).forEach(System.out::println);
        assertEquals(0, AnyTypeComparator.compare(expectedNamedType, namedType).size());
        assertEquals(expectedNamedType, namedType);
    }

    @Test
    public void testJsonSchemaDraft7ToAdtAllOf() throws JsonProcessingException, AdtException {
        data.modeling.adt.mappers.jsonschemadraft7ToAdt.JsonSchemaDraft7ToAdt mapper = new data.modeling.adt.mappers.jsonschemadraft7ToAdt.JsonSchemaDraft7ToAdt(JsonSchemaAllOfTestFixture.JSON_SCHEMA_STRING);
        String schemaNamespace = "http://example.com/allof.schema.json";

        SchemaContext expectedSchemaContext = new SchemaContext();
        NamedType myDefinition = NamedType.builder("#/definitions/MyDefinition", ProductType.of(
                FieldType.builder("id", new StringType()).build(),
                FieldType.builder("name", new StringType()).build()
        )).build();
        expectedSchemaContext.registerNamedType(myDefinition);
        NamedType expectedNamedType = NamedType.builder(schemaNamespace, ProductType.of(
                Stream.of(new ReferenceNamedType("#/definitions/MyDefinition")).collect(Collectors.toCollection(LinkedHashSet::new)),
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

        // Convert JSON schema string to stream of ADT named types
        var namedTypes = mapper.stream();
        SchemaContext schemaContext = SchemaContext.of(namedTypes);

        // Verify that the expected named type was generated
        assertEquals(2, schemaContext.size());
        var namedType = schemaContext.getNamedType(schemaNamespace);

        // Compare
        AnyTypeComparator.compare(expectedNamedType, namedType).forEach(System.out::println);
        assertEquals(0, AnyTypeComparator.compare(expectedNamedType, namedType).size());
        assertEquals(expectedNamedType, namedType);
    }

    public static String mapToJson(Map<String, Object> map) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter writer = objectMapper.writerWithDefaultPrettyPrinter();
        return writer.writeValueAsString(map);

    }

}
