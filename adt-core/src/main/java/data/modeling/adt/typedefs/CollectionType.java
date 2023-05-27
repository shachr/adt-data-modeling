package data.modeling.adt.typedefs;


public interface CollectionType extends ComplexType, AdtType {
    AnyType getItemType();
}

