package data.modeling.adt.mappers.jsonschemadraft7FromAdt.mappers;

import data.modeling.adt.exceptions.AdtException;
import data.modeling.adt.mappers.registries.FromAdtMapperRegistry;
import data.modeling.adt.typedefs.NullValueType;

import static data.modeling.adt.util.StreamExtensions.toMap;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JsonSchemaNullMapper extends JsonSchemaMapper<NullValueType> {

    private FromAdtMapperRegistry fromAdtMapperRegistry;

    public JsonSchemaNullMapper(FromAdtMapperRegistry fromAdtMapperRegistry) {

        this.fromAdtMapperRegistry = fromAdtMapperRegistry;
    }

    @Override
    public boolean canMap(NullValueType value) {
        return true;
    }

    @Override
    public Stream<Map.Entry<String, Object>> fromAdt(NullValueType type) throws AdtException {
        Map<String,Object> map = toMap(fromAdtMapperRegistry.fromAdt(type.getItemType()));
        map.put("type", Stream.of("null", map.get("type")).collect(Collectors.toList()));
        return map.entrySet().stream();
    }
}
