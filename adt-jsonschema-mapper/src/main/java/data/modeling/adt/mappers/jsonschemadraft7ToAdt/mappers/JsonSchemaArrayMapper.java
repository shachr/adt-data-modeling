package data.modeling.adt.mappers.jsonschemadraft7ToAdt.mappers;

import data.modeling.adt.SchemaContext;
import data.modeling.adt.exceptions.AdtException;
import data.modeling.adt.mappers.registries.ToAdtMapperRegistry;
import data.modeling.adt.typedefs.AnyType;
import data.modeling.adt.typedefs.ListType;
import data.modeling.adt.typedefs.ObjectType;
import data.modeling.adt.typedefs.SetType;

import java.util.Map;
import java.util.Objects;

public class JsonSchemaArrayMapper extends data.modeling.adt.mappers.jsonschemadraft7ToAdt.mappers.JsonSchemaMapper<ListType> {

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
        value.remove("type");
        boolean hasPrefixItems = value.containsKey("prefixItems");
        Map<String, Object> items = (Map<String, Object>) value.remove("items");
        if(!hasPrefixItems && !Objects.isNull(items)) {
            Object isSet = value.remove("uniqueItems");
            AnyType itemType = toAdtMapperRegistry.toAdt(items);
            if (null != isSet && isSet.equals(true)) {
                return new SetType(itemType);
            }
            return new ListType(itemType);
        }

        return new ListType(new ObjectType());
    }
}
