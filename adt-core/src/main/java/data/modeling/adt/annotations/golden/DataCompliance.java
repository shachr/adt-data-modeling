package data.modeling.adt.annotations.golden;

import data.modeling.adt.abstraction.annotations.Annotation;
import data.modeling.adt.enums.DataCompliances;

import java.util.Set;

public final class DataCompliance extends Annotation<Set<DataCompliances>> {

    public DataCompliance(Set<DataCompliances> values) {
        super(values);
    }

    public static DataCompliance of(Set<DataCompliances> value) {
        return new DataCompliance(value);
    }
}

