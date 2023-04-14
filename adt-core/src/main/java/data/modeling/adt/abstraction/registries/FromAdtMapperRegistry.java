package data.modeling.adt.abstraction.registries;

import data.modeling.adt.abstraction.exceptions.AdtException;
import data.modeling.adt.abstraction.exceptions.MapperNotFoundException;
import data.modeling.adt.abstraction.mappers.MapFromAdt;
import data.modeling.adt.abstraction.mappers.MapToAdt;
import data.modeling.adt.abstraction.typedefs.AnyType;

import java.util.*;

public class FromAdtMapperRegistry {

    Set<MapFromAdt> fromAdtMappers = new LinkedHashSet<>();

    public <T extends AnyType, R> void register(MapFromAdt<T,R> mapper){
        fromAdtMappers.add(mapper);
    }

    public <T extends AnyType, R> Optional<MapFromAdt> findToMapper(T value){
        if(!Optional.ofNullable(value).isPresent()) return Optional.empty();
        return fromAdtMappers.stream().filter(mapToAdt -> mapToAdt.canMap(value)).findFirst();
    }

    public <T extends AnyType, R> R fromAdt(T value) throws AdtException {
        Optional<MapFromAdt> mapper = findToMapper(value);
        if(!mapper.isPresent())
            throw new MapperNotFoundException(value);

        return (R) mapper.get().fromAdt(value);
    }
}
