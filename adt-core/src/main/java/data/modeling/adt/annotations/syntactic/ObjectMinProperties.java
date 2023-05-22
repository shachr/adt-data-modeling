package data.modeling.adt.annotations.syntactic;

import data.modeling.adt.abstraction.annotations.Constraint;

public class ObjectMinProperties extends Constraint<Integer> {
    public ObjectMinProperties(Integer value) {
        super(value);
    }
}
