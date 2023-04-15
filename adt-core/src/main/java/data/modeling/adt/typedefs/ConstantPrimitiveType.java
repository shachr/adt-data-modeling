package data.modeling.adt.typedefs;

import java.util.Objects;

public class ConstantPrimitiveType implements AnyType {
    private AnyType type;
    private Object constant;

    public ConstantPrimitiveType(AnyType type, Object constant){

        this.type = type;
        this.constant = constant;
    }

    public Object getConstant() {
        return constant;
    }

    public AnyType getType() {
        return type;
    }

    @Override
    public int hashCode() {
        return this.constant.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConstantPrimitiveType that = (ConstantPrimitiveType) o;
        return type.equals(that.type) && Objects.equals(constant, that.constant);
    }
}
