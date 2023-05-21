package data.modeling.adt.annotations.constaints;

import data.modeling.adt.abstraction.annotations.Constraint;

public class NumberMinimumRange extends Constraint<Integer> {
    public NumberMinimumRange(Integer value) {
        super(value);
    }
}
