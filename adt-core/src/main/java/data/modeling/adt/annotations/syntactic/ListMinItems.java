package data.modeling.adt.annotations.syntactic;

import data.modeling.adt.abstraction.annotations.Constraint;

public class ListMinItems extends Constraint<Integer> {
    public ListMinItems(Integer value) {
        super(value);
    }
}
