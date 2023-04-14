package data.modeling.adt.abstraction.typedefs;

public abstract class PrimitiveType implements AnyType {
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        return true;
    }
}
