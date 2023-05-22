package data.modeling.adt.abstraction.monads;

import data.modeling.adt.exceptions.AdtException;
import data.modeling.adt.mappers.registries.ToAdtMapperRegistry;
import data.modeling.adt.typedefs.NamedType;

import java.util.stream.Stream;

public interface NamedTypeStream {

    ToAdtMapperRegistry toAdtMapperRegistry = new ToAdtMapperRegistry();

    default ToAdtMapperRegistry getMapperRegistry(){
        return toAdtMapperRegistry;
    }
    Stream<NamedType> stream() throws AdtException;
}
