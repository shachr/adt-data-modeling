package data.modeling.adt.typedefs;

import data.modeling.adt.abstraction.visitors.AdtVisitor;

import java.util.Objects;

public class MapType implements CollectionType {
    private AnyType keyType;
    private AnyType itemType;

    public MapType(AnyType keyType, AnyType valueType) {
        this.keyType = keyType;
        this.itemType = valueType;
    }

    public AnyType getKeyType() {
        return keyType;
    }
    public AnyType getItemType() {
        return itemType;
    }

    public void accept(AdtVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MapType mapType = (MapType) o;
        return keyType.equals(mapType.keyType) && itemType.equals(mapType.itemType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(keyType, itemType);
    }
}

