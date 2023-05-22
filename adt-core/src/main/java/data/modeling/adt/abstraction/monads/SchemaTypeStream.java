package data.modeling.adt.abstraction.monads;

import data.modeling.adt.exceptions.AdtException;
import data.modeling.adt.mappers.registries.FromAdtMapperRegistry;

import java.util.stream.Stream;

public interface SchemaTypeStream<T> {

    FromAdtMapperRegistry fromAdtMapperRegistry = new FromAdtMapperRegistry();

    default FromAdtMapperRegistry getMapperRegistry(){
        return fromAdtMapperRegistry;
    }

    Stream<T> stream() throws AdtException;
}
