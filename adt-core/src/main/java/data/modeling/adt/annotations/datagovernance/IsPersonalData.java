package data.modeling.adt.annotations.datagovernance;

import data.modeling.adt.abstraction.annotations.Annotation;

public final class IsPersonalData extends Annotation<Boolean> {


    public IsPersonalData(boolean value) {
        super(value);
    }

    public static IsPersonalData of(boolean value) {
        return new IsPersonalData(value);
    }
}

