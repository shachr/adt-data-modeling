package data.modeling.adt.mappers.jsonschemadraft7;

import com.fasterxml.jackson.core.JsonProcessingException;
import data.modeling.adt.SchemaContext;
import data.modeling.adt.abstraction.exceptions.AdtException;
import data.modeling.adt.abstraction.typedefs.*;
import data.modeling.adt.mappers.jsonschemadraft7.fixtures.JsonSchemaAllOfTestFixture;
import data.modeling.adt.mappers.jsonschemadraft7.fixtures.JsonSchemaSanityTestFixture;
import data.modeling.adt.util.AnyTypeComparator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonSchemaDraft7ToAdtTest {

    @Test
    public void testJsonSchemaDraft7ToAdtSanity() throws JsonProcessingException, AdtException {
        data.modeling.adt.mappers.jsonschemadraft7.JsonSchemaDraft7ToAdt mapper = new data.modeling.adt.mappers.jsonschemadraft7.JsonSchemaDraft7ToAdt(JsonSchemaSanityTestFixture.JSON_SCHEMA_STRING);
        String schemaNamespace = "http://example.com/product.schema.json";

        NamedType expectedNamedType = NamedType.builder(schemaNamespace, ProductType.of(
                FieldType.builder("productId", new IntType()).build(0),
                FieldType.builder("productName", new StringType()).build(1),
                FieldType.builder("price", new DoubleType()).build(2),
                FieldType.builder("tags", new ListType(new StringType())).build(3),
                FieldType.builder("dimensions", ProductType.of(
                        FieldType.builder("length", new DoubleType()).build(0),
                        FieldType.builder("width", new DoubleType()).build(1),
                        FieldType.builder("height", new DoubleType()).build(2)
                )).build(4),
                FieldType.builder("color", EnumType.of(
                        new StringType(),
                        StringType.constantOf("red"),
                        StringType.constantOf("green"),
                        StringType.constantOf("blue")
                )).build(5),
                FieldType.builder("isAvailable", new BoolType()).build(6)
        )).build();

        // Convert JSON schema string to stream of ADT named types
        var namedTypes = mapper.stream().toList();

        // Verify that the expected named type was generated
        assertEquals(1, namedTypes.size());
        var namedType = namedTypes.get(0);

        assertEquals(0, AnyTypeComparator.compare(expectedNamedType, namedType).size());
        assertEquals(expectedNamedType, namedType);
    }

    @Test
    public void testJsonSchemaDraft7ToAdtAllOf() throws JsonProcessingException, AdtException {
        data.modeling.adt.mappers.jsonschemadraft7.JsonSchemaDraft7ToAdt mapper = new data.modeling.adt.mappers.jsonschemadraft7.JsonSchemaDraft7ToAdt(JsonSchemaAllOfTestFixture.JSON_SCHEMA_STRING);
        String schemaNamespace = "http://example.com/allof.schema.json";

        NamedType expectedNamedType = NamedType.builder(schemaNamespace, ProductType.of(
                FieldType.builder("id", new StringType()).build(0),
                FieldType.builder("name", new StringType()).build(1),
                FieldType.builder("prop1", new StringType()).build(2),
                FieldType.builder("prop2", new StringType()).build(3),
                FieldType.builder("prop3", new BoolType()).build(4),
                FieldType.builder("prop4", new ListType(new StringType())).build(5)

        )).build();

//        SchemaContext schemaContext = new SchemaContext();
//        schemaContext.registerNamedType(contextNamedType);
//        schemaContext.registerNamedType(expectedNamedType);

        // Convert JSON schema string to stream of ADT named types
        var namedTypes = mapper.stream();
        SchemaContext schemaContext = SchemaContext.of(namedTypes);

        // Verify that the expected named type was generated
        assertEquals(2, schemaContext.size());
        var namedType = schemaContext.getNamedType(schemaNamespace);
        ProductType productType = (ProductType) namedType.getType();
        productType.resolveAllFields(schemaContext).stream()
                .forEach(fieldType -> System.out.println(fieldType.getIndex() + " : " + fieldType.getName() + " : "+fieldType.getType().toString()));

        AnyTypeComparator.compare(expectedNamedType, namedType).forEach(System.out::println);
        assertEquals(0, AnyTypeComparator.compare(expectedNamedType, namedType).size());
        assertEquals(expectedNamedType, namedType);
    }
}
