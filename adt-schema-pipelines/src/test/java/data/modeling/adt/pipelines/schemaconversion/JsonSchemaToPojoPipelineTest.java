package data.modeling.adt.pipelines.schemaconversion;

import data.modeling.adt.SchemaContext;
import data.modeling.adt.compatibility.AnyTypeComparator;
import data.modeling.adt.compatibility.Difference;
import data.modeling.adt.pipelines.schemaconvertion.converters.SchemaCompositionToAdt;
import data.modeling.adt.typedefs.*;
import org.junit.Test;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

public class JsonSchemaToPojoPipelineTest {
    TypeDefinition expectedTypeDefinition = TypeDefinition.builder("foo", ProductType.of(
            Stream.of(new ReferencedDefinition("base")).collect(Collectors.toCollection(LinkedHashSet::new)),
            Stream.of(
                FieldDefinition.builder("id", new Int32Type()).build(),
                FieldDefinition.builder("name", new StringType()).build(),
                FieldDefinition.builder("creditCard", new StringType()).build(),
                FieldDefinition.builder("country", new StringType()).build(),
                FieldDefinition.builder("state", new StringType()).build()
            )
    )).build();

    @Test
    public void testAdtToPojo(){
        SchemaContext  schemaContext = new SchemaContext();
        TypeDefinition base = TypeDefinition.builder("base", ProductType.of(
                new FieldDefinition("id", new StringType()),
                new FieldDefinition("age", new Int32Type())
        )).build();

        TypeDefinition foo = TypeDefinition.builder("foo", AllOfType.of(
                new ReferencedDefinition("base"),
                ProductType.of(Stream.of(
                    FieldDefinition.builder("id", new Int32Type()).build(),
                    FieldDefinition.builder("name", new StringType()).build(),
                    new FieldAdditionalDefinition(ProductType.of(
                            FieldDefinition.builder("creditCard", new StringType()).build(),
                            FieldDefinition.builder("name", new StringType()).build()
                    )),
                    new FieldAdditionalDefinition(
                            ProductType.of(FieldDefinition.builder("creditCard", new StringType()).build())),
                    new FieldAdditionalDefinition(
                            ProductType.of(FieldDefinition.builder("country", new StringType()).build())),
                    new FieldAdditionalDefinition(
                            ProductType.of(FieldDefinition.builder("state", new StringType()).build()))
                ))
        )).build();

        schemaContext.registerNamedType(base);
        schemaContext.registerNamedType(foo);
        new SchemaCompositionToAdt(schemaContext).apply();

        Definition<ComplexType> fooResolved = schemaContext.getNamedType("foo");
        List<Difference> diffs =  AnyTypeComparator.compare(expectedTypeDefinition, fooResolved);
        diffs.forEach(System.out::println);
        assertEquals(0, diffs.size());
        assertEquals(expectedTypeDefinition, fooResolved);
    }
}
