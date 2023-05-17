package data.modeling.adt.mappers.graphqlToAdt;

import data.modeling.adt.SchemaContext;
import data.modeling.adt.abstraction.monads.NamedTypeStream;
import data.modeling.adt.exceptions.AdtException;
import data.modeling.adt.mappers.graphqlToAdt.mappers.EnumTypeMapper;
import data.modeling.adt.mappers.graphqlToAdt.mappers.FieldTypeMapper;
import data.modeling.adt.mappers.graphqlToAdt.mappers.ObjectTypeMapper;
import data.modeling.adt.mappers.graphqlToAdt.mappers.TypeNameMapper;
import data.modeling.adt.mappers.registries.ToAdtMapperRegistry;
import data.modeling.adt.typedefs.NamedType;
import data.modeling.adt.util.LambdaExceptionUtil;
import graphql.language.TypeDefinition;
import graphql.org.antlr.v4.runtime.Parser;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;


import java.util.Map;
import java.util.stream.Stream;

public class GraphQLToAdt implements NamedTypeStream {

    protected ToAdtMapperRegistry toAdtMapperRegistry = new ToAdtMapperRegistry();
    protected TypeDefinitionRegistry typeDefinitionRegistry;
    private final String graphqlString;
    protected SchemaContext schemaContext;

    public GraphQLToAdt(String graphQLString)  {
        this(graphQLString, new SchemaContext());
    }
    public GraphQLToAdt(String graphqlString, SchemaContext schemaContext)  {
        this.graphqlString = graphqlString;
        this.schemaContext = schemaContext;

        typeDefinitionRegistry = new SchemaParser().parse(graphqlString);
/*
The valid SDL directive locations are as follows :

SCHEMA,
SCALAR,
OBJECT,
FIELD_DEFINITION,
ARGUMENT_DEFINITION,
INTERFACE,
UNION,
ENUM,
ENUM_VALUE,
INPUT_OBJECT,
INPUT_FIELD_DEFINITION
 */
        toAdtMapperRegistry.register(new ObjectTypeMapper(toAdtMapperRegistry));
        toAdtMapperRegistry.register(new FieldTypeMapper(toAdtMapperRegistry));
        toAdtMapperRegistry.register(new TypeNameMapper(toAdtMapperRegistry, schemaContext));
        toAdtMapperRegistry.register(new EnumTypeMapper(toAdtMapperRegistry));
    }

    @Override
    public Stream<NamedType> stream() throws AdtException {
        return typeDefinitionRegistry.types().values().stream()
                .map(LambdaExceptionUtil.function(typeDefinition ->
                    toAdtMapperRegistry.toAdt(typeDefinition)))
                .map(type -> (NamedType) type);
    }
}