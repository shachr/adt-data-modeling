package adt.hiveddlToAdt.mappers;

import data.modeling.adt.abstraction.mappers.MapToAdt;
import data.modeling.adt.typedefs.AnyType;

public abstract class DataTypeMapper <T, R extends AnyType>
        implements MapToAdt<T, R> {

}
