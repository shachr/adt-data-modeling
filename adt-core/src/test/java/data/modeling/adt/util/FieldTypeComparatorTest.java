package data.modeling.adt.util;
    import data.modeling.adt.compatibility.AnyTypeComparator;
    import data.modeling.adt.compatibility.Difference;
    import data.modeling.adt.compatibility.DifferenceTypes;
    import data.modeling.adt.typedefs.FieldType;
    import data.modeling.adt.typedefs.IntType;
    import data.modeling.adt.typedefs.ProductType;
    import data.modeling.adt.typedefs.StringType;
    import org.junit.jupiter.api.Test;

    import java.util.List;
    import java.util.stream.Stream;

    import static org.junit.jupiter.api.Assertions.assertEquals;

public class FieldTypeComparatorTest {

    @Test
    public void testCompareFieldTypes() {
        FieldType fieldType1 = new FieldType("field1", new StringType());
        FieldType fieldType2 = new FieldType("field2", new IntType());

        List<Difference> diffs = AnyTypeComparator.compare(ProductType.of(fieldType1), ProductType.of(fieldType2));
        assertEquals(2, diffs.size());

        Difference diff1 = diffs.get(0);
        assertEquals(DifferenceTypes.FieldLabelChanged, diff1.differenceType());
        assertEquals("/field1", diff1.jsonPointer());
        assertEquals("field1", diff1.expected());
        assertEquals("field2", diff1.actual());

        Difference diff2 = diffs.get(1);
        assertEquals(DifferenceTypes.TypeChanged, diff2.differenceType());
        assertEquals("/field1", diff2.jsonPointer());
        assertEquals("StringType", diff2.expected());
        assertEquals("IntType", diff2.actual());
    }
}

