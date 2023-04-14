package data.modeling.adt.abstraction.annotations;

public abstract class Annotation<T> {
    private final T value;

    public Annotation(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

//    public abstract <R> R accept(AnnotationVisitor<R> visitor);
}
