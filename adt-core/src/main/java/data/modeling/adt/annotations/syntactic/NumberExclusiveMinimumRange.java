package data.modeling.adt.annotations.syntactic;

import data.modeling.adt.abstraction.annotations.Constraint;

public class NumberExclusiveMinimumRange extends Constraint<Integer> {
    public NumberExclusiveMinimumRange(Integer value) {
        super(value);
    }
}
