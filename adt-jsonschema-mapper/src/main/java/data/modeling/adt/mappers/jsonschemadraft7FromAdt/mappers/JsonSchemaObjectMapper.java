package data.modeling.adt.mappers.jsonschemadraft7FromAdt.mappers;

import data.modeling.adt.exceptions.AdtException;
import data.modeling.adt.mappers.jsonschemadraft7FromAdt.util.MapBuilder;
import data.modeling.adt.mappers.jsonschemadraft7ToAdt.annotations.JsonSchemaAnnotation;
import data.modeling.adt.mappers.registries.FromAdtMapperRegistry;
import data.modeling.adt.typedefs.AdditionalFieldsType;
import data.modeling.adt.typedefs.FieldType;
import data.modeling.adt.typedefs.ProductType;
import data.modeling.adt.util.LambdaExceptionUtil;
import static data.modeling.adt.util.StreamExtensions.toMap;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JsonSchemaObjectMapper extends JsonSchemaMapper<ProductType> {

    private FromAdtMapperRegistry fromAdtMapperRegistry;

    public JsonSchemaObjectMapper(FromAdtMapperRegistry fromAdtMapperRegistry) {

        this.fromAdtMapperRegistry = fromAdtMapperRegistry;
    }

    @Override
    public boolean canMap(ProductType value) {
        return value.getExtendedProductTypes().isEmpty();
    }

    @Override
    public Stream<Map.Entry<String, Object>> fromAdt(ProductType type) throws AdtException {
        MapBuilder mapBuilder = MapBuilder.create();
        mapBuilder.put("type", "object");
        if(!type.getOwnFields().isEmpty()) {
            MapBuilder propertiesMapBuilder = mapBuilder.putNestedMap("properties");

            List<String> required = new ArrayList<>();
            type.getOwnFields().forEach(LambdaExceptionUtil.consumer(fieldType -> {
                if (fieldType.isRequired()) {
                    required.add(fieldType.getName());
                }
                if(fieldType instanceof AdditionalFieldsType){
                    mapBuilder.put("additionalProperties", toMap(fromAdtMapperRegistry.fromAdt(fieldType.getType())));
                } else {
                    Map<String, Object> fieldMap = toMap(fromAdtMapperRegistry.fromAdt(fieldType.getType()));
                    fieldType.getAnnotations().stream()
                            .filter(annotation -> annotation instanceof JsonSchemaAnnotation)
                            .map(annotation -> (JsonSchemaAnnotation) annotation)
                            .forEach(jsonSchemaAnnotation ->
                                    fieldMap.put(jsonSchemaAnnotation.getName(), jsonSchemaAnnotation.getValue()));
                    propertiesMapBuilder.put(fieldType.getName(), fieldMap);
                }
            }));

            if (!required.isEmpty()) {
                mapBuilder.put("required", required);
            }
        }
        return mapBuilder.build().entrySet().stream();
    }
}
