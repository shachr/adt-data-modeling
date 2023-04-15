package data.modeling.adt.abstraction.monads;

import data.modeling.adt.exceptions.AdtException;
import data.modeling.adt.typedefs.NamedType;

import java.util.stream.Stream;

public interface NamedTypeStream {

    Stream<NamedType> stream() throws AdtException;
}
