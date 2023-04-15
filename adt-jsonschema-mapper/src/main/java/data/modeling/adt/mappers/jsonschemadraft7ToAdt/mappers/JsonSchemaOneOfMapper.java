package data.modeling.adt.mappers.jsonschemadraft7ToAdt.mappers;

import data.modeling.adt.SchemaContext;
import data.modeling.adt.mappers.registries.ToAdtMapperRegistry;
import data.modeling.adt.typedefs.AnyType;
import data.modeling.adt.typedefs.UnionType;
import data.modeling.adt.util.LambdaExceptionUtil;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class JsonSchemaOneOfMapper extends JsonSchemaMapper<UnionType> {

    private ToAdtMapperRegistry toAdtMapperRegistry;
    private SchemaContext schemaContext;

    public JsonSchemaOneOfMapper(ToAdtMapperRegistry toAdtMapperRegistry, SchemaContext schemaContext) {

        this.toAdtMapperRegistry = toAdtMapperRegistry;
        this.schemaContext = schemaContext;
    }

    @Override
    public boolean canMap(Map<String, Object> value) {
        return value.containsKey("oneOf");
    }

    @Override
    public UnionType toAdt(Map<String, Object> value) {
        // todo: add logic
        value.remove("type");
        List<Object> oneOf = (List<Object>)value.remove("oneOf");
        // todo: identify named types and register them
        List<AnyType> listAnyType = oneOf.stream()
                .map(LambdaExceptionUtil.function(item->
                        toAdtMapperRegistry.findToMapper(item).get().toAdt(item)))
                .collect(Collectors.toList());

        return new UnionType(listAnyType);
    }
}
