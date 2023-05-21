package data.modeling.adt.mappers.graphqlToAdt.mappers;

import data.modeling.adt.annotations.documentation.Description;
import data.modeling.adt.exceptions.AdtException;
import data.modeling.adt.mappers.registries.ToAdtMapperRegistry;
import data.modeling.adt.typedefs.*;
import data.modeling.adt.util.LambdaExceptionUtil;
import graphql.language.FieldDefinition;
import graphql.language.NonNullType;
import graphql.language.ObjectTypeDefinition;
import graphql.language.ObjectTypeExtensionDefinition;
import graphql.schema.idl.TypeDefinitionRegistry;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ObjectTypeDefinitionMapper extends GraphQlSchemaMapper<ObjectTypeDefinition, NamedType> {
    private ToAdtMapperRegistry toAdtMapperRegistry;
    private TypeDefinitionRegistry typeDefinitionRegistry;

    public ObjectTypeDefinitionMapper(ToAdtMapperRegistry toAdtMapperRegistry, TypeDefinitionRegistry typeDefinitionRegistry){

        this.toAdtMapperRegistry = toAdtMapperRegistry;
        this.typeDefinitionRegistry = typeDefinitionRegistry;
    }
    @Override
    public boolean canMap(ObjectTypeDefinition value) {
        return true;
    }

    @Override
    public NamedType toAdt(ObjectTypeDefinition value) throws AdtException {
        // support object type extensions
        List<ObjectTypeExtensionDefinition> extensionDefinitionList = typeDefinitionRegistry.objectTypeExtensions().get(value.getName());
        if(Objects.isNull(extensionDefinitionList)){
            extensionDefinitionList = new ArrayList<>();
        }
        Stream<FieldDefinition> extensionFields = extensionDefinitionList.stream()
                .map(ObjectTypeDefinition::getFieldDefinitions)
                .flatMap(List::stream);

        NamedType namedType = NamedType.builder(value.getName(), ProductType.of(
                        Stream.concat(value.getFieldDefinitions().stream(), extensionFields)
                        .map(LambdaExceptionUtil.function(field -> (FieldType)toAdtMapperRegistry.toAdt(field)))))
                .build();

        ProductType productType = (ProductType)namedType.getType();
        Collection<ReferenceNamedType> implements_ = value.getImplements().stream()
                .map(NonNullType::new)
                .map(LambdaExceptionUtil.function(toAdtMapperRegistry::toAdt))
                .map(anyType -> (ReferenceNamedType)anyType)
                .collect(Collectors.toCollection(LinkedHashSet::new));
        productType.getImplements().addAll(implements_);

        if(!Objects.isNull(value.getDescription())){
            namedType.getAnnotations().add(new Description(value.getDescription().getContent()));
        }

        return namedType;
    }
}
