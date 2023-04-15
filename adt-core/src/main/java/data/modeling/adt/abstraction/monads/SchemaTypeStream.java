package data.modeling.adt.abstraction.monads;

import data.modeling.adt.exceptions.AdtException;

import java.util.stream.Stream;

public interface SchemaTypeStream<T> {

    Stream<T> stream() throws AdtException;
}
