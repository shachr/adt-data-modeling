package data.modeling.adt.mappers.jsonschemadraft7.mappers;

import data.modeling.adt.abstraction.mappers.MapToAdt;
import data.modeling.adt.abstraction.typedefs.AnyType;
import data.modeling.adt.util.ReferenceNamedTypeCollector;
import data.modeling.adt.util.TraversingContext;

import java.util.Map;

public abstract class JsonSchemaMapper<R extends AnyType>
        implements MapToAdt<Map<String, Object>, R>
{
    private String jsonSchemaId = "";
    private ReferenceNamedTypeCollector jsonSchemaReferences = new ReferenceNamedTypeCollector();
    private TraversingContext traversingContext = new TraversingContext();

    public ReferenceNamedTypeCollector getJsonSchemaReferences() {
        return jsonSchemaReferences;
    }

    public TraversingContext getTraversingContext() {
        return traversingContext;
    }

}
