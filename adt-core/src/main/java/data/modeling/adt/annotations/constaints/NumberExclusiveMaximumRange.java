package data.modeling.adt.annotations.constaints;

import data.modeling.adt.abstraction.annotations.Constraint;

public class NumberExclusiveMaximumRange extends Constraint<Integer> {
    public NumberExclusiveMaximumRange(Integer value) {
        super(value);
    }
}
