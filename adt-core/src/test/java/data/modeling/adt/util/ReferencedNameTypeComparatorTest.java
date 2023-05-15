package data.modeling.adt.util;

import data.modeling.adt.compatibility.AnyTypeComparator;
import data.modeling.adt.compatibility.Difference;
import data.modeling.adt.compatibility.DifferenceTypes;
import data.modeling.adt.typedefs.ReferenceNamedType;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class ReferencedNameTypeComparatorTest {
    @Test
    public void testCompareReferenceNamedType_sameObject() {
        ReferenceNamedType obj1 = new ReferenceNamedType("ref1");
        Assert.assertEquals(0, AnyTypeComparator.compare(obj1, obj1).size());
    }

    @Test
    public void testCompareReferenceNamedType_differentReferenceName() {
        ReferenceNamedType obj1 = new ReferenceNamedType("ref1");
        ReferenceNamedType obj2 = new ReferenceNamedType("ref2");
        List<Difference> diffs = AnyTypeComparator.compare(obj1, obj2);
        Assert.assertEquals(1, diffs.size());
        Difference diff = diffs.get(0);
        Assert.assertEquals(DifferenceTypes.ReferenceChanged, diff.differenceType());
        Assert.assertEquals("/", diff.jsonPointer());
        Assert.assertEquals("ref1", diff.expected());
        Assert.assertEquals("ref2", diff.actual());
    }

    @Test
    public void testCompareReferenceNamedType_sameReferenceName() {
        ReferenceNamedType obj1 = new ReferenceNamedType("ref1");
        ReferenceNamedType obj2 = new ReferenceNamedType("ref1");
        Assert.assertEquals(0, AnyTypeComparator.compare(obj1, obj2).size());
    }
}
