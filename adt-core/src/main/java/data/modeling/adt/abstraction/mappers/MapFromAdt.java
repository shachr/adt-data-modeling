package data.modeling.adt.abstraction.mappers;

import data.modeling.adt.exceptions.AdtException;
import data.modeling.adt.typedefs.AnyType;

public interface MapFromAdt<T extends AnyType, R> {

    boolean canMap(T value);

    R fromAdt(T type) throws AdtException;
}
