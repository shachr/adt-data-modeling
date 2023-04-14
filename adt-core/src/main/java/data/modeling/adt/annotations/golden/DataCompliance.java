package data.modeling.adt.annotations.golden;

import data.modeling.adt.abstraction.annotations.Annotation;
import data.modeling.adt.enums.DataCompliances;

public final class DataCompliance extends Annotation<DataCompliances> {

    public DataCompliance(DataCompliances value) {
        super(value);
    }

    public static DataCompliance of(DataCompliances value) {
        return new DataCompliance(value);
    }
}

