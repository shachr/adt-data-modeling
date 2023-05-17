package data.modeling.adt.annotations.syntactic;

import data.modeling.adt.abstraction.annotations.Annotation;

public final class DefaultValue extends Annotation<Object> {

    public DefaultValue(Object value) {
        super(value);
    }

    public static DefaultValue of(Object value) {
        return new DefaultValue(value);
    }
}

