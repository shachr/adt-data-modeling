package data.modeling.adt.abstraction.annotations;

import data.modeling.adt.abstraction.annotations.Annotation;
import data.modeling.adt.enums.DataCompliances;

import java.util.Set;

public abstract class DataGovernance<T> extends Annotation<T> {

    public DataGovernance(T value) {
        super(value);
    }
}

