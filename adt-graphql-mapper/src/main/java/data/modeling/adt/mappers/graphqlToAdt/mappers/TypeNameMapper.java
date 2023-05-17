package data.modeling.adt.mappers.graphqlToAdt.mappers;

import data.modeling.adt.SchemaContext;
import data.modeling.adt.annotations.documentation.Description;
import data.modeling.adt.exceptions.AdtException;
import data.modeling.adt.mappers.registries.ToAdtMapperRegistry;
import data.modeling.adt.typedefs.*;
import graphql.language.Field;
import graphql.language.FieldDefinition;
import graphql.language.TypeName;

public class TypeNameMapper extends GraphQlSchemaMapper<TypeName, AnyType> {

    private ToAdtMapperRegistry toAdtMapperRegistry;
    private SchemaContext schemaContext;

    public TypeNameMapper(ToAdtMapperRegistry toAdtMapperRegistry, SchemaContext schemaContext){

        this.toAdtMapperRegistry = toAdtMapperRegistry;
        this.schemaContext = schemaContext;
    }
    @Override
    public boolean canMap(TypeName value) {
        return true;
    }

    @Override
    public AnyType toAdt(TypeName value) throws AdtException {
        return switch (value.getName()) {
            case "ID", "String" -> new StringType();
            case "Int" -> new IntType();
            case "Float" -> new FloatType();
            case "Boolean" -> new BoolType();
            case "Date" -> new DateType();
            case "DateTime" -> new DateTimeType();
            default -> schemaContext.getNamedType(value.getName());
        };
    }
}
