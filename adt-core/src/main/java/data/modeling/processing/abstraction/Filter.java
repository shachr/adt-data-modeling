package data.modeling.processing.abstraction;

public interface Filter<T> {
    boolean shouldExecute(T message);
    void executeTask(T message);
}
