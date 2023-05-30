package data.modeling.adt.mappers.graphqlToAdt.mappers;

import data.modeling.adt.exceptions.AdtException;
import data.modeling.adt.mappers.registries.ToAdtMapperRegistry;
import data.modeling.adt.typedefs.AnyType;
import data.modeling.adt.typedefs.NullValueType;
import graphql.language.NonNullType;

public class NonNullTypeMapper extends GraphQlSchemaMapper<NonNullType, AnyType> {

    private ToAdtMapperRegistry toAdtMapperRegistry;

    public NonNullTypeMapper(ToAdtMapperRegistry toAdtMapperRegistry){

        this.toAdtMapperRegistry = toAdtMapperRegistry;
    }
    @Override
    public boolean canMap(NonNullType value) {
        return true;
    }

    @Override
    public AnyType toAdt(NonNullType value) throws AdtException {
        NullValueType nullValueType = (NullValueType) toAdtMapperRegistry.toAdt(value.getType());
        return nullValueType.getItemType();
    }
}
