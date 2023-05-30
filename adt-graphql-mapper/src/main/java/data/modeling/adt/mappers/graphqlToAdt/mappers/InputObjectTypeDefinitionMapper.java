package data.modeling.adt.mappers.graphqlToAdt.mappers;

import data.modeling.adt.annotations.documentation.Description;
import data.modeling.adt.mappers.registries.ToAdtMapperRegistry;
import data.modeling.adt.typedefs.FieldDefinition;
import data.modeling.adt.typedefs.TypeDefinition;
import data.modeling.adt.typedefs.ProductType;
import data.modeling.adt.util.LambdaExceptionUtil;
import graphql.language.*;
import graphql.schema.idl.TypeDefinitionRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class InputObjectTypeDefinitionMapper extends GraphQlSchemaMapper<InputObjectTypeDefinition, TypeDefinition> {
    private ToAdtMapperRegistry toAdtMapperRegistry;
    private TypeDefinitionRegistry typeDefinitionRegistry;

    public InputObjectTypeDefinitionMapper(ToAdtMapperRegistry toAdtMapperRegistry, TypeDefinitionRegistry typeDefinitionRegistry){

        this.toAdtMapperRegistry = toAdtMapperRegistry;
        this.typeDefinitionRegistry = typeDefinitionRegistry;
    }
    @Override
    public boolean canMap(InputObjectTypeDefinition value) {
        return true;
    }

    @Override
    public TypeDefinition toAdt(InputObjectTypeDefinition value) {
        typeDefinitionRegistry.inputObjectTypeExtensions().get(value.getName());
        // support object type extensions
        List<InputObjectTypeExtensionDefinition> extensionDefinitionList = typeDefinitionRegistry.inputObjectTypeExtensions().get(value.getName());
        if(Objects.isNull(extensionDefinitionList)){
            extensionDefinitionList = new ArrayList<>();
        }

        Stream<InputValueDefinition> extensionObjectTypes = extensionDefinitionList.stream()
                .map(InputObjectTypeExtensionDefinition::getInputValueDefinitions)
                .flatMap(List::stream);

        TypeDefinition typeDefinition = TypeDefinition.builder(value.getName(), ProductType.of(
                Stream.concat(value.getInputValueDefinitions().stream(), extensionObjectTypes)
                        .map(LambdaExceptionUtil.function(field -> (FieldDefinition)toAdtMapperRegistry.toAdt(field)))))
                .build();

        if(!Objects.isNull(value.getDescription())){
            typeDefinition.getAnnotations().add(new Description(value.getDescription().getContent()));
        }

        return typeDefinition;
    }
}
