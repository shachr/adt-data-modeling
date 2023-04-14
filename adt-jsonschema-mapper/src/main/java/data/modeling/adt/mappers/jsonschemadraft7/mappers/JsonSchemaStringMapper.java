package data.modeling.adt.mappers.jsonschemadraft7.mappers;

import data.modeling.adt.abstraction.typedefs.StringType;

import java.util.Map;

public class JsonSchemaStringMapper extends JsonSchemaMapper<StringType> {
    @Override
    public boolean canMap(Map<String, Object> value) {
        return value.containsKey("type") && value.get("type").equals("string");
    }

    @Override
    public StringType toAdt(Map<String, Object> value) {

        value.remove("type");
        return new StringType();
    }
}
