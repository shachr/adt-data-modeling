package data.modeling.adt.mappers.fixtures;

import data.modeling.adt.SchemaContext;
import data.modeling.adt.util.AnyTypeComparator;

import java.util.List;

public interface FromAdtFixtureData<T> extends FixtureData {
    String getInputNamedTypeName();
    SchemaContext getInputSchemaContext();

    T getExpectedSchema() throws Exception;

    List<AnyTypeComparator.Difference> getExpectedDifferences();
}
