package data.modeling.adt.annotations.syntactic;

import data.modeling.adt.abstraction.annotations.Constraint;

public class NumberExclusiveMaximumRange extends Constraint<Integer> {
    public NumberExclusiveMaximumRange(Integer value) {
        super(value);
    }
}
