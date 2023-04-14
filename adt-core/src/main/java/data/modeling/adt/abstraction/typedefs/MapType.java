package data.modeling.adt.abstraction.typedefs;

public class MapType implements CollectionType {
    private AnyType itemType;

    public MapType(AnyType itemType) {
        this.itemType = itemType;
    }

    public AnyType getItemType() {
        return itemType;
    }
}

