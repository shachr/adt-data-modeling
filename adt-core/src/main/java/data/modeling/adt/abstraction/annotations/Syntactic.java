package data.modeling.adt.abstraction.annotations;

import data.modeling.adt.abstraction.annotations.Annotation;

public abstract class Syntactic<T> extends Annotation<T> {

    public Syntactic(T value) {
        super(value);
    }
}

