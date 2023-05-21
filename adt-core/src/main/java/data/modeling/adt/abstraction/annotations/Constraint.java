package data.modeling.adt.abstraction.annotations;

public abstract class Constraint<T> extends Annotation<T> {
    public Constraint(T value) {
        super(value);
    }
}
