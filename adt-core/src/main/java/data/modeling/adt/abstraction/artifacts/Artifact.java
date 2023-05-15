package data.modeling.adt.abstraction.artifacts;

public interface Artifact<T> {
    String getName();
    T getValue();
}
