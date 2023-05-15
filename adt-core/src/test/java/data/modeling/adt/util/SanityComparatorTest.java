package data.modeling.adt.util;

import data.modeling.adt.abstraction.compatibility.CompatibilityPolicy;
import data.modeling.adt.compatibility.AnyTypeComparator;
import data.modeling.adt.compatibility.Difference;
import data.modeling.adt.compatibility.DifferenceTypes;
import data.modeling.adt.compatibility.policies.BackwardCompatibilityPolicy;
import data.modeling.adt.compatibility.policies.ForwardCompatibilityPolicy;
import data.modeling.adt.compatibility.policies.FullCompatibilityPolicy;
import data.modeling.adt.typedefs.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class SanityComparatorTest {
    CompatibilityPolicy backwardCompatibilityPolicy = new BackwardCompatibilityPolicy();
    CompatibilityPolicy forwardCompatibilityPolicy = new ForwardCompatibilityPolicy();
    CompatibilityPolicy fullCompatibilityPolicy = new FullCompatibilityPolicy();

    @Test
    public void testCompare() {
        NamedType namedType1 = new NamedType("person", new ProductType(new HashSet<>()));
        NamedType namedType2 = new NamedType("person", new ProductType(new HashSet<>()));

        // Same objects
        assertEquals(0, AnyTypeComparator.compare(namedType1, namedType1).size());

        // Different objects with same values
        assertEquals(0, AnyTypeComparator.compare(namedType1, namedType2).size());

        // Different named types
        FieldType foo = new FieldType("foo", new StringType());
        FieldType bar = new FieldType("bar", new IntType());
        namedType2 = new NamedType("person", ProductType.of(
                foo,
                bar
        ));

        List<Difference> diffs = AnyTypeComparator.compare(namedType1, namedType2);
        assertEquals(2, diffs.size());

        Difference diff1 = diffs.get(0);
        assertEquals(DifferenceTypes.FieldAddedOptional, diff1.differenceType());
        assertEquals("/", diff1.jsonPointer());
        assertEquals(null, diff1.expected());
        assertEquals(foo, diff1.actual());

        Difference diff2 = diffs.get(1);
        assertEquals(DifferenceTypes.FieldAddedOptional, diff2.differenceType());
        assertEquals("/", diff2.jsonPointer());
        assertEquals(null, diff2.expected());
        assertEquals(bar, diff2.actual());

        assertTrue(backwardCompatibilityPolicy.isCompatible(diffs));
        assertTrue(forwardCompatibilityPolicy.isCompatible(diffs));
        assertTrue(fullCompatibilityPolicy.isCompatible(diffs));
    }

    @Test
    void testCompareNamedTypeDifferentName() {
        NamedType nt1 = new NamedType("foo", new ListType(new IntType()));
        NamedType nt2 = new NamedType("bar", new ListType(new IntType()));
        List<Difference> diffs = AnyTypeComparator.compare(nt1, nt2);
        Assertions.assertEquals(1, diffs.size());
        Difference diff = diffs.get(0);
        Assertions.assertEquals(DifferenceTypes.LabelChanged, diff.differenceType());
        Assertions.assertEquals("/", diff.jsonPointer());
        Assertions.assertEquals("foo", diff.expected());
        Assertions.assertEquals("bar", diff.actual());

        assertFalse(backwardCompatibilityPolicy.isCompatible(diffs));
        assertFalse(forwardCompatibilityPolicy.isCompatible(diffs));
        assertFalse(fullCompatibilityPolicy.isCompatible(diffs));
    }

    @Test
    void testCompareNamedTypeDifferentType() {
        NamedType nt1 = new NamedType("foo", new SetType(new IntType()));
        NamedType nt2 = new NamedType("foo", new StringType());
        List<Difference> diffs = AnyTypeComparator.compare(nt1, nt2);
        Assertions.assertEquals(1, diffs.size());
        Difference diff = diffs.get(0);
        Assertions.assertEquals(DifferenceTypes.TypeChanged, diff.differenceType());
        Assertions.assertEquals("/", diff.jsonPointer());
        Assertions.assertEquals("SetType", diff.expected());
        Assertions.assertEquals("StringType", diff.actual());

        assertFalse(backwardCompatibilityPolicy.isCompatible(diffs));
        assertFalse(forwardCompatibilityPolicy.isCompatible(diffs));
        assertFalse(fullCompatibilityPolicy.isCompatible(diffs));
    }

    @Test
    void testCompareSumTypeUnionType() {
        Set<AnyType> types1 = new HashSet<>();
        types1.add(new NullValueType(new IntType()));
        UnionType ut1 = new UnionType(types1);
        Set<AnyType> types2 = new HashSet<>();
        types2.add(new NullValueType(new IntType()));
        UnionType ut2 = new UnionType(types2);
        List<Difference> diffs = AnyTypeComparator.compare(ut1, ut2);
        Assertions.assertEquals(0, diffs.size());

        assertTrue(backwardCompatibilityPolicy.isCompatible(diffs));
        assertTrue(forwardCompatibilityPolicy.isCompatible(diffs));
        assertTrue(fullCompatibilityPolicy.isCompatible(diffs));
    }

    @Test
    void testCompareSumTypeEnumTypeSameBaseType() {
        Set<ConstantPrimitiveType> values1 = new HashSet<>();
        values1.add(StringType.constantOf("foo"));
        EnumType et1 = new EnumType(new StringType(), values1);
        Set<ConstantPrimitiveType> values2 = new HashSet<>();
        values2.add(StringType.constantOf("foo"));
        EnumType et2 = new EnumType(new StringType(), values2);
        List<Difference> diffs = AnyTypeComparator.compare(et1, et2);
        Assertions.assertEquals(0, diffs.size());

        assertTrue(backwardCompatibilityPolicy.isCompatible(diffs));
        assertTrue(forwardCompatibilityPolicy.isCompatible(diffs));
        assertTrue(fullCompatibilityPolicy.isCompatible(diffs));
    }
    @Test
    void testCompareSumTypeEnumTypeDifferentBaseType() {
        Set<ConstantPrimitiveType> values1 = new HashSet<>();
        values1.add(StringType.constantOf("foo"));
        EnumType et1 = new EnumType(new StringType(), values1);
        Set<ConstantPrimitiveType> values2 = new HashSet<>();
        values2.add(IntType.constantOf(1));
        EnumType et2 = new EnumType(new IntType(), values2);
        List<Difference> diffs = AnyTypeComparator.compare(et1, et2);
        Assertions.assertEquals(1, diffs.size());
        Difference diff = diffs.get(0);
        Assertions.assertEquals(DifferenceTypes.TypeChanged, diff.differenceType());
        Assertions.assertEquals("/", diff.jsonPointer());
        Assertions.assertEquals("StringType", diff.expected());
        Assertions.assertEquals("IntType", diff.actual());

        assertFalse(backwardCompatibilityPolicy.isCompatible(diffs));
        assertFalse(forwardCompatibilityPolicy.isCompatible(diffs));
        assertFalse(fullCompatibilityPolicy.isCompatible(diffs));
    }
}
