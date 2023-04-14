package data.modeling.adt.abstraction.typedefs;

public class NullValueType implements CollectionType {
    private final AnyType elementType;

    public NullValueType(AnyType elementType) {
        this.elementType = elementType;
    }

    public AnyType getItemType() {
        return elementType;
    }
}

