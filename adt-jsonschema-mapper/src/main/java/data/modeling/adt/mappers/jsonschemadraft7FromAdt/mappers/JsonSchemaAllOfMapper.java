package data.modeling.adt.mappers.jsonschemadraft7FromAdt.mappers;

import data.modeling.adt.exceptions.AdtException;
import data.modeling.adt.mappers.jsonschemadraft7FromAdt.util.MapBuilder;
import data.modeling.adt.mappers.registries.FromAdtMapperRegistry;
import data.modeling.adt.typedefs.AllOfType;
import data.modeling.adt.typedefs.CompositionType;
import data.modeling.adt.typedefs.ProductType;
import data.modeling.adt.util.LambdaExceptionUtil;

import static data.modeling.adt.util.StreamExtensions.toMap;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JsonSchemaAllOfMapper extends JsonSchemaMapper<CompositionType> {

    private FromAdtMapperRegistry fromAdtMapperRegistry;

    public JsonSchemaAllOfMapper(FromAdtMapperRegistry fromAdtMapperRegistry) {

        this.fromAdtMapperRegistry = fromAdtMapperRegistry;
    }

    @Override
    public boolean canMap(CompositionType value) {
        return value instanceof AllOfType || (value instanceof ProductType productType && !productType.getImplements().isEmpty());
    }

    @Override
    public Stream<Map.Entry<String, Object>> fromAdt(CompositionType type) throws AdtException {
        MapBuilder mapBuilder = MapBuilder.create();
        List<Map<String, Object>> allOf = new LinkedList<>();
        mapBuilder.put("type", "object");
        mapBuilder.put("allOf", allOf);

        AllOfType allOfType;
        if(type instanceof ProductType){
            allOfType = AllOfType.of(((ProductType) type));
        } else {
            allOfType = (AllOfType) type;
        }

        allOf.addAll(allOfType.getTypes()
                .stream()
                .map(LambdaExceptionUtil.function(itemType -> toMap(fromAdtMapperRegistry.fromAdt(itemType))))
                .collect(Collectors.toCollection(LinkedHashSet::new)));

        return mapBuilder.build().entrySet().stream();
    }
}
