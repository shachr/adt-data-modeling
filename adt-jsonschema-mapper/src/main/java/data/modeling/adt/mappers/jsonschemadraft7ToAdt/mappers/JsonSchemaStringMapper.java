package data.modeling.adt.mappers.jsonschemadraft7ToAdt.mappers;

import data.modeling.adt.typedefs.StringType;

import java.util.Map;

public class JsonSchemaStringMapper extends JsonSchemaMapper<Map<String, Object>, StringType> {
    @Override
    public boolean canMap(Map<String, Object> value) {
        return value.containsKey("pattern") || (value.containsKey("type") && value.get("type").equals("string"));
    }

    @Override
    public StringType toAdt(Map<String, Object> value) {

        value.remove("type");
        return new StringType();
    }
}
