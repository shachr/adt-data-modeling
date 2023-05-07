package data.modeling.adt.annotations.golden;

import data.modeling.adt.abstraction.annotations.Annotation;

public final class Required extends Annotation<Boolean> {

    private Required(Boolean value) {
        super(value);
    }

    public static Required of(Boolean value) {
        return new Required(value);
    }
}

