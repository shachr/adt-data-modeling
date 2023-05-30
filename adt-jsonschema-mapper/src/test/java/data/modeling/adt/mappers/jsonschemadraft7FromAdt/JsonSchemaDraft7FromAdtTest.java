package data.modeling.adt.mappers.jsonschemadraft7FromAdt;

import data.modeling.adt.SchemaContext;
import data.modeling.adt.mappers.MapAssert;
import data.modeling.adt.mappers.fixtures.FromAdtFixtureData;
import data.modeling.adt.mappers.jsonschemadraft7FromAdt.fixtures.AllOfFixtureData;
import data.modeling.adt.mappers.jsonschemadraft7FromAdt.fixtures.SanityFixtureData;
import data.modeling.adt.typedefs.ComplexType;
import data.modeling.adt.typedefs.Definition;
import data.modeling.adt.typedefs.TypeDefinition;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@RunWith(Parameterized.class)
public class JsonSchemaDraft7FromAdtTest {

    @Parameterized.Parameters(name = "{index}: {0}")
    public static Collection<FromAdtFixtureData<Map<String, Object>>> data() {
        return Stream.of(
            new SanityFixtureData(),
            new AllOfFixtureData()
        ).collect(Collectors.toList());
    }

    private final FromAdtFixtureData<Map<String, Object>> fixtureData;

    public JsonSchemaDraft7FromAdtTest(FromAdtFixtureData<Map<String, Object>> fixtureData){
        this.fixtureData = fixtureData;
    }

    @Test
    public void testMethod() throws Exception {
        SchemaContext schemaContext = fixtureData.getInputSchemaContext();
        TypeDefinition typeDefinition = (TypeDefinition)schemaContext.getNamedType(fixtureData.getInputNamedTypeName());

        Map<String, Object> expectedMap = fixtureData.getExpectedSchema();
        JsonSchemaDraft7FromAdt fromAdt = new JsonSchemaDraft7FromAdt(typeDefinition, schemaContext);
        Map<String, Object> actualmap = fromAdt.stream().findFirst().get();
        MapAssert.assertMapsEqualIgnoreOrder(expectedMap, actualmap);
    }
}
