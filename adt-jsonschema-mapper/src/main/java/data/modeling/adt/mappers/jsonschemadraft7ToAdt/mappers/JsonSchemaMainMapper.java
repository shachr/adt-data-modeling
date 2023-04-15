package data.modeling.adt.mappers.jsonschemadraft7ToAdt.mappers;

import data.modeling.adt.SchemaContext;
import data.modeling.adt.exceptions.AdtException;
import data.modeling.adt.mappers.jsonschemadraft7ToAdt.annotations.JsonSchemaAnnotation;
import data.modeling.adt.mappers.registries.ToAdtMapperRegistry;
import data.modeling.adt.typedefs.NamedType;

import java.util.Map;

public class JsonSchemaMainMapper extends data.modeling.adt.mappers.jsonschemadraft7ToAdt.mappers.JsonSchemaMapper<NamedType> {

    private ToAdtMapperRegistry toAdtMapperRegistry;
    private SchemaContext schemaContext;

    public JsonSchemaMainMapper(ToAdtMapperRegistry toAdtMapperRegistry, SchemaContext schemaContext) {

        this.toAdtMapperRegistry = toAdtMapperRegistry;
        this.schemaContext = schemaContext;
    }

    @Override
    public boolean canMap(Map<String, Object> value) {
        return value.containsKey("$id");
    }

    @Override
    public NamedType toAdt(Map<String, Object> value) throws AdtException {
        String id = (String)value.get("$id");
        NamedType namedType = new NamedType(id, toAdtMapperRegistry.toAdt(value));
        value.remove("definitions");
        value.keySet().forEach(key->{
            namedType.getAnnotations().add(new JsonSchemaAnnotation(key, value.get(key)));
        });
        schemaContext.registerNamedType(namedType);
        return namedType;
    }
}
