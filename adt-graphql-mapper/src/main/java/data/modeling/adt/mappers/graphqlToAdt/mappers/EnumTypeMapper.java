package data.modeling.adt.mappers.graphqlToAdt.mappers;

import data.modeling.adt.annotations.documentation.Description;
import data.modeling.adt.exceptions.AdtException;
import data.modeling.adt.mappers.registries.ToAdtMapperRegistry;
import data.modeling.adt.typedefs.*;
import graphql.language.EnumTypeDefinition;
import graphql.language.FieldDefinition;
import graphql.language.NonNullType;
import graphql.language.Type;

import java.util.Objects;

public class EnumTypeMapper extends GraphQlSchemaMapper<EnumTypeDefinition, NamedType> {

    private ToAdtMapperRegistry toAdtMapperRegistry;

    public EnumTypeMapper(ToAdtMapperRegistry toAdtMapperRegistry){

        this.toAdtMapperRegistry = toAdtMapperRegistry;
    }
    @Override
    public boolean canMap(EnumTypeDefinition value) {
        return true;
    }

    @Override
    public NamedType toAdt(EnumTypeDefinition value) throws AdtException {
        String name = value.getName();
        EnumType enumType = EnumType.of(new StringType(),
            value.getEnumValueDefinitions().stream()
                    .map(enumValueDefinition ->
                            new EnumType.EnumItemType(
                                    enumValueDefinition.getName(),
                                    StringType.constantOf(enumValueDefinition.getName())))
        );


        return NamedType.builder(name, enumType).build();
    }

}
