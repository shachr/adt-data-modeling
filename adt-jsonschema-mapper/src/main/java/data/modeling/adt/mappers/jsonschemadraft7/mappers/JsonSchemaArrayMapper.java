package data.modeling.adt.mappers.jsonschemadraft7.mappers;

import data.modeling.adt.SchemaContext;
import data.modeling.adt.abstraction.annotations.GenericAnnotation;
import data.modeling.adt.abstraction.exceptions.AdtException;
import data.modeling.adt.abstraction.registries.ToAdtMapperRegistry;
import data.modeling.adt.abstraction.typedefs.ListType;

import java.util.Map;

public class JsonSchemaArrayMapper extends data.modeling.adt.mappers.jsonschemadraft7.mappers.JsonSchemaMapper<ListType> {

    private ToAdtMapperRegistry toAdtMapperRegistry;
    private SchemaContext schemaContext;

    public JsonSchemaArrayMapper(ToAdtMapperRegistry toAdtMapperRegistry, SchemaContext schemaContext) {

        this.toAdtMapperRegistry = toAdtMapperRegistry;
        this.schemaContext = schemaContext;
    }

    @Override
    public boolean canMap(Map<String, Object> value) {
        return value.containsKey("type") && value.get("type").equals("array") ||
                value.containsKey("items");
    }

    @Override
    public ListType toAdt(Map<String, Object> value) throws AdtException {
        // todo: add logic
        // todo: support null + type, do not support any mixture of types.
        if(value.containsKey("items")) {
            Map<String, Object> items = (Map<String, Object>) value.remove("items");
            return new ListType(toAdtMapperRegistry.toAdt(items));
        }

        // todo: other scenarios ..
        throw new AdtException("not supported");
    }
}
