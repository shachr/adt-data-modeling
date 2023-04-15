package data.modeling.adt.mappers.jsonschemadraft7FromAdt.mappers;

import data.modeling.adt.SchemaContext;
import data.modeling.adt.exceptions.AdtException;
import data.modeling.adt.mappers.jsonschemadraft7FromAdt.util.MapBuilder;
import data.modeling.adt.mappers.registries.FromAdtMapperRegistry;
import data.modeling.adt.typedefs.ProductType;
import data.modeling.adt.typedefs.UnionType;
import data.modeling.adt.util.LambdaExceptionUtil;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

public class JsonSchemaOneOfMapper extends JsonSchemaMapper<UnionType> {

    private FromAdtMapperRegistry fromAdtMapperRegistry;

    public JsonSchemaOneOfMapper(FromAdtMapperRegistry fromAdtMapperRegistry) {

        this.fromAdtMapperRegistry = fromAdtMapperRegistry;
    }

    @Override
    public boolean canMap(UnionType value) {
        return true;
    }

    @Override
    public Stream<Map.Entry<String, Object>> fromAdt(UnionType type) throws AdtException {
        MapBuilder map = MapBuilder.create();
        Set<Map<String, Object>> oneOf = new HashSet<>();
        type.getTypes().forEach(LambdaExceptionUtil.consumer(innerType ->
                oneOf.add(fromAdtMapperRegistry.fromAdt(innerType))));
        map.put("oneOf", oneOf);
        return map.build().entrySet().stream();
    }
}
