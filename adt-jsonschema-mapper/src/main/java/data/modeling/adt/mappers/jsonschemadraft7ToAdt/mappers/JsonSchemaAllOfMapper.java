package data.modeling.adt.mappers.jsonschemadraft7ToAdt.mappers;

import data.modeling.adt.SchemaContext;
import data.modeling.adt.exceptions.AdtException;
import data.modeling.adt.mappers.registries.ToAdtMapperRegistry;
import data.modeling.adt.typedefs.AllOfType;
import data.modeling.adt.util.LambdaExceptionUtil;

import java.util.*;

public class JsonSchemaAllOfMapper extends JsonSchemaMapper<Map<String, Object>, AllOfType> {

    private ToAdtMapperRegistry toAdtMapperRegistry;
    private SchemaContext schemaContext;

    public JsonSchemaAllOfMapper(ToAdtMapperRegistry toAdtMapperRegistry, SchemaContext schemaContext) {

        this.toAdtMapperRegistry = toAdtMapperRegistry;
        this.schemaContext = schemaContext;
    }

    @Override
    public boolean canMap(Map<String, Object> value) {
        return value.containsKey("allOf");
    }

    @Override
    public AllOfType toAdt(Map<String, Object> value) throws AdtException {
        value.remove("type");
        List<Object> allOf = (List<Object>) value.remove("allOf");
        return new AllOfType(allOf.stream().map(LambdaExceptionUtil.function(item ->
                toAdtMapperRegistry.toAdt(item))));
    }
}
