package data.modeling.adt.typedefs;

import data.modeling.adt.SchemaContext;
import data.modeling.adt.exceptions.AdtException;

public interface ResolvableType {
    AnyType resolveSubSchemes(SchemaContext schemaContext) throws AdtException;
}
