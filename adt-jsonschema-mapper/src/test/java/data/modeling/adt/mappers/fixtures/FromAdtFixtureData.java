package data.modeling.adt.mappers.fixtures;

import data.modeling.adt.SchemaContext;
import data.modeling.adt.compatibility.AnyTypeComparator;
import data.modeling.adt.compatibility.Difference;

import java.util.List;

public interface FromAdtFixtureData<T> extends FixtureData {
    String getInputNamedTypeName();
    SchemaContext getInputSchemaContext();

    T getExpectedSchema() throws Exception;

    List<Difference> getExpectedDifferences();
}
