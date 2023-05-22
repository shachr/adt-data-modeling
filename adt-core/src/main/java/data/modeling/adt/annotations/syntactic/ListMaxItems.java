package data.modeling.adt.annotations.syntactic;

import data.modeling.adt.abstraction.annotations.Constraint;

public class ListMaxItems extends Constraint<Integer> {
    public ListMaxItems(Integer value) {
        super(value);
    }
}
