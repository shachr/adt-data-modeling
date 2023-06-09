package data.modeling.adt.mappers.jsonschemadraft7ToAdt.mappers;

import data.modeling.adt.SchemaContext;
import data.modeling.adt.exceptions.AdtException;
import data.modeling.adt.mappers.jsonschemadraft7ToAdt.annotations.JsonSchemaAnnotation;
import data.modeling.adt.mappers.jsonschemadraft7ToAdt.exceptions.JsonSchemaMissingId;
import data.modeling.adt.mappers.registries.ToAdtMapperRegistry;
import data.modeling.adt.typedefs.ComplexType;
import data.modeling.adt.typedefs.TypeDefinition;
import data.modeling.adt.util.LambdaExceptionUtil;

import java.util.Map;
import java.util.Objects;

public class JsonSchemaMainMapper extends JsonSchemaMapper<Map<String, Object>, TypeDefinition> {

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
    public TypeDefinition toAdt(Map<String, Object> value) throws AdtException {
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

    private TypeDefinition registerNamedType(String name, Map<String, Object> map) throws AdtException {
        ComplexType anyType = (ComplexType)toAdtMapperRegistry.toAdt(map);
        TypeDefinition typeDefinition = new TypeDefinition(name, anyType);
        map.keySet().forEach(key -> {
            typeDefinition.getAnnotations().add(new JsonSchemaAnnotation(key, map.get(key)));
        });
        schemaContext.registerNamedType(typeDefinition);
        return typeDefinition;
    }
}
