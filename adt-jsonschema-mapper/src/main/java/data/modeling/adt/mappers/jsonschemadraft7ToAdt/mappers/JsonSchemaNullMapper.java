package data.modeling.adt.mappers.jsonschemadraft7ToAdt.mappers;

import data.modeling.adt.exceptions.AdtException;
import data.modeling.adt.mappers.jsonschemadraft7ToAdt.exceptions.JsonSchemaAnyOfNotSupported;
import data.modeling.adt.mappers.jsonschemadraft7ToAdt.exceptions.JsonSchemaTypeNotSupported;
import data.modeling.adt.mappers.registries.FromAdtMapperRegistry;
import data.modeling.adt.mappers.registries.ToAdtMapperRegistry;
import data.modeling.adt.typedefs.BoolType;
import data.modeling.adt.typedefs.NullValueType;

import java.util.List;
import java.util.Map;

public class JsonSchemaNullMapper extends JsonSchemaMapper<NullValueType> {

    private ToAdtMapperRegistry registry;

    public JsonSchemaNullMapper(ToAdtMapperRegistry registry){

        this.registry = registry;
    }
    @Override
    public boolean canMap(Map<String, Object> value) {
        return value.containsKey("type") && value.get("type") instanceof List && ((List)value.get("type")).contains("null");
    }

    @Override
    public NullValueType toAdt(Map<String, Object> value) throws AdtException {
        List<String> types = (List<String>)value.get("type");
        types.remove("null");
        if(types.size()>1)
            throw new JsonSchemaTypeNotSupported();
        value.put("type", types.get(0));
        return new NullValueType(registry.toAdt(value));
    }
}
