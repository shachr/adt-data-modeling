package data.modeling.adt.annotations.datagovernance;

import data.modeling.adt.abstraction.annotations.DataGovernance;
import data.modeling.adt.enums.DataCompliances;

import java.util.Set;

public final class DataCompliance extends DataGovernance<Set<DataCompliances>> {

    public DataCompliance(Set<DataCompliances> values) {
        super(values);
    }

    public static DataCompliance of(Set<DataCompliances> value) {
        return new DataCompliance(value);
    }
}

