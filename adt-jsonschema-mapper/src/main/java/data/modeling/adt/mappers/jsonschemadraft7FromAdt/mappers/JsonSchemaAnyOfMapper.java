package data.modeling.adt.mappers.jsonschemadraft7FromAdt.mappers;

import data.modeling.adt.exceptions.AdtException;
import data.modeling.adt.mappers.jsonschemadraft7FromAdt.util.MapBuilder;
import data.modeling.adt.mappers.registries.FromAdtMapperRegistry;
import data.modeling.adt.typedefs.AnyOfType;
import data.modeling.adt.typedefs.ProductType;
import data.modeling.adt.util.LambdaExceptionUtil;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static data.modeling.adt.util.StreamExtensions.toMap;

public class JsonSchemaAnyOfMapper extends JsonSchemaMapper<AnyOfType> {

    private FromAdtMapperRegistry fromAdtMapperRegistry;

    public JsonSchemaAnyOfMapper(FromAdtMapperRegistry fromAdtMapperRegistry) {

        this.fromAdtMapperRegistry = fromAdtMapperRegistry;
    }

    @Override
    public boolean canMap(AnyOfType value) {
        return true;
    }

    @Override
    public Stream<Map.Entry<String, Object>> fromAdt(AnyOfType type) throws AdtException {
        MapBuilder mapBuilder = MapBuilder.create();
        List<Map<String, Object>> anyOf = new LinkedList<>();
        mapBuilder.put("type", "object"); // is it really an object?
        mapBuilder.put("anyOf", anyOf);

        Map<String, Object> allOfItemMap = toMap((Stream<Map.Entry<String, Object>>) fromAdtMapperRegistry
                .getMapper(JsonSchemaObjectMapper.class).fromAdt(type));
        List<String> required = (List<String>)allOfItemMap.remove("required");
        anyOf.add(allOfItemMap);
        if(null != required && !required.isEmpty()) {
            mapBuilder.put("required", required);
        }
        return mapBuilder.build().entrySet().stream();
    }
}
