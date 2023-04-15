package data.modeling.adt.mappers.jsonschemadraft7FromAdt.mappers;

import data.modeling.adt.SchemaContext;
import data.modeling.adt.exceptions.AdtException;
import data.modeling.adt.mappers.jsonschemadraft7FromAdt.util.MapBuilder;
import data.modeling.adt.mappers.jsonschemadraft7FromAdt.util.MultipleOfConverter;
import data.modeling.adt.mappers.registries.FromAdtMapperRegistry;
import data.modeling.adt.typedefs.*;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

public class JsonSchemaPrimitiveMapper extends JsonSchemaMapper<PrimitiveType> {

    public JsonSchemaPrimitiveMapper() {

    }

    @Override
    public boolean canMap(PrimitiveType value) {
        return value instanceof StringType || value instanceof NumericType || value instanceof BoolType;
    }

    @Override
    public Stream<Map.Entry<String, Object>> fromAdt(PrimitiveType type) throws AdtException {
        MapBuilder mapBuilder = new MapBuilder();
        if(type instanceof StringType) {
            mapBuilder.put("type", "string");
        } else if(type instanceof BoolType){
                mapBuilder.put("type", "boolean");
        } else if(type instanceof NumericType){
            if( type instanceof IntType){
                mapBuilder.put("type", "integer");
            } else {

                if(type instanceof DecimalType){
                    DecimalType decimalType = (DecimalType)type;

                    Set oneOf = new HashSet<Map<String, Object>>();
                    MapBuilder mapBuilder2 = new MapBuilder();
                    mapBuilder2.put("type", "string");
                    mapBuilder2.put("pattern", "^\\d+(\\.\\d{1," + decimalType.getScale() + "})?$");
                    oneOf.add(mapBuilder2.build());

                    mapBuilder2 = new MapBuilder();
                    mapBuilder2.put("type", "number");
                    mapBuilder2.put("multipleOf", MultipleOfConverter.toMultipleOf((DecimalType)type));
                    oneOf.add(mapBuilder2.build());
                    mapBuilder.put("oneOf", oneOf);

                } else {
                    mapBuilder.put("type", "number");
                }
            }
        }
        return mapBuilder.build().entrySet().stream();
    }
}
