package data.modeling.adt.mappers.registries;

import data.modeling.adt.exceptions.AdtException;
import data.modeling.adt.exceptions.MapperNotFoundException;
import data.modeling.adt.abstraction.mappers.MapToAdt;
import data.modeling.adt.typedefs.AnyType;
import data.modeling.adt.util.ReflectionUtil;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

public class ToAdtMapperRegistry {

    Set<MapToAdt> toAdtMappers = new LinkedHashSet<>();
    Map<Class, Class> typeArguments = new LinkedHashMap<>();

    public <T, R extends AnyType> void register(MapToAdt<T,R> mapper){
        toAdtMappers.add(mapper);
        typeArguments.put(mapper.getClass(), ReflectionUtil.getFirstTypeArgument((mapper.getClass())));
    }


    public <T, R extends AnyType> void remove(MapToAdt<T,R> mapper){
        toAdtMappers.remove(mapper);
    }

    public <T, R extends AnyType> Optional<MapToAdt<T, R>> findToMapper(Object value){
        if(Optional.ofNullable(value).isEmpty()) return Optional.empty();
        return toAdtMappers.stream()
                .filter(mapFromAdt -> {
                    Class mapperType = typeArguments.get(mapFromAdt.getClass());
                    return mapperType.isInstance(value) || value.getClass().isAssignableFrom(mapperType);
                })
                .filter(mapToAdt -> mapToAdt.canMap(value)).findFirst()
                .map(mapper -> (MapToAdt<T, R>)mapper);
    }

    public AnyType toAdt(Object value) throws AdtException {
        Optional<MapToAdt<Object, AnyType>> mapper = findToMapper(value);
        if(!mapper.isPresent())
            throw new MapperNotFoundException(value);

        return mapper.get().toAdt(value);
    }


}
