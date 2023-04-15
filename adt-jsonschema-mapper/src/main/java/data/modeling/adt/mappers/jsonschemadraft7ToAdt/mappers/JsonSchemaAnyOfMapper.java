package data.modeling.adt.mappers.jsonschemadraft7ToAdt.mappers;

import data.modeling.adt.mappers.registries.ToAdtMapperRegistry;
import data.modeling.adt.typedefs.ProductType;
import data.modeling.adt.mappers.jsonschemadraft7ToAdt.exceptions.JsonSchemaAnyOfNotSupported;

import java.util.Map;

public class JsonSchemaAnyOfMapper extends data.modeling.adt.mappers.jsonschemadraft7ToAdt.mappers.JsonSchemaMapper<ProductType> {
    private ToAdtMapperRegistry toAdtMapperRegistry;

    public JsonSchemaAnyOfMapper(ToAdtMapperRegistry toAdtMapperRegistry) {

        this.toAdtMapperRegistry = toAdtMapperRegistry;
    }

    @Override
    public boolean canMap(Map<String, Object> value) {
        return value.containsKey("anyOf");
    }

    @Override
    public ProductType toAdt(Map<String, Object> value) throws JsonSchemaAnyOfNotSupported {
        // todo: add logic
        throw new JsonSchemaAnyOfNotSupported();
    }
}
