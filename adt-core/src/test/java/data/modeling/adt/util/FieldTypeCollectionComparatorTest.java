package data.modeling.adt.util;

import data.modeling.adt.compatibility.AnyTypeComparator;
import data.modeling.adt.compatibility.DifferenceTypes;
import data.modeling.adt.typedefs.FieldType;
import data.modeling.adt.typedefs.IntType;
import data.modeling.adt.typedefs.ProductType;
import data.modeling.adt.typedefs.StringType;
import data.modeling.adt.compatibility.Difference;
import org.junit.Test;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FieldTypeCollectionComparatorTest {

    @Test
    public void testCompareFieldTypeCollection() {
        // Create two sets of fields with different contents
        Set<FieldType> fields1 = new LinkedHashSet<>();
        fields1.add(new FieldType("field1", new IntType()));
        fields1.add(new FieldType("field2", new ProductType()));
        FieldType fieldType3 = new FieldType("field3", new IntType());
        fields1.add(fieldType3);

        Set<FieldType> fields2 = new LinkedHashSet<>();
        fields2.add(new FieldType("field1", new IntType()));
        fields2.add(new FieldType("field4", new StringType()));

        // Compare the two sets of fields
        List<Difference> diffs = AnyTypeComparator.compare(new ProductType(fields1), new ProductType(fields2));
        diffs.forEach(System.out::println);
        assertEquals(3, diffs.size());

        Difference diff1 = diffs.get(0);
        assertEquals("/field2", diff1.jsonPointer());
        assertEquals("field2", diff1.expected());
        assertEquals("field4", diff1.actual());
        assertTrue(diff1.differenceType().equals(DifferenceTypes.FieldLabelChanged));

        Difference diff2 = diffs.get(1);
        assertEquals("/field2", diff2.jsonPointer());
        assertEquals("ProductType", diff2.expected());
        assertEquals("StringType", diff2.actual());
        assertTrue(diff2.differenceType().equals(DifferenceTypes.TypeChanged));

        Difference diff3 = diffs.get(2);
        assertEquals("/field3", diff3.jsonPointer());
        assertEquals(fieldType3, diff3.expected());
        assertEquals(null, diff3.actual());
        assertTrue(diff3.differenceType().equals(DifferenceTypes.FieldRemovedOptional));
    }

}
