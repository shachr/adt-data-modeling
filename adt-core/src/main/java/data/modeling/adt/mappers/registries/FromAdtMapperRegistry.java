package data.modeling.adt.mappers.registries;

import data.modeling.adt.exceptions.AdtException;
import data.modeling.adt.exceptions.MapperNotFoundException;
import data.modeling.adt.abstraction.mappers.MapFromAdt;
import data.modeling.adt.typedefs.AnyType;
import data.modeling.adt.util.ReflectionUtil;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import java.util.*;

public class FromAdtMapperRegistry {

    Set<MapFromAdt> fromAdtMappers = new LinkedHashSet<>();
    Map<Class, Class> typeArguments = new LinkedHashMap<>();

    public <T extends AnyType, R> void register(MapFromAdt<T,R> mapper){
        fromAdtMappers.add(mapper);
        typeArguments.put(mapper.getClass(), ReflectionUtil.getFirstTypeArgument((mapper.getClass())));
    }

    public MapFromAdt getMapper(Class mapperClass){
        return fromAdtMappers.stream()
                .filter(mapFromAdt -> mapFromAdt.getClass().equals(mapperClass))
                .findFirst()
                .get();
    }

    public <T extends AnyType, R> Optional<MapFromAdt> findToMapper(T value){
        if(!Optional.ofNullable(value).isPresent()) return Optional.empty();
        return fromAdtMappers.stream()
                .filter(mapFromAdt -> {
                    Class mapperType = typeArguments.get(mapFromAdt.getClass());
                    return mapperType.isInstance(value) || value.getClass().isAssignableFrom(mapperType);
                })
                .filter(mapFromAdt -> mapFromAdt.canMap(value)).findFirst();
    }

    public <T extends AnyType, R> R fromAdt(T value) throws AdtException {
        Optional<MapFromAdt> mapper = findToMapper(value);
        if(!mapper.isPresent())
            throw new MapperNotFoundException(value);

        return (R) mapper.get().fromAdt(value);
    }
}
