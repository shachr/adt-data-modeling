package data.modeling.adt.typedefs;

import data.modeling.adt.abstraction.visitors.AdtVisitor;
import data.modeling.adt.exceptions.AdtException;

import java.util.Arrays;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EnumType implements SumType {
    private ScalarType baseType;
    private Set<EnumItemType> items;

    public EnumType(ScalarType baseType, Set<EnumItemType> items) {
        this.baseType = baseType;
        this.items = items;
    }

    public Set<EnumItemType> getItems() {
        return items;
    }

    public static <T extends ScalarType> EnumType of(T baseType, EnumItemType... values){
        return of(baseType, Arrays.stream(values));
    }

    public static <T extends ScalarType> EnumType of(T baseType, Stream<EnumItemType> enumItemTypeStream){
        return new EnumType(baseType, enumItemTypeStream.collect(Collectors.toSet()));
    }

    public ScalarType getBaseType() {
        return baseType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EnumType enumType = (EnumType) o;
        return baseType.equals(enumType.baseType) && items.equals(enumType.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(baseType, items);
    }

    @Override
    public int size() {
        return items.size();
    }

    public void accept(AdtVisitor visitor) throws AdtException {
        visitor.visit(this);
    }

    public boolean isValueOf(Object value){
        return this.baseType.isValueOf(value) && items.stream().anyMatch(enumItemType -> enumItemType.value.getConstant().equals(value));
    }

    public record EnumItemType(String name, ConstantPrimitiveType value) implements AnyType{}
}
