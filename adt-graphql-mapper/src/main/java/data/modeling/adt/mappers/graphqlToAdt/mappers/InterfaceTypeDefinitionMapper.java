package data.modeling.adt.mappers.graphqlToAdt.mappers;

import data.modeling.adt.annotations.documentation.Description;
import data.modeling.adt.mappers.registries.ToAdtMapperRegistry;
import data.modeling.adt.typedefs.InterfaceDefinition;
import data.modeling.adt.typedefs.ProductType;
import data.modeling.adt.util.LambdaExceptionUtil;
import graphql.language.InterfaceTypeDefinition;
import graphql.language.InterfaceTypeExtensionDefinition;
import graphql.schema.idl.TypeDefinitionRegistry;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InterfaceTypeDefinitionMapper extends GraphQlSchemaMapper<InterfaceTypeDefinition, InterfaceDefinition> {
    private ToAdtMapperRegistry toAdtMapperRegistry;
    private TypeDefinitionRegistry typeDefinitionRegistry;

    public InterfaceTypeDefinitionMapper(ToAdtMapperRegistry toAdtMapperRegistry, TypeDefinitionRegistry typeDefinitionRegistry){

        this.toAdtMapperRegistry = toAdtMapperRegistry;
        this.typeDefinitionRegistry = typeDefinitionRegistry;
    }
    @Override
    public boolean canMap(InterfaceTypeDefinition value) {
        return true;
    }

    @Override
    public InterfaceDefinition toAdt(InterfaceTypeDefinition value) {
        List<InterfaceTypeExtensionDefinition> extensionDefinitionList = typeDefinitionRegistry.interfaceTypeExtensions().get(value.getName());
        if(Objects.isNull(extensionDefinitionList)){
            extensionDefinitionList = new ArrayList<>();
        }

        Stream<graphql.language.FieldDefinition> extensionInterfaces = extensionDefinitionList.stream()
                .map(InterfaceTypeDefinition::getFieldDefinitions)
                .flatMap(List::stream);


        InterfaceDefinition interfaceDefinition = new InterfaceDefinition(value.getName(),
                ProductType.of(Stream.concat(value.getFieldDefinitions().stream(), extensionInterfaces)
                        .map(LambdaExceptionUtil.function(field ->
                                (data.modeling.adt.typedefs.FieldDefinition)toAdtMapperRegistry.toAdt(field)))));

        if(!Objects.isNull(value.getDescription())){
            interfaceDefinition.getAnnotations().add(new Description(value.getDescription().getContent()));
        }

        ProductType productType = (ProductType) interfaceDefinition.getType();
        Collection implements_ = value.getImplements().stream()
                .map(LambdaExceptionUtil.function(toAdtMapperRegistry::toAdt))
                .collect(Collectors.toCollection(LinkedHashSet::new));
        productType.getImplements().addAll(implements_);
        return interfaceDefinition;
    }
}
