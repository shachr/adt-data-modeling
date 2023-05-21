package data.modeling.adt.annotations.constaints;

import data.modeling.adt.abstraction.annotations.Constraint;

public class NumberMaximumRange extends Constraint<Integer> {
    public NumberMaximumRange(Integer value) {
        super(value);
    }
}
