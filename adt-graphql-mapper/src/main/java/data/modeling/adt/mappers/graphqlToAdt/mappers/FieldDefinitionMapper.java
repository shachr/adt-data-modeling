package data.modeling.adt.mappers.graphqlToAdt.mappers;

import data.modeling.adt.annotations.documentation.Description;
import data.modeling.adt.exceptions.AdtException;
import data.modeling.adt.mappers.registries.ToAdtMapperRegistry;
import data.modeling.adt.typedefs.FieldDefinition;
import data.modeling.adt.typedefs.NullValueType;
import graphql.language.Type;

import java.util.Objects;

public class FieldDefinitionMapper extends GraphQlSchemaMapper<graphql.language.FieldDefinition, FieldDefinition> {

    private ToAdtMapperRegistry toAdtMapperRegistry;

    public FieldDefinitionMapper(ToAdtMapperRegistry toAdtMapperRegistry){

        this.toAdtMapperRegistry = toAdtMapperRegistry;
    }
    @Override
    public boolean canMap(graphql.language.FieldDefinition value) {
        return true;
    }

    @Override
    public FieldDefinition toAdt(graphql.language.FieldDefinition value) throws AdtException {
        Type type = value.getType();
        FieldDefinition fieldDefinition = FieldDefinition.builder(value.getName(), toAdtMapperRegistry.toAdt(type)).build();
        if(!(fieldDefinition.getType() instanceof NullValueType)) {
            fieldDefinition.setRequired(true);
        }
        if(!Objects.isNull(value.getDescription())){
            fieldDefinition.getAnnotations().add(new Description(value.getDescription().getContent()));
        }
        return fieldDefinition;
    }
}
