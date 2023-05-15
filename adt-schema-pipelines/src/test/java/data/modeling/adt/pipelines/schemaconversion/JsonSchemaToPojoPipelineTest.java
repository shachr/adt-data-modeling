package data.modeling.adt.pipelines.schemaconversion;

import data.modeling.adt.SchemaContext;
import data.modeling.adt.compatibility.AnyTypeComparator;
import data.modeling.adt.compatibility.Difference;
import data.modeling.adt.pipelines.schemaconvertion.converters.AdtToPojo;
import data.modeling.adt.typedefs.*;
import org.junit.Test;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

public class JsonSchemaToPojoPipelineTest {
    NamedType expectedNamedType = NamedType.builder("foo", ProductType.of(
            FieldType.builder("id", new IntType()).build(),
            FieldType.builder("age", new IntType()).build(),
            FieldType.builder("name", new StringType()).build(),
            FieldType.builder("creditCard", new StringType()).build(),
            FieldType.builder("country", new StringType()).build(),
            FieldType.builder("state", new StringType()).build()
    )).build();

    @Test
    public void testAdtToPojo(){
        SchemaContext  schemaContext = new SchemaContext();
        NamedType base = NamedType.builder("base", ProductType.of(
                new FieldType("id", new StringType()),
                new FieldType("age", new IntType())
        )).build();

        NamedType foo = NamedType.builder("foo", ProductType.of(
                Stream.of(new ReferenceNamedType("base")).collect(Collectors.toCollection(LinkedHashSet::new)),
                Stream.of(
                    FieldType.builder("id", new IntType()).build(),
                    FieldType.builder("name", new StringType()).build(),
                    new FieldAdditionalTypes(ProductType.of(
                            FieldType.builder("creditCard", new StringType()).build(),
                            FieldType.builder("name", new StringType()).build()
                    )),
                    new FieldAdditionalTypes(
                            ProductType.of(FieldType.builder("creditCard", new StringType()).build())),
                    new FieldAdditionalTypes(
                            ProductType.of(FieldType.builder("country", new StringType()).build())),
                    new FieldAdditionalTypes(
                            ProductType.of(FieldType.builder("state", new StringType()).build()))
                ))
        ).build();

        schemaContext.registerNamedType(base);
        schemaContext.registerNamedType(foo);
        new AdtToPojo(schemaContext).apply();

        NamedType fooResolved = schemaContext.getNamedType("foo");
        List<Difference> diffs =  AnyTypeComparator.compare(expectedNamedType, fooResolved);
        assertEquals(0, diffs.size());
        assertEquals(expectedNamedType, fooResolved);
    }
}
