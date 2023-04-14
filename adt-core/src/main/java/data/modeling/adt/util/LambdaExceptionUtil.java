package data.modeling.adt.util;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class LambdaExceptionUtil {
    public static <T, R> Function<T, R> function(FunctionWithException<T, R> functionWithException) {
        return arg -> {
            try {
                return functionWithException.apply(arg);
            } catch (Exception ex) {
                throwAsUnchecked(ex);
                return null; // unreachable code
            }
        };
    }

    public static <T> Consumer<T> consumer(ConsumerWithException<T> consumerWithException) {
        return arg -> {
            try {
                consumerWithException.accept(arg);
            } catch (Exception ex) {
                throwAsUnchecked(ex);
            }
        };
    }

    public static <T> Predicate<T> predicate(PredicateWithException<T> predicateWithException) {
        return arg -> {
            try {
                return predicateWithException.test(arg);
            } catch (Exception ex) {
                throwAsUnchecked(ex);
                return false; // unreachable code
            }
        };
    }

    public static <T, U, R> BiFunction<T, U, R> biFunction(BiFunctionWithException<T, U, R> biFunctionWithException) {
        return (arg1, arg2) -> {
            try {
                return biFunctionWithException.apply(arg1, arg2);
            } catch (Exception ex) {
                throwAsUnchecked(ex);
                return null; // unreachable code
            }
        };
    }

    public static <T, U> BiConsumer<T, U> biConsumer(BiConsumerWithException<T, U> biConsumerWithException) {
        return (arg1, arg2) -> {
            try {
                biConsumerWithException.accept(arg1, arg2);
            } catch (Exception ex) {
                throwAsUnchecked(ex);
            }
        };
    }

    public static <T, U> BiPredicate<T, U> biPredicate(BiPredicateWithException<T, U> biPredicateWithException) {
        return (arg1, arg2) -> {
            try {
                return biPredicateWithException.test(arg1, arg2);
            } catch (Exception ex) {
                throwAsUnchecked(ex);
                return false; // unreachable code
            }
        };
    }

    @FunctionalInterface
    public interface FunctionWithException<T, R> {
        R apply(T t) throws Exception;
    }

    @FunctionalInterface
    public interface ConsumerWithException<T> {
        void accept(T t) throws Exception;
    }

    @FunctionalInterface
    public interface PredicateWithException<T> {
        boolean test(T t) throws Exception;
    }

    @FunctionalInterface
    public interface BiFunctionWithException<T, U, R> {
        R apply(T t, U u) throws Exception;
    }

    @FunctionalInterface
    public interface BiConsumerWithException<T, U> {
        void accept(T t, U u) throws Exception;
    }

    @FunctionalInterface
    public interface BiPredicateWithException<T, U> {
        boolean test(T t, U u) throws Exception;
    }

    /**
     * Throws a checked exception without declaring it in the method signature.
     * <p>
     * This method is meant to be used in exception handling code where you want to rethrow
     * a checked exception as an unchecked exception without having to declare it in the method signature.
     */
    public static <E extends Exception> void throwAsUnchecked(Exception ex) throws E {
        throw (E) ex;
    }
}
