package data.modeling.adt.mappers.graphqlToAdt.mappers;

import data.modeling.adt.annotations.documentation.Description;
import data.modeling.adt.exceptions.AdtException;
import data.modeling.adt.mappers.registries.ToAdtMapperRegistry;
import data.modeling.adt.typedefs.FieldType;
import data.modeling.adt.typedefs.NamedType;
import data.modeling.adt.typedefs.ProductType;
import data.modeling.adt.util.LambdaExceptionUtil;
import graphql.language.InputObjectTypeDefinition;
import graphql.language.InputValueDefinition;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.stream.Collectors;

public class InputInputValueDefinitionMapper extends GraphQlSchemaMapper<InputValueDefinition, FieldType> {
    private ToAdtMapperRegistry toAdtMapperRegistry;

    public InputInputValueDefinitionMapper(ToAdtMapperRegistry toAdtMapperRegistry){

        this.toAdtMapperRegistry = toAdtMapperRegistry;
    }
    @Override
    public boolean canMap(InputValueDefinition value) {
        return true;
    }

    @Override
    public FieldType toAdt(InputValueDefinition value) throws AdtException {
        FieldType fieldType = FieldType.builder(value.getName(), toAdtMapperRegistry.toAdt(value.getType())).build();

        if(!Objects.isNull(value.getDescription())){
            fieldType.getAnnotations().add(new Description(value.getDescription().getContent()));
        }

        return fieldType;
    }
}
