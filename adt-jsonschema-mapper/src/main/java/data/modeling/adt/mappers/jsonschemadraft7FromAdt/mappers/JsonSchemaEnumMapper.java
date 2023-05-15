package data.modeling.adt.mappers.jsonschemadraft7FromAdt.mappers;

import data.modeling.adt.exceptions.AdtException;
import data.modeling.adt.mappers.jsonschemadraft7FromAdt.util.MapBuilder;
import data.modeling.adt.mappers.registries.FromAdtMapperRegistry;
import data.modeling.adt.typedefs.EnumType;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JsonSchemaEnumMapper extends JsonSchemaMapper<EnumType> {

    private FromAdtMapperRegistry fromAdtMapperRegistry;

    public JsonSchemaEnumMapper(FromAdtMapperRegistry fromAdtMapperRegistry) {

        this.fromAdtMapperRegistry = fromAdtMapperRegistry;
    }

    @Override
    public boolean canMap(EnumType value) {
        return true;
    }

    @Override
    public Stream<Map.Entry<String, Object>> fromAdt(EnumType type) throws AdtException {
        MapBuilder mapBuilder = MapBuilder.create();
        mapBuilder.merge((Stream<Map.Entry<String, Object>>)fromAdtMapperRegistry.fromAdt(type.getBaseType()));
        mapBuilder.put("enum", type.getItems().stream().map(constantPrimitiveType ->
                constantPrimitiveType.value().getConstant()).collect(Collectors.toList()));
        return mapBuilder.build().entrySet().stream();
    }
}
