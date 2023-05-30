package data.modeling.adt.mappers.graphqlToAdt.mappers;

import data.modeling.adt.exceptions.AdtException;
import data.modeling.adt.mappers.registries.ToAdtMapperRegistry;
import data.modeling.adt.typedefs.NullValueType;
import graphql.language.ListType;

public class ListTypeMapper extends GraphQlSchemaMapper<ListType, NullValueType> {

    private ToAdtMapperRegistry toAdtMapperRegistry;

    public ListTypeMapper(ToAdtMapperRegistry toAdtMapperRegistry){

        this.toAdtMapperRegistry = toAdtMapperRegistry;
    }
    @Override
    public boolean canMap(ListType value) {
        return true;
    }

    @Override
    public NullValueType toAdt(ListType value) throws AdtException {
        return new NullValueType(new data.modeling.adt.typedefs.ListType(toAdtMapperRegistry.toAdt(value.getType())));
    }

}
