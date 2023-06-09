package data.modeling.adt.mappers.jsonschemadraft7ToAdt.mappers;

import data.modeling.adt.exceptions.AdtException;
import data.modeling.adt.mappers.registries.ToAdtMapperRegistry;
import data.modeling.adt.typedefs.ConstantPrimitiveType;
import data.modeling.adt.typedefs.EnumType;
import data.modeling.adt.typedefs.ScalarType;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class JsonSchemaEnumMapper extends JsonSchemaMapper<Map<String, Object>, EnumType> {
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
        ScalarType baseType = (ScalarType)toAdtMapperRegistry.toAdt(value);
        return new EnumType( baseType,
            enumValues.stream()
                    .map(enumValue -> new EnumType.EnumItemType(enumValue.toString(), new ConstantPrimitiveType(baseType, enumValue)))
                    .collect(Collectors.toSet())
        );

    }
}
