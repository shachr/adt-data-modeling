package data.modeling.adt.annotations.datagovernance;

import data.modeling.adt.abstraction.annotations.DataGovernance;
import data.modeling.adt.enums.DataHandlingClassifications;

public final class DataHandlingClassification extends DataGovernance<DataHandlingClassifications> {

    public DataHandlingClassification(DataHandlingClassifications value) {
        super(value);
    }

    public static DataHandlingClassification of(DataHandlingClassifications value) {
        return new DataHandlingClassification(value);
    }
}

