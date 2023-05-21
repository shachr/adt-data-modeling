package data.modeling.adt.abstraction.annotations;

import data.modeling.adt.abstraction.annotations.Annotation;

public abstract class Documentation<T> extends Annotation<T> {

    public Documentation(T value) {
        super(value);
    }
}

