package data.modeling.adt.mappers.jsonschemadraft7ToAdt.mappers;

import data.modeling.adt.SchemaContext;
import data.modeling.adt.exceptions.AdtException;
import data.modeling.adt.mappers.jsonschemadraft7ToAdt.annotations.JsonSchemaAnnotation;
import data.modeling.adt.mappers.jsonschemadraft7ToAdt.exceptions.JsonSchemaMissingId;
import data.modeling.adt.mappers.registries.ToAdtMapperRegistry;
import data.modeling.adt.typedefs.AnyType;
import data.modeling.adt.typedefs.NamedType;
import data.modeling.adt.util.LambdaExceptionUtil;

import java.util.Map;
import java.util.Objects;

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

        if(Objects.isNull(id)){
            throw new JsonSchemaMissingId();
        }

        Map<String, Object> definitions = (Map<String, Object>)value.remove("definitions");
        if(!Objects.isNull(definitions)){
            definitions.entrySet().forEach(LambdaExceptionUtil.consumer(definition ->
                    registerNamedType(JsonSchemaRefMapper.createReferencedNamed(id, definition.getKey()), (Map<String, Object>)definition.getValue())));
        }
        return registerNamedType(id, value);
    }

    private NamedType registerNamedType(String name, Map<String, Object> map) throws AdtException {
        AnyType anyType = toAdtMapperRegistry.toAdt(map);
        NamedType namedType = new NamedType(name, anyType);
        map.keySet().forEach(key -> {
            namedType.getAnnotations().add(new JsonSchemaAnnotation(key, map.get(key)));
        });
        schemaContext.registerNamedType(namedType);
        return namedType;
    }
}
