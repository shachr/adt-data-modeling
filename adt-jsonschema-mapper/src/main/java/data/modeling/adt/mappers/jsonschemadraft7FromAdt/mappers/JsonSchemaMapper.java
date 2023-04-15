package data.modeling.adt.mappers.jsonschemadraft7FromAdt.mappers;

import data.modeling.adt.abstraction.mappers.MapFromAdt;
import data.modeling.adt.abstraction.mappers.MapToAdt;
import data.modeling.adt.typedefs.AnyType;
import data.modeling.adt.util.ReferenceNamedTypeCollector;
import data.modeling.adt.util.TraversingContext;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Stream;

public abstract class JsonSchemaMapper<R extends AnyType>
        implements MapFromAdt<R, Stream<Map.Entry<String, Object>>>
{
}
