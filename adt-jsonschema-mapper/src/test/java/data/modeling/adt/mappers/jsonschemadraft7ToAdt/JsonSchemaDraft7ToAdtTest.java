package data.modeling.adt.mappers.jsonschemadraft7ToAdt;

import data.modeling.adt.SchemaContext;
import data.modeling.adt.exceptions.AdtException;
import data.modeling.adt.mappers.fixtures.*;
import data.modeling.adt.mappers.jsonschemadraft7ToAdt.fixtures.AllOfFixtureData;
import data.modeling.adt.mappers.jsonschemadraft7ToAdt.fixtures.SanityFixtureData;
import data.modeling.adt.typedefs.ComplexType;
import data.modeling.adt.typedefs.Definition;
import data.modeling.adt.typedefs.TypeDefinition;
import data.modeling.adt.compatibility.AnyTypeComparator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(Parameterized.class)
public class JsonSchemaDraft7ToAdtTest {

    @Parameterized.Parameters(name = "{index}: {0}")
    public static Collection<ToAdtFixtureData<String>> data() {
        return Stream.of(
               new SanityFixtureData(),
               new AllOfFixtureData()
        ).collect(Collectors.toList());
    }

    private final ToAdtFixtureData<String> fixtureData;


    public JsonSchemaDraft7ToAdtTest(ToAdtFixtureData<String> fixtureData) {
        this.fixtureData = fixtureData;
    }

    @Test
    public void testMethod() throws AdtException {
        JsonSchemaDraft7ToAdt mapper = new JsonSchemaDraft7ToAdt(fixtureData.getInputSchema());
        SchemaContext expectedSchemaContext = fixtureData.getExpectedSchemaContext();
        Definition<ComplexType> expectedTypeDefinition = expectedSchemaContext.getNamedType(fixtureData.getExpectedNamedTypeName());

        // Convert JSON schema string to stream of ADT named types
        var namedTypes = mapper.stream();
        SchemaContext actualSchemaContext = new SchemaContext(namedTypes);

        // Verify that the expected named type was generated
        assertEquals(fixtureData.expectedSchemaContextSize(), actualSchemaContext.size());

        var namedType = actualSchemaContext.getNamedType(fixtureData.getExpectedNamedTypeName());

        AnyTypeComparator.compare(expectedTypeDefinition, namedType).forEach(System.out::println);
        assertEquals(fixtureData.expectedDiffSize(), AnyTypeComparator.compare(expectedTypeDefinition, namedType).size());
        assertEquals(expectedTypeDefinition, namedType);
    }
}
