package data.modeling.adt.mappers.jsonschemadraft7FromAdt.mappers;

import data.modeling.adt.exceptions.AdtException;
import data.modeling.adt.mappers.jsonschemadraft7FromAdt.util.MapBuilder;
import data.modeling.adt.mappers.registries.FromAdtMapperRegistry;
import data.modeling.adt.typedefs.ProductType;
import data.modeling.adt.util.LambdaExceptionUtil;

import static data.modeling.adt.util.StreamExtensions.toMap;

import java.util.*;
import java.util.stream.Stream;

public class JsonSchemaAllOfMapper extends JsonSchemaMapper<ProductType> {

    private FromAdtMapperRegistry fromAdtMapperRegistry;

    public JsonSchemaAllOfMapper(FromAdtMapperRegistry fromAdtMapperRegistry) {

        this.fromAdtMapperRegistry = fromAdtMapperRegistry;
    }

    @Override
    public boolean canMap(ProductType value) {
        return !value.getExtendedProductTypes().isEmpty();
    }

    @Override
    public Stream<Map.Entry<String, Object>> fromAdt(ProductType type) throws AdtException {
        MapBuilder mapBuilder = MapBuilder.create();
        List<Map<String, Object>> allOf = new LinkedList<>();
        mapBuilder.put("type", "object");
        mapBuilder.put("allOf", allOf);
        type.getExtendedProductTypes().forEach(LambdaExceptionUtil.consumer(referenceNamedType -> {
            MapBuilder allOfItemMap = MapBuilder.create();
            fromAdtMapperRegistry.fromAdt(referenceNamedType);
            allOf.add(allOfItemMap.put("$ref", referenceNamedType.getReferenceName()).build());
        }));

        Map<String, Object> allOfItemMap = toMap((Stream<Map.Entry<String, Object>>) fromAdtMapperRegistry
                .getMapper(JsonSchemaObjectMapper.class).fromAdt(type));
        List<String> required = (List<String>)allOfItemMap.remove("required");
        allOf.add(allOfItemMap);
        if(null != required && !required.isEmpty()) {
            mapBuilder.put("required", required);
        }
        return mapBuilder.build().entrySet().stream();
    }
}
