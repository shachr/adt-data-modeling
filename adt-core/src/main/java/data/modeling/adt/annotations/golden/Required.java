package data.modeling.adt.annotations.golden;

import data.modeling.adt.abstraction.annotations.Annotation;

public final class Required extends Annotation {

    private Required(boolean value) {
        super(value);
    }

    public static Required of(boolean value) {
        return new Required(value);
    }
}

