package data.modeling.adt.annotations.constaints;

import data.modeling.adt.abstraction.annotations.Constraint;

public class StringPattern extends Constraint<String> {
    public StringPattern(String value) {
        super(value);
    }
}
