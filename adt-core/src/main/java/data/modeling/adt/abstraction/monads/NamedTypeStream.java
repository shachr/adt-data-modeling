package data.modeling.adt.abstraction.monads;

import data.modeling.adt.abstraction.exceptions.AdtException;
import data.modeling.adt.abstraction.typedefs.NamedType;
import data.modeling.adt.abstraction.typedefs.ProductType;

import java.util.stream.Stream;

public interface NamedTypeStream {

    Stream<NamedType> stream() throws AdtException;
}
