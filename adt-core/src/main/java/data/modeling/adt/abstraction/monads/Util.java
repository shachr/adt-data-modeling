package data.modeling.adt.abstraction.monads;

import data.modeling.adt.SchemaContext;
import data.modeling.adt.abstraction.typedefs.NamedType;

import java.util.stream.Stream;

public class Util {
    public static SchemaContext toSchemaContext(Stream<NamedType> stream){
        SchemaContext schemaContext = new SchemaContext();
        stream.forEach(schemaContext::registerNamedType);
        return schemaContext;
    }
}
