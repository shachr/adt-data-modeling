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

import static org.junit.jupiter.api.Assertions.*;

public class SanityComparatorTest {
    CompatibilityPolicy backwardCompatibilityPolicy = new BackwardCompatibilityPolicy();
    CompatibilityPolicy forwardCompatibilityPolicy = new ForwardCompatibilityPolicy();
    CompatibilityPolicy fullCompatibilityPolicy = new FullCompatibilityPolicy();

    @Test
    public void testCompare() {
        TypeDefinition typeDefinition1 = new TypeDefinition("person", new ProductType(new HashSet<>()));
        TypeDefinition typeDefinition2 = new TypeDefinition("person", new ProductType(new HashSet<>()));

        // Same objects
        assertEquals(0, AnyTypeComparator.compare(typeDefinition1, typeDefinition1).size());

        // Different objects with same values
        assertEquals(0, AnyTypeComparator.compare(typeDefinition1, typeDefinition2).size());

        // Different named types
        FieldDefinition foo = new FieldDefinition("foo", new StringType());
        FieldDefinition bar = new FieldDefinition("bar", new Int32Type());
        typeDefinition2 = new TypeDefinition("person", ProductType.of(
                foo,
                bar
        ));

        List<Difference> diffs = AnyTypeComparator.compare(typeDefinition1, typeDefinition2);
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
        TypeDefinition nt1 = new TypeDefinition("foo", new ListType(new Int32Type()));
        TypeDefinition nt2 = new TypeDefinition("bar", new ListType(new Int32Type()));
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
        SetType nt1 = new SetType(new Int32Type());
        StringType nt2 = new StringType();
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
        types1.add(new NullValueType(new Int32Type()));
        UnionType ut1 = new UnionType(types1);
        Set<AnyType> types2 = new HashSet<>();
        types2.add(new NullValueType(new Int32Type()));
        UnionType ut2 = new UnionType(types2);
        List<Difference> diffs = AnyTypeComparator.compare(ut1, ut2);
        Assertions.assertEquals(0, diffs.size());

        assertTrue(backwardCompatibilityPolicy.isCompatible(diffs));
        assertTrue(forwardCompatibilityPolicy.isCompatible(diffs));
        assertTrue(fullCompatibilityPolicy.isCompatible(diffs));
    }

    @Test
    void testCompareSumTypeEnumTypeSameBaseType() {
        Set<EnumType.EnumItemType> values1 = new HashSet<>();
        values1.add(new EnumType.EnumItemType("foo", StringType.constantOf("foo")));
        EnumType et1 = new EnumType(new StringType(), values1);
        Set<EnumType.EnumItemType> values2 = new HashSet<>();
        values2.add(new EnumType.EnumItemType("foo", StringType.constantOf("foo")));
        EnumType et2 = new EnumType(new StringType(), values2);
        List<Difference> diffs = AnyTypeComparator.compare(et1, et2);
        Assertions.assertEquals(0, diffs.size());

        assertTrue(backwardCompatibilityPolicy.isCompatible(diffs));
        assertTrue(forwardCompatibilityPolicy.isCompatible(diffs));
        assertTrue(fullCompatibilityPolicy.isCompatible(diffs));
    }
    @Test
    void testCompareSumTypeEnumTypeDifferentBaseType() {
        Set<EnumType.EnumItemType> values1 = new HashSet<>();
        values1.add(new EnumType.EnumItemType("foo", StringType.constantOf("foo")));
        EnumType et1 = new EnumType(new StringType(), values1);
        Set<EnumType.EnumItemType> values2 = new HashSet<>();
        values2.add(new EnumType.EnumItemType("1", Int32Type.constantOf(1)));
        EnumType et2 = new EnumType(new Int32Type(), values2);
        List<Difference> diffs = AnyTypeComparator.compare(et1, et2);
        Assertions.assertEquals(1, diffs.size());
        Difference diff = diffs.get(0);
        Assertions.assertEquals(DifferenceTypes.TypeChanged, diff.differenceType());
        Assertions.assertEquals("/", diff.jsonPointer());
        Assertions.assertEquals("StringType", diff.expected());
        Assertions.assertEquals("Int32Type", diff.actual());

        assertFalse(backwardCompatibilityPolicy.isCompatible(diffs));
        assertFalse(forwardCompatibilityPolicy.isCompatible(diffs));
        assertFalse(fullCompatibilityPolicy.isCompatible(diffs));
    }
}
