package data.modeling.adt.annotations.constaints;

import data.modeling.adt.abstraction.annotations.Constraint;

public class ObjectMinProperties extends Constraint<Integer> {
    public ObjectMinProperties(Integer value) {
        super(value);
    }
}
