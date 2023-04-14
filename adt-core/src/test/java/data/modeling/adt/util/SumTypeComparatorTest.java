package data.modeling.adt.util;

import data.modeling.adt.abstraction.typedefs.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SumTypeComparatorTest {

    @Test
    public void compareSumType_shouldPassForMatchingUnionTypes() {
        // Create matching union types
        List<AnyType> types1 = new ArrayList<>();
        types1.add(new NamedType("type1", new StringType()));
        types1.add(new NamedType("type2", new IntType()));
        UnionType unionType1 = new UnionType(types1);

        List<AnyType> types2 = new ArrayList<>();
        types2.add(new NamedType("type1", new StringType()));
        types2.add(new NamedType("type2", new IntType()));
        UnionType unionType2 = new UnionType(types2);

        // Compare the union types
        List<AnyTypeComparator.Difference> differences = new AnyTypeComparator(unionType1, unionType2).findDiff();

        // Verify that no differences were found
        assertEquals(0, differences.size());
    }

    @Test
    public void compareSumType_shouldFailForMismatchingUnionTypes() {
        // Create mismatching union types
        UnionType unionType1 = new UnionType(Stream.of(new StringType(), new StringType()).collect(Collectors.toSet()));
        UnionType unionType2 = new UnionType(Stream.of(new IntType()).collect(Collectors.toSet()));

        // Compare the union types
        List<AnyTypeComparator.Difference> differences = new AnyTypeComparator(unionType1, unionType2).findDiff();

        differences.forEach(System.out::println);
        // Verify that differences were found
        assertEquals(2, differences.size());

        AnyTypeComparator.Difference difference1 = differences.get(0);
        assertEquals("type is different", difference1.getMessage());
        assertEquals("/0", difference1.getJsonPointer());
        assertEquals("StringType", difference1.getExpected());
        assertEquals("IntType", difference1.getActual());

        AnyTypeComparator.Difference difference2 = differences.get(1);
        assertEquals("item not found: StringType", difference2.getMessage());
        assertEquals("/", difference2.getJsonPointer());
        assertEquals("StringType", difference2.getExpected().getClass().getSimpleName());
        assertEquals(null, difference2.getActual());
    }

    @Test
    public void compareSumType_shouldFailForMismatchingEnumTypes() {
        // Create mismatching enum types
        EnumType enumType1 = new EnumType(new IntType(), Stream.of(IntType.constantOf(1)).collect(Collectors.toSet()));
        EnumType enumType2 = new EnumType(new StringType(), Stream.of(StringType.constantOf("foo")).collect(Collectors.toSet()));

        // Compare the enum types
        List<AnyTypeComparator.Difference> differences = new AnyTypeComparator(enumType1, enumType2).findDiff();

        // Verify that differences were found
        assertEquals(1, differences.size());

        // Verify the difference message and JSON pointer
        AnyTypeComparator.Difference difference = differences.get(0);
        assertEquals("enum base type mismatch", difference.getMessage());
        assertEquals("/", difference.getJsonPointer());
    }

    @Test
    public void compareSumType_shouldPassForMatchingEnumTypes() {
        // Create mismatching enum types
        EnumType enumType1 = new EnumType(new IntType(), Stream.of(IntType.constantOf(1)).collect(Collectors.toSet()));
        EnumType enumType2 = new EnumType(new IntType(), Stream.of(IntType.constantOf(1)).collect(Collectors.toSet()));

        // Compare the enum types
        List<AnyTypeComparator.Difference> differences = new AnyTypeComparator(enumType1, enumType2).findDiff();

        // Verify that differences were found
        assertEquals(0, differences.size());
    }
}