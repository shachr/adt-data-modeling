package data.modeling.adt.mappers.graphqlToAdt.mappers;

import data.modeling.adt.abstraction.mappers.MapToAdt;
import data.modeling.adt.typedefs.AnyType;
import data.modeling.adt.util.TraversingContext;

import java.io.Serializable;

public abstract class GraphQlSchemaMapper<T extends Serializable, R extends AnyType>
        implements MapToAdt<T, R>
{
    private String jsonSchemaId = "";

    private TraversingContext traversingContext = new TraversingContext();

    public TraversingContext getTraversingContext() {
        return traversingContext;
    }

}