package data.modeling.adt.mappers.graphqlToAdt.mappers;

import data.modeling.adt.annotations.documentation.Description;
import data.modeling.adt.annotations.sdl.TypeDeclaration;
import data.modeling.adt.enums.TypeDeclarations;
import data.modeling.adt.mappers.registries.ToAdtMapperRegistry;
import data.modeling.adt.typedefs.FieldType;
import data.modeling.adt.typedefs.NamedType;
import data.modeling.adt.typedefs.ProductType;
import data.modeling.adt.util.LambdaExceptionUtil;
import graphql.language.*;
import graphql.schema.idl.TypeDefinitionRegistry;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InterfaceTypeDefinitionMapper extends GraphQlSchemaMapper<InterfaceTypeDefinition, NamedType> {
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
    public NamedType toAdt(InterfaceTypeDefinition value) {
        List<InterfaceTypeExtensionDefinition> extensionDefinitionList = typeDefinitionRegistry.interfaceTypeExtensions().get(value.getName());
        if(Objects.isNull(extensionDefinitionList)){
            extensionDefinitionList = new ArrayList<>();
        }

        Stream<FieldDefinition> extensionInterfaces = extensionDefinitionList.stream()
                .map(InterfaceTypeDefinition::getFieldDefinitions)
                .flatMap(List::stream);


        NamedType namedType = NamedType.builder(value.getName(), ProductType.of(
                Stream.concat(value.getFieldDefinitions().stream(), extensionInterfaces)
                        .map(LambdaExceptionUtil.function(field -> (FieldType)toAdtMapperRegistry.toAdt(field)))))
                .build();

        namedType.getAnnotations().add(new TypeDeclaration(TypeDeclarations.Interface));
        if(!Objects.isNull(value.getDescription())){
            namedType.getAnnotations().add(new Description(value.getDescription().getContent()));
        }

        ProductType productType = (ProductType)namedType.getType();
        Collection implements_ = value.getImplements().stream()
                .map(LambdaExceptionUtil.function(toAdtMapperRegistry::toAdt))
                .collect(Collectors.toCollection(LinkedHashSet::new));
        productType.getImplements().addAll(implements_);
        return namedType;
    }
}
