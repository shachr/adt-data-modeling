package data.modeling.adt.util;

import data.modeling.adt.abstraction.typedefs.FieldType;
import data.modeling.adt.abstraction.typedefs.IntType;
import data.modeling.adt.abstraction.typedefs.ProductType;
import data.modeling.adt.abstraction.typedefs.StringType;
import data.modeling.adt.util.AnyTypeComparator.Difference;
import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FieldTypeCollectionComparatorTest {

    @Test
    public void testCompareFieldTypeCollection() {
        // Create two sets of fields with different contents
        Set<FieldType> fields1 = new HashSet<>();
        fields1.add(new FieldType("field1", new IntType()));
        fields1.add(new FieldType("field2", new ProductType()));
        fields1.add(new FieldType("field3", new IntType()));

        Set<FieldType> fields2 = new HashSet<>();
        fields2.add(new FieldType("field1", new IntType()));
        fields2.add(new FieldType("field4", new StringType()));

        // Compare the two sets of fields
        AnyTypeComparator comparator = new AnyTypeComparator(new ProductType(fields1), new ProductType(fields2));
        List<Difference> diffs = comparator.findDiff();
        diffs.forEach(System.out::println);
        assertEquals(3, diffs.size());

        Difference diff1 = diffs.get(0);
        assertEquals("/field2", diff1.getJsonPointer());
        assertEquals("field2", diff1.getExpected());
        assertEquals("field4", diff1.getActual());
        assertTrue(diff1.getMessage().contains("field name mismatch"));

        Difference diff2 = diffs.get(1);
        assertEquals("/field2", diff2.getJsonPointer());
        assertEquals("ProductType", diff2.getExpected());
        assertEquals("StringType", diff2.getActual());
        assertTrue(diff2.getMessage().contains("type is different"));

        Difference diff3 = diffs.get(2);
        assertEquals("/field3", diff3.getJsonPointer());
        assertEquals("field3", diff3.getExpected());
        assertEquals(null, diff3.getActual());
        assertTrue(diff3.getMessage().contains("field not found: field3"));
    }

}
