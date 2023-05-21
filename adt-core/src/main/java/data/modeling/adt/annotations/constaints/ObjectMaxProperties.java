package data.modeling.adt.annotations.constaints;

import data.modeling.adt.abstraction.annotations.Constraint;

public class ObjectMaxProperties extends Constraint<Integer> {
    public ObjectMaxProperties(Integer value) {
        super(value);
    }
}
