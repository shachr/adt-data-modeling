package data.modeling.adt.typedefs;


import java.util.Objects;

public class ListType implements CollectionType {
    private AnyType itemsType;

    public ListType(AnyType itemsType) {
        this.itemsType = itemsType;
    }

    public AnyType getItemType() {
        return itemsType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListType listType = (ListType) o;
        return itemsType.equals(listType.itemsType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemsType);
    }
}

