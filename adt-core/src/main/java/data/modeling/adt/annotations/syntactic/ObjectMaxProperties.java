package data.modeling.adt.annotations.syntactic;

import data.modeling.adt.abstraction.annotations.Constraint;

public class ObjectMaxProperties extends Constraint<Integer> {
    public ObjectMaxProperties(Integer value) {
        super(value);
    }
}
