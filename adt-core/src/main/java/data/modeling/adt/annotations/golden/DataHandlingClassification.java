package data.modeling.adt.annotations.golden;

import data.modeling.adt.abstraction.annotations.Annotation;
import data.modeling.adt.enums.DataHandlingClassifications;

public final class DataHandlingClassification extends Annotation<DataHandlingClassifications> {

    public DataHandlingClassification(DataHandlingClassifications value) {
        super(value);
    }

    public static DataHandlingClassification of(DataHandlingClassifications value) {
        return new DataHandlingClassification(value);
    }
}

