package data.modeling.adt.mappers.jsonschemadraft7FromAdt.mappers;

import data.modeling.adt.SchemaContext;
import data.modeling.adt.exceptions.AdtException;
import data.modeling.adt.mappers.jsonschemadraft7FromAdt.util.MapBuilder;
import data.modeling.adt.mappers.jsonschemadraft7ToAdt.annotations.JsonSchemaAnnotation;
import data.modeling.adt.mappers.registries.FromAdtMapperRegistry;
import data.modeling.adt.typedefs.AnyType;
import data.modeling.adt.typedefs.ProductType;
import data.modeling.adt.typedefs.ReferenceNamedType;
import data.modeling.adt.util.LambdaExceptionUtil;
import data.modeling.adt.util.MapsUtil;
import static data.modeling.adt.util.StreamExtensions.toMap;

import java.util.*;
import java.util.stream.Collectors;
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
        Set<Map<String, Object>> allOf = new HashSet<>();
        mapBuilder.put("allOf", allOf);
        type.getExtendedProductTypes().forEach(LambdaExceptionUtil.consumer(referenceNamedType -> {
            MapBuilder allOfItemMap = MapBuilder.create();
            fromAdtMapperRegistry.fromAdt(referenceNamedType);
            allOf.add(allOfItemMap.put("$ref", referenceNamedType.getReferenceName()).build());
        }));

        MapBuilder allOfItemMap = MapBuilder.create();
        MapBuilder propertiesMap = allOfItemMap.putNestedMap("properties");
        type.getOwnFields().forEach(LambdaExceptionUtil.consumer(fieldType -> {
            Map<String, Object> property = toMap(fromAdtMapperRegistry.fromAdt(fieldType.getType()));
            fieldType.getAnnotations().stream()
                    .filter(annotation -> annotation instanceof JsonSchemaAnnotation)
                    .map(annotation -> (JsonSchemaAnnotation)annotation)
                    .forEach(annotation -> {
                        property.putIfAbsent(annotation.getName(), annotation.getValue());
                    });
            propertiesMap.put(fieldType.getName(), property);
        }));
        allOf.add(allOfItemMap.build());
        return mapBuilder.build().entrySet().stream();
    }
}
