package data.modeling.adt.mappers.jsonschemadraft7FromAdt.mappers;

import data.modeling.adt.exceptions.AdtException;
import data.modeling.adt.mappers.jsonschemadraft7FromAdt.util.MapBuilder;
import data.modeling.adt.mappers.registries.FromAdtMapperRegistry;
import data.modeling.adt.typedefs.*;
import static data.modeling.adt.util.StreamExtensions.toMap;

import java.util.Map;
import java.util.stream.Stream;

public class JsonSchemaArrayMapper extends JsonSchemaMapper<ListType> {

    private FromAdtMapperRegistry fromAdtMapperRegistry;

    public JsonSchemaArrayMapper(FromAdtMapperRegistry fromAdtMapperRegistry) {

        this.fromAdtMapperRegistry = fromAdtMapperRegistry;
    }

    @Override
    public boolean canMap(ListType value) {
        return true;
    }

    @Override
    public Stream<Map.Entry<String, Object>> fromAdt(ListType type) throws AdtException {
        MapBuilder mapBuilder = new MapBuilder();
        mapBuilder.put("type", "array");
        mapBuilder.put("items", toMap(fromAdtMapperRegistry.fromAdt(type.getItemType())));
        if(type instanceof SetType){
            mapBuilder.put("uniqueItems", true);
        }
        return mapBuilder.build().entrySet().stream();
    }
}
