package data.modeling.adt.typedefs;

import data.modeling.adt.abstraction.visitors.AdtVisitor;

public interface SumType extends AnyType{
    int size();
}
