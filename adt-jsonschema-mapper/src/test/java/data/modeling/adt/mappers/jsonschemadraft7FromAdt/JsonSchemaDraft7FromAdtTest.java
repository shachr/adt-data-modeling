package data.modeling.adt.mappers.jsonschemadraft7FromAdt;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import data.modeling.adt.SchemaContext;
import data.modeling.adt.mappers.MapAssert;
import data.modeling.adt.mappers.fixtures.FromAdtFixtureData;
import data.modeling.adt.mappers.jsonschemadraft7FromAdt.fixtures.SanityFixtureData;
import data.modeling.adt.typedefs.NamedType;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static data.modeling.adt.util.StreamExtensions.toMap;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;


@RunWith(Parameterized.class)
public class JsonSchemaDraft7FromAdtTest {

    @Parameterized.Parameters(name = "{index}: {0}")
    public static Collection<FromAdtFixtureData<Map<String, Object>>> data() {
        return Stream.of(
            new SanityFixtureData()
        ).collect(Collectors.toList());
    }

    private FromAdtFixtureData<Map<String, Object>> fixtureData;

    public JsonSchemaDraft7FromAdtTest(FromAdtFixtureData<Map<String, Object>> fixtureData){
        this.fixtureData = fixtureData;
    }

    @Test
    public void testMethod() throws Exception {
        SchemaContext schemaContext = fixtureData.getInputSchemaContext();
        NamedType namedType = schemaContext.getNamedType(fixtureData.getInputNamedTypeName());

        Map<String, Object> expectedMap = fixtureData.getExpectedSchema();
        JsonSchemaDraft7FromAdt fromAdt = new JsonSchemaDraft7FromAdt(namedType, schemaContext);
        Map<String, Object> actualmap = toMap(fromAdt.stream());
        printJson(actualmap);

        MapAssert.assertMapsEqualIgnoreOrder(expectedMap, actualmap);
    }

    public static void printJson(Map<String, Object> map) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter writer = objectMapper.writerWithDefaultPrettyPrinter();
        System.out.println(writer.writeValueAsString(map));
    }
}
