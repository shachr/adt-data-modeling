package data.modeling.adt.abstraction.annotations;

import data.modeling.adt.abstraction.annotations.Annotation;
import data.modeling.adt.enums.TypeDeclarations;

public abstract class SDL<T> extends Annotation<T> {

    public SDL(T value) {
        super(value);
    }
}


