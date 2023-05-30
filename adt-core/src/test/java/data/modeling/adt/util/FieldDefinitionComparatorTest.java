package data.modeling.adt.util;
    import data.modeling.adt.compatibility.AnyTypeComparator;
    import data.modeling.adt.compatibility.Difference;
    import data.modeling.adt.compatibility.DifferenceTypes;
    import data.modeling.adt.typedefs.FieldDefinition;
    import data.modeling.adt.typedefs.Int32Type;
    import data.modeling.adt.typedefs.ProductType;
    import data.modeling.adt.typedefs.StringType;
    import org.junit.jupiter.api.Test;

    import java.util.List;

    import static org.junit.jupiter.api.Assertions.assertEquals;

public class FieldDefinitionComparatorTest {

    @Test
    public void testCompareFieldTypes() {
        FieldDefinition fieldDefinition1 = new FieldDefinition("field1", new StringType());
        FieldDefinition fieldDefinition2 = new FieldDefinition("field2", new Int32Type());

        List<Difference> diffs = AnyTypeComparator.compare(ProductType.of(fieldDefinition1), ProductType.of(fieldDefinition2));
        assertEquals(2, diffs.size());

        Difference diff1 = diffs.get(0);
        assertEquals(DifferenceTypes.FieldLabelChanged, diff1.differenceType());
        assertEquals("/field1/name", diff1.jsonPointer());
        assertEquals("field1", diff1.expected());
        assertEquals("field2", diff1.actual());

        Difference diff2 = diffs.get(1);
        assertEquals(DifferenceTypes.TypeChanged, diff2.differenceType());
        assertEquals("/field1", diff2.jsonPointer());
        assertEquals("StringType", diff2.expected());
        assertEquals("Int32Type", diff2.actual());
    }
}

