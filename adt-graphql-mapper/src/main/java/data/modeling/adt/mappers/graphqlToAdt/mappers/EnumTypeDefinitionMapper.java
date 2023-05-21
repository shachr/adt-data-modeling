package data.modeling.adt.mappers.graphqlToAdt.mappers;

import data.modeling.adt.exceptions.AdtException;
import data.modeling.adt.mappers.registries.ToAdtMapperRegistry;
import data.modeling.adt.typedefs.*;
import graphql.language.*;
import graphql.schema.idl.TypeDefinitionRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class EnumTypeDefinitionMapper extends GraphQlSchemaMapper<EnumTypeDefinition, NamedType> {

    private ToAdtMapperRegistry toAdtMapperRegistry;
    private TypeDefinitionRegistry typeDefinitionRegistry;

    public EnumTypeDefinitionMapper(ToAdtMapperRegistry toAdtMapperRegistry, TypeDefinitionRegistry typeDefinitionRegistry){

        this.toAdtMapperRegistry = toAdtMapperRegistry;
        this.typeDefinitionRegistry = typeDefinitionRegistry;
    }
    @Override
    public boolean canMap(EnumTypeDefinition value) {
        return true;
    }

    @Override
    public NamedType toAdt(EnumTypeDefinition value) throws AdtException {
        typeDefinitionRegistry.enumTypeExtensions().get(value.getName());
        // support object type extensions
        List<EnumTypeExtensionDefinition> extensionDefinitionList = typeDefinitionRegistry.enumTypeExtensions().get(value.getName());
        if(Objects.isNull(extensionDefinitionList)){
            extensionDefinitionList = new ArrayList<>();
        }

        Stream<EnumValueDefinition> extensionEnumValues = extensionDefinitionList.stream()
                .map(EnumTypeDefinition::getEnumValueDefinitions)
                .flatMap(List::stream);

        String name = value.getName();
        EnumType enumType = EnumType.of(new StringType(),
            Stream.concat(value.getEnumValueDefinitions().stream(), extensionEnumValues)
                    .map(enumValueDefinition ->
                            new EnumType.EnumItemType(
                                    enumValueDefinition.getName(),
                                    StringType.constantOf(enumValueDefinition.getName())))
        );


        return NamedType.builder(name, enumType).build();
    }

}
