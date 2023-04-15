package data.modeling.adt.typedefs;


public interface CollectionType extends AnyType {
    AnyType getItemType();
}

