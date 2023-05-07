package data.modeling.adt.mappers.jsonschemadraft7ToAdt.mappers;

import data.modeling.adt.exceptions.AdtException;
import data.modeling.adt.mappers.registries.ToAdtMapperRegistry;
import data.modeling.adt.typedefs.ConstantPrimitiveType;
import data.modeling.adt.typedefs.EnumType;
import data.modeling.adt.typedefs.PrimitiveType;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class JsonSchemaEnumMapper extends JsonSchemaMapper<EnumType> {
    private ToAdtMapperRegistry toAdtMapperRegistry;

    public JsonSchemaEnumMapper(ToAdtMapperRegistry toAdtMapperRegistry) {

        this.toAdtMapperRegistry = toAdtMapperRegistry;
    }
    @Override
    public boolean canMap(Map<String, Object> value) {
        return value.containsKey("enum");
    }

    @Override
    public EnumType toAdt(Map<String, Object> value) throws AdtException {
        List<Object> enumValues = (List<Object>)value.remove("enum");
        PrimitiveType baseType = (PrimitiveType)toAdtMapperRegistry.toAdt(value);
        return new EnumType( baseType,
            enumValues.stream()
                    .map(enumValue -> new ConstantPrimitiveType(baseType, enumValue))
                    .collect(Collectors.toSet())
        );

    }
}
