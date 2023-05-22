package data.modeling.adt.annotations.documentation;

import data.modeling.adt.abstraction.annotations.Documentation;

public final class Identifier extends Documentation<Boolean> {

    public Identifier() {
        super(true);
    }
    public Identifier(Boolean value) {
        super(value);
    }

    public static Identifier of(Boolean value) {
        return new Identifier(value);
    }
}

