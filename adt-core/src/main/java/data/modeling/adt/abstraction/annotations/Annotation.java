package data.modeling.adt.abstraction.annotations;

import java.util.Objects;

public abstract class Annotation<T> {
    private final T value;

    public Annotation(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public String getName(){
        return this.getClass().getCanonicalName();
    }
    public <R> R accept(AnnotationVisitor<R> visitor){
        return visitor.visit(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Annotation<?> that = (Annotation<?>) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
