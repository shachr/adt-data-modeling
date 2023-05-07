package data.modeling.adt.util;

import data.modeling.adt.typedefs.ReferenceObjectType;
import org.junit.Assert;
import org.junit.Test;
public class ReferencedNameTypeComparatorTest {
    @Test
    public void testCompareReferenceNamedType_sameObject() {
        ReferenceObjectType obj1 = new ReferenceObjectType("ref1");
        AnyTypeComparator comparator = new AnyTypeComparator(obj1, obj1);
        Assert.assertEquals(0, comparator.findDiff().size());
    }

    @Test
    public void testCompareReferenceNamedType_differentReferenceName() {
        ReferenceObjectType obj1 = new ReferenceObjectType("ref1");
        ReferenceObjectType obj2 = new ReferenceObjectType("ref2");
        AnyTypeComparator comparator = new AnyTypeComparator(obj1, obj2);
        Assert.assertEquals(1, comparator.findDiff().size());
        AnyTypeComparator.Difference diff = comparator.findDiff().get(0);
        Assert.assertEquals("reference name mismatch", diff.getMessage());
        Assert.assertEquals("/", diff.getJsonPointer());
        Assert.assertEquals("ref1", diff.getExpected());
        Assert.assertEquals("ref2", diff.getActual());
    }

    @Test
    public void testCompareReferenceNamedType_sameReferenceName() {
        ReferenceObjectType obj1 = new ReferenceObjectType("ref1");
        ReferenceObjectType obj2 = new ReferenceObjectType("ref1");
        AnyTypeComparator comparator = new AnyTypeComparator(obj1, obj2);
        Assert.assertEquals(0, comparator.findDiff().size());
    }
}
