package data.modeling.adt.mappers.jsonschemadraft7ToAdt.mappers;

import data.modeling.adt.typedefs.Int32Type;

import java.util.Map;

public class JsonSchemaIntegerMapper extends JsonSchemaMapper<Map<String, Object>, Int32Type> {
    @Override
    public boolean canMap(Map<String, Object> value) {
        return value.containsKey("type") && value.get("type").equals("integer");
    }

    @Override
    public Int32Type toAdt(Map<String, Object> value) {
        value.remove("type");
        return new Int32Type();
    }
}
