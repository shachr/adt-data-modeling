package data.modeling.adt.typedefs;

import data.modeling.adt.SchemaContext;
import data.modeling.adt.abstraction.visitors.AdtVisitor;
import data.modeling.adt.exceptions.AdtException;

import java.util.Arrays;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class EnumType implements SumType {
    private PrimitiveType baseType;
    private Set<EnumItemType> items;

    public EnumType(PrimitiveType baseType, Set<EnumItemType> items) {
        this.baseType = baseType;
        this.items = items;
    }

    public Set<EnumItemType> getItems() {
        return items;
    }

    public static <T extends PrimitiveType> EnumType of(T baseType, EnumItemType... values){
        return new EnumType(baseType, Arrays.stream(values).collect(Collectors.toSet()));
    }

    public PrimitiveType getBaseType() {
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

    @Override
    public AnyType resolveSubSchemes(SchemaContext schemaContext) throws AdtException {
        return this;
    }

    public record EnumItemType(String name, ConstantPrimitiveType value) implements AnyType{}
}
