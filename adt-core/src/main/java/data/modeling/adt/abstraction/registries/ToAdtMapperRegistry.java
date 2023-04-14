package data.modeling.adt.abstraction.registries;

import data.modeling.adt.abstraction.exceptions.AdtException;
import data.modeling.adt.abstraction.exceptions.MapperNotFoundException;
import data.modeling.adt.abstraction.mappers.MapToAdt;
import data.modeling.adt.abstraction.typedefs.AnyType;

import java.util.*;

public class ToAdtMapperRegistry {

    Set<MapToAdt> toAdtMappers = new LinkedHashSet<>();

    public <T, R extends AnyType> void register(MapToAdt<T,R> mapper){
        toAdtMappers.add(mapper);
    }

    public <T, R extends AnyType> void remove(MapToAdt<T,R> mapper){
        toAdtMappers.remove(mapper);
    }

    public <T, R extends AnyType> Optional<MapToAdt> findToMapper(Object value){
        if(!Optional.ofNullable(value).isPresent()) return Optional.empty();
        return toAdtMappers.stream().filter(mapToAdt -> mapToAdt.canMap(value)).findFirst();
    }

    public AnyType toAdt(Object value) throws AdtException {
        Optional<MapToAdt> mapper = findToMapper(value);
        if(!mapper.isPresent())
            throw new MapperNotFoundException(value);

        return mapper.get().toAdt(value);
    }


}
