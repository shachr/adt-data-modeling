package data.modeling.adt.abstraction.mappers;

import data.modeling.adt.exceptions.AdtException;
import data.modeling.adt.typedefs.AnyType;

public interface MapToAdt<T, R extends AnyType> {
    boolean canMap(T value);

    R toAdt(T value) throws AdtException;
}

