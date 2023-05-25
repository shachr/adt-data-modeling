package data.modeling.adt.util;

import data.modeling.adt.abstraction.compatibility.CompatibilityPolicy;
import data.modeling.adt.annotations.syntactic.DefaultValue;
import data.modeling.adt.compatibility.AnyTypeComparator;
import data.modeling.adt.compatibility.Difference;
import data.modeling.adt.compatibility.DifferenceTypes;
import data.modeling.adt.compatibility.policies.BackwardCompatibilityPolicy;
import data.modeling.adt.compatibility.policies.ForwardCompatibilityPolicy;
import data.modeling.adt.compatibility.policies.FullCompatibilityPolicy;
import data.modeling.adt.typedefs.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class SumTypeComparatorTest {
    CompatibilityPolicy backwardCompatibilityPolicy = new BackwardCompatibilityPolicy();
    CompatibilityPolicy forwardCompatibilityPolicy = new ForwardCompatibilityPolicy();
    CompatibilityPolicy fullCompatibilityPolicy = new FullCompatibilityPolicy();

    @Test
    public void compareSumType_shouldPassForMatchingUnionTypes() {
        // Create matching union types
        List<AnyType> types1 = new ArrayList<>();
        types1.add(new StringType());
        types1.add(new Int32Type());
        UnionType unionType1 = new UnionType(types1);

        List<AnyType> types2 = new ArrayList<>();
        types2.add(new StringType());
        types2.add(new Int32Type());
        UnionType unionType2 = new UnionType(types2);

        // Compare the union types
//        unionType1.compatiableWith(unionType2, )
        List<Difference> differences = AnyTypeComparator.compare(unionType1, unionType2);

        // Verify that no differences were found
        assertEquals(0, differences.size());
    }

    @Test
    public void compareSumType_shouldFailForMismatchingUnionTypes() {
        // Create mismatching union types
        UnionType unionType1 = new UnionType(Stream.of(new StringType(), new StringType()).collect(Collectors.toSet()));
        UnionType unionType2 = new UnionType(Stream.of(new Int32Type()).collect(Collectors.toSet()));

        // Compare the union types
        List<Difference> differences = AnyTypeComparator.compare(unionType1, unionType2);

        differences.forEach(System.out::println);
        // Verify that differences were found
        assertEquals(2, differences.size());

        Difference difference1 = differences.get(0);
        assertEquals(DifferenceTypes.TypeChanged, difference1.differenceType());
        assertEquals("/0", difference1.jsonPointer());
        assertEquals("StringType", difference1.expected());
        assertEquals("Int32Type", difference1.actual());

        Difference difference2 = differences.get(1);
        assertEquals(DifferenceTypes.TypeRemoved, difference2.differenceType());
        assertEquals("/1", difference2.jsonPointer());
        assertEquals("StringType", difference2.expected().getClass().getSimpleName());
        assertEquals(null, difference2.actual());

        assertFalse(backwardCompatibilityPolicy.isCompatible(differences));
        assertFalse(forwardCompatibilityPolicy.isCompatible(differences));
        assertFalse(fullCompatibilityPolicy.isCompatible(differences));
    }

    @Test
    public void compareSumType_shouldFailForMismatchingEnumTypes() {
        // Create mismatching enum types
        EnumType enumType1 = new EnumType(new Int32Type(), Stream.of(new EnumType.EnumItemType("1", Int32Type.constantOf(1))).collect(Collectors.toSet()));
        EnumType enumType2 = new EnumType(new StringType(), Stream.of(new EnumType.EnumItemType("foo", StringType.constantOf("foo"))).collect(Collectors.toSet()));

        // Compare the enum types
        List<Difference> differences = AnyTypeComparator.compare(enumType1, enumType2);

        // Verify that differences were found
        assertEquals(1, differences.size());

        // Verify the difference message and JSON pointer
        Difference difference = differences.get(0);
        assertEquals(DifferenceTypes.TypeChanged, difference.differenceType());
        assertEquals("/", difference.jsonPointer());

        assertFalse(backwardCompatibilityPolicy.isCompatible(differences));
        assertFalse(forwardCompatibilityPolicy.isCompatible(differences));
        assertFalse(fullCompatibilityPolicy.isCompatible(differences));
    }

    @Test
    public void compareSumType_shouldPassForMatchingEnumTypes() {
        // Create mismatching enum types
        EnumType enumType1 = new EnumType(new Int32Type(), Stream.of(new EnumType.EnumItemType("1", Int32Type.constantOf(1))).collect(Collectors.toSet()));
        EnumType enumType2 = new EnumType(new StringType(), Stream.of(new EnumType.EnumItemType("1", Int32Type.constantOf(1))).collect(Collectors.toSet()));

        // Compare the enum types
        List<Difference> differences = AnyTypeComparator.compare(enumType1, enumType2);

        // Verify that differences were found
        assertEquals(1, differences.size());
    }

    @Test
    public void compareEnumType_shouldFailForTypeAddedWithDefault() {
        // additive enum change with default
        EnumType enumType1 = new EnumType(new Int32Type(), Stream.of(new EnumType.EnumItemType("1", Int32Type.constantOf(1)))
                .collect(Collectors.toSet()));
        EnumType enumType2 = new EnumType(new StringType(), Stream.of(
                new EnumType.EnumItemType("1", Int32Type.constantOf(1)),
                new EnumType.EnumItemType("2", Int32Type.constantOf(2)))
                .collect(Collectors.toSet()));

        ProductType productType1 = ProductType.of(FieldType.builder("foo", enumType1).withAnnotations(new DefaultValue(1)).build());
        ProductType productType2 = ProductType.of(FieldType.builder("foo", enumType2).withAnnotations(new DefaultValue(1)).build());

        // Compare the enum types
        List<Difference> differences = AnyTypeComparator.compare(productType1, productType2);

        // Verify that differences were found
        assertEquals(2, differences.size());
        assertEquals(DifferenceTypes.TypeChanged, differences.get(0).differenceType());
        assertEquals(DifferenceTypes.TypeAddedWithDefault, differences.get(1).differenceType());

        assertFalse(backwardCompatibilityPolicy.isCompatible(differences));
        assertFalse(forwardCompatibilityPolicy.isCompatible(differences));
        assertFalse(fullCompatibilityPolicy.isCompatible(differences));
    }

    @Test
    public void compareUnionType_shouldFailForTypeAddedWithDefault() {
        // additive enum change with default
        UnionType unionType1 = new UnionType(Stream.of(new Int32Type()).collect(Collectors.toCollection(LinkedHashSet::new)));
        UnionType unionType2 = new UnionType(Stream.of(new Int32Type(), new StringType()).collect(Collectors.toCollection(LinkedHashSet::new)));

        ProductType productType1 = ProductType.of(FieldType.builder("foo", unionType1).withAnnotations(new DefaultValue(1)).build());
        ProductType productType2 = ProductType.of(FieldType.builder("foo", unionType2).withAnnotations(new DefaultValue(1)).build());

        // Compare the enum types
        List<Difference> differences = AnyTypeComparator.compare(productType1, productType2);

        // Verify that differences were found
        differences.forEach(System.out::println);
        assertEquals(false, differences.isEmpty());
        assertEquals(1, differences.size());
        assertEquals(DifferenceTypes.TypeAddedWithDefault, differences.get(0).differenceType());

        assertTrue(backwardCompatibilityPolicy.isCompatible(differences));
        assertTrue(forwardCompatibilityPolicy.isCompatible(differences));
        assertTrue(fullCompatibilityPolicy.isCompatible(differences));
    }
}