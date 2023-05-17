package data.modeling.adt.mappers.graphqlToAdt.mappers;

import data.modeling.adt.annotations.documentation.Description;
import data.modeling.adt.exceptions.AdtException;
import data.modeling.adt.mappers.registries.ToAdtMapperRegistry;
import data.modeling.adt.typedefs.AnyType;
import data.modeling.adt.typedefs.FieldType;
import data.modeling.adt.typedefs.NullValueType;
import graphql.language.FieldDefinition;
import graphql.language.NonNullType;
import graphql.language.Type;
import graphql.language.TypeName;
import graphql.schema.GraphQLFieldDefinition;

import java.util.Objects;

public class FieldTypeMapper extends GraphQlSchemaMapper<FieldDefinition, FieldType> {

    private ToAdtMapperRegistry toAdtMapperRegistry;

    public FieldTypeMapper(ToAdtMapperRegistry toAdtMapperRegistry){

        this.toAdtMapperRegistry = toAdtMapperRegistry;
    }
    @Override
    public boolean canMap(FieldDefinition value) {
        return true;
    }

    @Override
    public FieldType toAdt(FieldDefinition value) throws AdtException {
        Type type = value.getType();
        FieldType fieldType = FieldType.builder(value.getName(), toAdtType(type)).build();
        if(!(fieldType.getType() instanceof NullValueType)) {
            fieldType.setRequired(true);
        }
        if(!Objects.isNull(value.getDescription())){
            fieldType.getAnnotations().add(new Description(value.getDescription().getContent()));
        }
        return fieldType;
    }

    private AnyType toAdtType(Type type) throws AdtException {
        boolean isNonNullType = type instanceof NonNullType;
        if(isNonNullType){
            return toAdtMapperRegistry.toAdt(((NonNullType)type).getType());
        } else {
            return new NullValueType(toAdtMapperRegistry.toAdt(type));
        }
    }
}
