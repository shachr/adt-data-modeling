package data.modeling.adt.mappers.graphqlToAdt.mappers;

import data.modeling.adt.annotations.documentation.Description;
import data.modeling.adt.exceptions.AdtException;
import data.modeling.adt.mappers.registries.ToAdtMapperRegistry;
import data.modeling.adt.typedefs.FieldType;
import data.modeling.adt.typedefs.NamedType;
import data.modeling.adt.typedefs.ProductType;
import data.modeling.adt.util.LambdaExceptionUtil;
import graphql.language.ObjectTypeDefinition;
import graphql.schema.GraphQLObjectType;

import java.util.Objects;

public class ObjectTypeMapper extends GraphQlSchemaMapper<ObjectTypeDefinition, NamedType> {
    private ToAdtMapperRegistry toAdtMapperRegistry;

    public ObjectTypeMapper(ToAdtMapperRegistry toAdtMapperRegistry){

        this.toAdtMapperRegistry = toAdtMapperRegistry;
    }
    @Override
    public boolean canMap(ObjectTypeDefinition value) {
        return true;
    }

    @Override
    public NamedType toAdt(ObjectTypeDefinition value) {
        NamedType namedType = NamedType.builder(value.getName(), ProductType.of(
                value.getFieldDefinitions().stream()
                        .map(LambdaExceptionUtil.function(field -> (FieldType)toAdtMapperRegistry.toAdt(field)))))
                .build();

        if(!Objects.isNull(value.getDescription())){
            namedType.getAnnotations().add(new Description(value.getDescription().getContent()));
        }
        return namedType;
    }
}
