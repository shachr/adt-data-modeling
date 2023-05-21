package data.modeling.adt.annotations.constaints;

import data.modeling.adt.abstraction.annotations.Constraint;

import java.math.BigDecimal;

public class NumberMultipleOf extends Constraint<BigDecimal> {
    public NumberMultipleOf(BigDecimal value) {
        super(value);
    }
}
