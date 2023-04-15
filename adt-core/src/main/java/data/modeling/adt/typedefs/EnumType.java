package data.modeling.adt.typedefs;

import java.util.Arrays;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class EnumType implements SumType {
    private PrimitiveType baseType;
    private Set<ConstantPrimitiveType> values;

    public EnumType(PrimitiveType baseType, Set<ConstantPrimitiveType> values) {
        this.baseType = baseType;
        this.values = values;
    }

    public Set<ConstantPrimitiveType> getValues() {
        return values;
    }

    public static <T extends PrimitiveType> EnumType of(T baseType, ConstantPrimitiveType... values){
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
        return baseType.equals(enumType.baseType) && values.equals(enumType.values);
    }

    @Override
    public int hashCode() {
        return Objects.hash(baseType, values);
    }

    @Override
    public int size() {
        return values.size();
    }
}
