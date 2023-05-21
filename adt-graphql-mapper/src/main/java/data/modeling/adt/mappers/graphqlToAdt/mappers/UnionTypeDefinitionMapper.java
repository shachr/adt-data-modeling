package data.modeling.adt.mappers.graphqlToAdt.mappers;

import data.modeling.adt.annotations.documentation.Description;
import data.modeling.adt.exceptions.AdtException;
import data.modeling.adt.mappers.registries.ToAdtMapperRegistry;
import data.modeling.adt.typedefs.NamedType;
import data.modeling.adt.typedefs.UnionType;
import data.modeling.adt.util.LambdaExceptionUtil;
import graphql.language.*;
import graphql.schema.idl.TypeDefinitionRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class UnionTypeDefinitionMapper extends GraphQlSchemaMapper<UnionTypeDefinition, NamedType> {

    private ToAdtMapperRegistry toAdtMapperRegistry;
    private TypeDefinitionRegistry typeDefinitionRegistry;

    public UnionTypeDefinitionMapper(ToAdtMapperRegistry toAdtMapperRegistry, TypeDefinitionRegistry typeDefinitionRegistry){

        this.toAdtMapperRegistry = toAdtMapperRegistry;
        this.typeDefinitionRegistry = typeDefinitionRegistry;
    }
    @Override
    public boolean canMap(UnionTypeDefinition value) {
        return true;
    }

    @Override
    public NamedType toAdt(UnionTypeDefinition value) throws AdtException {
        List<UnionTypeExtensionDefinition> extensionDefinitionList = typeDefinitionRegistry.unionTypeExtensions().get(value.getName());
        if(Objects.isNull(extensionDefinitionList)){
            extensionDefinitionList = new ArrayList<>();
        }

        Stream<Type> extensionFields = extensionDefinitionList.stream()
                .map(UnionTypeDefinition::getMemberTypes)
                .flatMap(List::stream);

        NamedType namedType = new NamedType(value.getName(), UnionType.of(
                Stream.concat(value.getMemberTypes().stream(), extensionFields)
                .map(LambdaExceptionUtil.function(toAdtMapperRegistry::toAdt))));

        if(!Objects.isNull(value.getDescription()))
            namedType.getAnnotations().add(new Description(value.getDescription().getContent()));

        return namedType;
    }

}
