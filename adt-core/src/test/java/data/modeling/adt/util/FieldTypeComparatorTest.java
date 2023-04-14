package data.modeling.adt.util;
    import data.modeling.adt.abstraction.typedefs.FieldType;
    import data.modeling.adt.abstraction.typedefs.IntType;
    import data.modeling.adt.abstraction.typedefs.StringType;
    import org.junit.jupiter.api.Test;

    import java.util.List;

    import static org.junit.jupiter.api.Assertions.assertEquals;

public class FieldTypeComparatorTest {

    @Test
    public void testCompareFieldTypes() {
        FieldType fieldType1 = new FieldType("field1", new StringType());
        FieldType fieldType2 = new FieldType("field2", new IntType());

        AnyTypeComparator comparator = new AnyTypeComparator(new JsonPathTraversingContext(), fieldType1, fieldType2);
        List<AnyTypeComparator.Difference> diffs = comparator.findDiff();
        assertEquals(2, diffs.size());

        AnyTypeComparator.Difference diff1 = diffs.get(0);
        assertEquals("field name mismatch", diff1.getMessage());
        assertEquals("/field1", diff1.getJsonPointer());
        assertEquals("field1", diff1.getExpected());
        assertEquals("field2", diff1.getActual());

        AnyTypeComparator.Difference diff2 = diffs.get(1);
        assertEquals("type is different", diff2.getMessage());
        assertEquals("/field1", diff2.getJsonPointer());
        assertEquals("StringType", diff2.getExpected());
        assertEquals("IntType", diff2.getActual());
    }
}

