package data.modeling.adt.annotations.golden;

import data.modeling.adt.abstraction.annotations.Annotation;

public final class IsPersonalData extends Annotation {


    private IsPersonalData(boolean value) {
        super(value);
    }

    public static IsPersonalData of(boolean value) {
        return new IsPersonalData(value);
    }
}

