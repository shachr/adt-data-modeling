package data.modeling.adt.annotations.syntactic;

import data.modeling.adt.abstraction.annotations.Syntactic;

public final class DefaultValue extends Syntactic<Object> {

    public DefaultValue(Object value) {
        super(value);
    }

    public static DefaultValue of(Object value) {
        return new DefaultValue(value);
    }
}

