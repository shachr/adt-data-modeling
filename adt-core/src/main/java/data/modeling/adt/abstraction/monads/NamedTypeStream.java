package data.modeling.adt.abstraction.monads;

import data.modeling.adt.exceptions.AdtException;
import data.modeling.adt.mappers.registries.ToAdtMapperRegistry;
import data.modeling.adt.typedefs.ComplexType;
import data.modeling.adt.typedefs.Definition;

import java.util.stream.Stream;

public interface NamedTypeStream {
    ToAdtMapperRegistry getMapperRegistry();

    Stream<Definition<ComplexType>> stream() throws AdtException;
}
