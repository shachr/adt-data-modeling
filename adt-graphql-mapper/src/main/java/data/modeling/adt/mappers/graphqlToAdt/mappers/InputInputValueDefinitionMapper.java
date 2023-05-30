package data.modeling.adt.mappers.graphqlToAdt.mappers;

import data.modeling.adt.annotations.documentation.Description;
import data.modeling.adt.exceptions.AdtException;
import data.modeling.adt.mappers.registries.ToAdtMapperRegistry;
import data.modeling.adt.typedefs.FieldDefinition;
import graphql.language.InputValueDefinition;

import java.util.Objects;

public class InputInputValueDefinitionMapper extends GraphQlSchemaMapper<InputValueDefinition, FieldDefinition> {
    private ToAdtMapperRegistry toAdtMapperRegistry;

    public InputInputValueDefinitionMapper(ToAdtMapperRegistry toAdtMapperRegistry){

        this.toAdtMapperRegistry = toAdtMapperRegistry;
    }
    @Override
    public boolean canMap(InputValueDefinition value) {
        return true;
    }

    @Override
    public FieldDefinition toAdt(InputValueDefinition value) throws AdtException {
        FieldDefinition fieldDefinition = FieldDefinition.builder(value.getName(), toAdtMapperRegistry.toAdt(value.getType())).build();

        if(!Objects.isNull(value.getDescription())){
            fieldDefinition.getAnnotations().add(new Description(value.getDescription().getContent()));
        }

        return fieldDefinition;
    }
}
