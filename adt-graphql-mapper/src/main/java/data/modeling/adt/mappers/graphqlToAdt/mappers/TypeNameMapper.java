package data.modeling.adt.mappers.graphqlToAdt.mappers;

import data.modeling.adt.SchemaContext;
import data.modeling.adt.exceptions.AdtException;
import data.modeling.adt.mappers.registries.ToAdtMapperRegistry;
import data.modeling.adt.typedefs.*;
import graphql.language.TypeName;

public class TypeNameMapper extends GraphQlSchemaMapper<TypeName, NullValueType> {

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
    public NullValueType toAdt(TypeName value) throws AdtException {
        AnyType anyType =switch (value.getName()) {
            case "ID", "String" -> new StringType();
            case "Int" -> new Int32Type();
            case "Float" -> new FloatType();
            case "Boolean" -> new BoolType();
            case "Date" -> new DateType();
            case "DateTime" -> new DateTimeType();
            default -> new ReferencedDefinition(value.getName());
        };

        return new NullValueType(anyType);
    }
}
