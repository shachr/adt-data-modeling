package data.modeling.adt.annotations.constaints;

import data.modeling.adt.abstraction.annotations.Constraint;

public class ListMaxItems extends Constraint<Integer> {
    public ListMaxItems(Integer value) {
        super(value);
    }
}
