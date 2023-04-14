package data.modeling.adt.abstraction.mappers;

import data.modeling.adt.abstraction.exceptions.AdtException;
import data.modeling.adt.abstraction.typedefs.AnyType;

public interface MapFromAdt<T extends AnyType, R> {

    boolean canMap(T value);

    R fromAdt(T type) throws AdtException;
}
