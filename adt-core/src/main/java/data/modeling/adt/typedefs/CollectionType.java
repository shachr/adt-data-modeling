package data.modeling.adt.typedefs;


public interface CollectionType extends ComplexType {
    AnyType getItemType();
}

