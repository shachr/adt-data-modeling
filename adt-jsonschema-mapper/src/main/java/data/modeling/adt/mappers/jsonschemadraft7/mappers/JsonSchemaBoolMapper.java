package data.modeling.adt.mappers.jsonschemadraft7.mappers;

import data.modeling.adt.abstraction.typedefs.BoolType;

import java.util.Map;

public class JsonSchemaBoolMapper extends data.modeling.adt.mappers.jsonschemadraft7.mappers.JsonSchemaMapper<BoolType> {
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
