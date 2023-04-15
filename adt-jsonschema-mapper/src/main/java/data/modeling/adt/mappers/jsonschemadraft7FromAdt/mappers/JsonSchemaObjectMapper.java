package data.modeling.adt.mappers.jsonschemadraft7FromAdt.mappers;

import data.modeling.adt.exceptions.AdtException;
import data.modeling.adt.mappers.jsonschemadraft7FromAdt.util.MapBuilder;
import data.modeling.adt.mappers.registries.FromAdtMapperRegistry;
import data.modeling.adt.typedefs.ProductType;
import data.modeling.adt.util.LambdaExceptionUtil;
import static data.modeling.adt.util.StreamExtensions.toMap;

import java.util.Map;
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
        MapBuilder propertiesMapBuilder = mapBuilder.putNestedMap("properties");
        type.getOwnFields().forEach(LambdaExceptionUtil.consumer(fieldType ->
                propertiesMapBuilder.put(fieldType.getName(), toMap(fromAdtMapperRegistry.fromAdt(fieldType.getType())))));
        return mapBuilder.build().entrySet().stream();
    }
}