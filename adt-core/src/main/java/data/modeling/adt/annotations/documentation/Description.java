package data.modeling.adt.annotations.documentation;

import data.modeling.adt.abstraction.annotations.Documentation;

public final class Description extends Documentation<String> {

    public Description(String value) {
        super(value);
    }

    public static Description of(String value) {
        return new Description(value);
    }
}

