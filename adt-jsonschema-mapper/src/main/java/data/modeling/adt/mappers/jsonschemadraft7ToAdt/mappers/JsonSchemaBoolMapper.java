package data.modeling.adt.mappers.jsonschemadraft7ToAdt.mappers;

import data.modeling.adt.typedefs.BoolType;

import java.util.Map;

public class JsonSchemaBoolMapper extends JsonSchemaMapper<Map<String, Object>, BoolType> {
    @Override
    public boolean canMap(Map<String, Object> value) {
        return value.containsKey("type") && value.get("type").equals("boolean");
    }

    @Override
    public BoolType toAdt(Map<String, Object> value) {
        value.remove("type");
        return new BoolType();
    }
}
