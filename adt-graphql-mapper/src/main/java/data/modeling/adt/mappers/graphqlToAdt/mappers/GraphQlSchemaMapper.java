package data.modeling.adt.mappers.graphqlToAdt.mappers;

import data.modeling.adt.abstraction.mappers.MapToAdt;
import data.modeling.adt.annotations.idl.TypeDeclaration;
import data.modeling.adt.typedefs.AnyType;
import data.modeling.adt.util.TraversingContext;
import graphql.language.ImplementingTypeDefinition;
import graphql.language.NamedNode;
import graphql.language.Node;
import graphql.language.TypeDefinition;
import graphql.schema.GraphQLSchemaElement;
import graphql.schema.GraphQLType;

public abstract class GraphQlSchemaMapper<T extends NamedNode, R extends AnyType>
        implements MapToAdt<T, R>
{
    private String jsonSchemaId = "";

    private TraversingContext traversingContext = new TraversingContext();

    public TraversingContext getTraversingContext() {
        return traversingContext;
    }

}