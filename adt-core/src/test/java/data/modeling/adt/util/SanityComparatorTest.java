package data.modeling.adt.util;

import data.modeling.adt.abstraction.typedefs.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SanityComparatorTest {

    @Test
    public void testCompare() {
        NamedType namedType1 = new NamedType("person", new ProductType(new HashSet<>()));
        NamedType namedType2 = new NamedType("person", new ProductType(new HashSet<>()));

        // Same objects
        assertEquals(0, AnyTypeComparator.compare(namedType1, namedType1).size());

        // Different objects with same values
        assertEquals(0, AnyTypeComparator.compare(namedType1, namedType2).size());

        // Different named types
        namedType2 = new NamedType("person", new ProductType(Stream.of(
                new FieldType("foo", new StringType()),
                new FieldType("bar", new IntType())
        ).collect(Collectors.toSet())));

        List<AnyTypeComparator.Difference> diffs = AnyTypeComparator.compare(namedType1, namedType2);
        assertEquals(2, diffs.size());

        AnyTypeComparator.Difference diff1 = diffs.get(0);
        assertEquals("unexpected field: foo", diff1.getMessage());
        assertEquals("/", diff1.getJsonPointer());
        assertEquals(null, diff1.getExpected());
        assertEquals("foo", diff1.getActual());

        AnyTypeComparator.Difference diff2 = diffs.get(1);
        assertEquals("unexpected field: bar", diff2.getMessage());
        assertEquals("/", diff2.getJsonPointer());
        assertEquals(null, diff2.getExpected());
        assertEquals("bar", diff2.getActual());
    }

    @Test
    void testCompareNamedTypeDifferentName() {
        NamedType nt1 = new NamedType("foo", new ListType(new IntType()));
        NamedType nt2 = new NamedType("bar", new ListType(new IntType()));
        List<AnyTypeComparator.Difference> diffs = AnyTypeComparator.compare(nt1, nt2);
        Assertions.assertEquals(1, diffs.size());
        AnyTypeComparator.Difference diff = diffs.get(0);
        Assertions.assertEquals("name is different", diff.getMessage());
        Assertions.assertEquals("/", diff.getJsonPointer());
        Assertions.assertEquals("foo", diff.getExpected());
        Assertions.assertEquals("bar", diff.getActual());
    }

    @Test
    void testCompareNamedTypeDifferentType() {
        NamedType nt1 = new NamedType("foo", new SetType(new IntType()));
        NamedType nt2 = new NamedType("foo", new StringType());
        List<AnyTypeComparator.Difference> diffs = AnyTypeComparator.compare(nt1, nt2);
        Assertions.assertEquals(1, diffs.size());
        AnyTypeComparator.Difference diff = diffs.get(0);
        Assertions.assertEquals("type is different", diff.getMessage());
        Assertions.assertEquals("/", diff.getJsonPointer());
        Assertions.assertEquals("SetType", diff.getExpected());
        Assertions.assertEquals("StringType", diff.getActual());
    }

    @Test
    void testCompareSumTypeUnionType() {
        Set<AnyType> types1 = new HashSet<>();
        types1.add(new NullValueType(new IntType()));
        UnionType ut1 = new UnionType(types1);
        Set<AnyType> types2 = new HashSet<>();
        types2.add(new NullValueType(new IntType()));
        UnionType ut2 = new UnionType(types2);
        List<AnyTypeComparator.Difference> diffs = AnyTypeComparator.compare(ut1, ut2);
        Assertions.assertEquals(0, diffs.size());
    }

    @Test
    void testCompareSumTypeEnumTypeSameBaseType() {
        Set<ConstantPrimitiveType> values1 = new HashSet<>();
        values1.add(StringType.constantOf("foo"));
        EnumType et1 = new EnumType(new StringType(), values1);
        Set<ConstantPrimitiveType> values2 = new HashSet<>();
        values2.add(StringType.constantOf("foo"));
        EnumType et2 = new EnumType(new StringType(), values2);
        List<AnyTypeComparator.Difference> diffs = AnyTypeComparator.compare(et1, et2);
        Assertions.assertEquals(0, diffs.size());
    }
    @Test
    void testCompareSumTypeEnumTypeDifferentBaseType() {
        Set<ConstantPrimitiveType> values1 = new HashSet<>();
        values1.add(StringType.constantOf("foo"));
        EnumType et1 = new EnumType(new StringType(), values1);
        Set<ConstantPrimitiveType> values2 = new HashSet<>();
        values2.add(IntType.constantOf(1));
        EnumType et2 = new EnumType(new IntType(), values2);
        List<AnyTypeComparator.Difference> diffs = AnyTypeComparator.compare(et1, et2);
        Assertions.assertEquals(1, diffs.size());
        AnyTypeComparator.Difference diff = diffs.get(0);
        Assertions.assertEquals("enum base type mismatch", diff.getMessage());
        Assertions.assertEquals("/", diff.getJsonPointer());
        Assertions.assertEquals("StringType", diff.getExpected());
        Assertions.assertEquals("IntType", diff.getActual());
    }
}
