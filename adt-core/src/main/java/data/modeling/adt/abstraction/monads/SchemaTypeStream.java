package data.modeling.adt.abstraction.monads;

import data.modeling.adt.exceptions.AdtException;
import data.modeling.adt.mappers.registries.FromAdtMapperRegistry;

import java.util.stream.Stream;

public interface SchemaTypeStream<T> {

    FromAdtMapperRegistry getMapperRegistry();

    Stream<T> stream() throws AdtException;
}
