package data.modeling.adt.mappers.jsonschemadraft7ToAdt.mappers;

import data.modeling.adt.mappers.registries.ToAdtMapperRegistry;
import data.modeling.adt.typedefs.AnyOfType;
import data.modeling.adt.typedefs.AnyType;
import data.modeling.adt.typedefs.FieldType;
import data.modeling.adt.typedefs.ProductType;
import data.modeling.adt.mappers.jsonschemadraft7ToAdt.exceptions.JsonSchemaAnyOfNotSupported;
import data.modeling.adt.util.LambdaExceptionUtil;

import java.util.*;
import java.util.stream.Collectors;

public class JsonSchemaAnyOfMapper extends JsonSchemaMapper<Map<String, Object>, AnyOfType> {
    private ToAdtMapperRegistry toAdtMapperRegistry;

    public JsonSchemaAnyOfMapper(ToAdtMapperRegistry toAdtMapperRegistry) {

        this.toAdtMapperRegistry = toAdtMapperRegistry;
    }

    @Override
    public boolean canMap(Map<String, Object> value) {
        return value.containsKey("anyOf");
    }

    @Override
    public AnyOfType toAdt(Map<String, Object> value) throws JsonSchemaAnyOfNotSupported {
        List<Map<String, Object>> anyOf = (List<Map<String, Object>>)value.remove("anyOf");
        Set<AnyType> typesFound = anyOf.stream()
                .map(LambdaExceptionUtil.function(item-> toAdtMapperRegistry.toAdt(item)))
                .collect(Collectors.toCollection(LinkedHashSet::new));

        AnyOfType anyOfType = new AnyOfType(typesFound);
        return anyOfType;
    }
}
