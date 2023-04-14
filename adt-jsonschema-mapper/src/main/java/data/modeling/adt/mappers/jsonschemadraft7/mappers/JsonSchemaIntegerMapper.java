package data.modeling.adt.mappers.jsonschemadraft7.mappers;

import data.modeling.adt.abstraction.typedefs.IntType;

import java.util.Map;

public class JsonSchemaIntegerMapper extends data.modeling.adt.mappers.jsonschemadraft7.mappers.JsonSchemaMapper<IntType> {
    @Override
    public boolean canMap(Map<String, Object> value) {
        return value.containsKey("type") && value.get("type").equals("integer");
    }

    @Override
    public IntType toAdt(Map<String, Object> value) {
        value.remove("type");
        return new IntType();
    }
}
