package data.modeling.adt.typedefs;


import data.modeling.adt.abstraction.visitors.AdtVisitor;
import data.modeling.adt.exceptions.AdtException;
import data.modeling.adt.util.AdtVisitorUtil;

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

    public void accept(AdtVisitor visitor) throws AdtException {
        visitor.visit(this);
        AdtVisitorUtil.visit(visitor, getItemType());
    }
}

