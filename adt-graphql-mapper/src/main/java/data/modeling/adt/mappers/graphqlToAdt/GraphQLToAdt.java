package data.modeling.adt.mappers.graphqlToAdt;

import data.modeling.adt.SchemaContext;
import data.modeling.adt.abstraction.monads.NamedTypeStream;
import data.modeling.adt.exceptions.AdtException;
import data.modeling.adt.mappers.graphqlToAdt.mappers.*;
import data.modeling.adt.mappers.registries.ToAdtMapperRegistry;
import data.modeling.adt.typedefs.TypeDefinition;
import data.modeling.adt.util.LambdaExceptionUtil;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;


import java.util.stream.Stream;

public class GraphQLToAdt implements NamedTypeStream {

    protected TypeDefinitionRegistry typeDefinitionRegistry;
    private final String graphqlString;
    protected SchemaContext schemaContext;

    private ToAdtMapperRegistry toAdtMapperRegistry = new ToAdtMapperRegistry();;

    public GraphQLToAdt(String graphQLString)  {
        this(graphQLString, new SchemaContext());
    }
    public GraphQLToAdt(String graphqlString, SchemaContext schemaContext)  {
        this.graphqlString = graphqlString;
        this.schemaContext = schemaContext;

        typeDefinitionRegistry = new SchemaParser().parse(graphqlString);
        toAdtMapperRegistry.register(new ObjectTypeDefinitionMapper(toAdtMapperRegistry, typeDefinitionRegistry));
        toAdtMapperRegistry.register(new FieldDefinitionMapper(toAdtMapperRegistry));
        toAdtMapperRegistry.register(new TypeNameMapper(toAdtMapperRegistry, schemaContext));
        toAdtMapperRegistry.register(new EnumTypeDefinitionMapper(toAdtMapperRegistry, typeDefinitionRegistry));
        toAdtMapperRegistry.register(new ListTypeMapper(toAdtMapperRegistry));
        toAdtMapperRegistry.register(new UnionTypeDefinitionMapper(toAdtMapperRegistry, typeDefinitionRegistry));
        toAdtMapperRegistry.register(new InputObjectTypeDefinitionMapper(toAdtMapperRegistry, typeDefinitionRegistry));
        toAdtMapperRegistry.register(new InputInputValueDefinitionMapper(toAdtMapperRegistry));
        toAdtMapperRegistry.register(new NonNullTypeMapper(toAdtMapperRegistry));
        toAdtMapperRegistry.register(new InterfaceTypeDefinitionMapper(toAdtMapperRegistry, typeDefinitionRegistry));
    }

    @Override
    public ToAdtMapperRegistry getMapperRegistry() {
        return toAdtMapperRegistry;
    }

    @Override
    public Stream<TypeDefinition> stream() throws AdtException {
        // todo: scalars???
        //  the assumption atm is that scalars are fixed, its wrong, should support several built-in scalars
        //  however, use case should be able to determine the scalars that are needed.
        return typeDefinitionRegistry.types().values().stream()
                .map(LambdaExceptionUtil.function(toAdtMapperRegistry::toAdt))
                .map(type -> (TypeDefinition) type)
                .peek(namedType -> schemaContext.registerNamedType(namedType));
    }
}