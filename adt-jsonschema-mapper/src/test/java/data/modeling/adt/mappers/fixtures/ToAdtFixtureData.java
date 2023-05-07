package data.modeling.adt.mappers.fixtures;

import data.modeling.adt.SchemaContext;

public interface ToAdtFixtureData<T> extends FixtureData {
    String getExpectedNamedTypeName();
    SchemaContext getExpectedSchemaContext();

    int expectedSchemaContextSize();
    int expectedDiffSize();

    T getInputSchema();
}
