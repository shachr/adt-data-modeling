package data.modeling.processing.abstraction;

public interface Task<TIn,TOut> {
    boolean shouldExecute(TIn message);
    TOut execute(TIn message) throws Exception;
}
