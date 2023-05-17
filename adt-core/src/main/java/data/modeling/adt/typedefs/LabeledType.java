package data.modeling.adt.typedefs;

import data.modeling.adt.abstraction.annotations.Annotation;
import data.modeling.adt.abstraction.visitors.AdtVisitor;
import data.modeling.adt.exceptions.AdtException;
import data.modeling.adt.util.AdtVisitorUtil;
import data.modeling.adt.util.LambdaExceptionUtil;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

public interface LabeledType extends AnyType {
    String getName();

    AnyType getType();

    Set<Annotation<?>> getAnnotations();

    default <T extends Annotation<?>> Optional<T> findAnnotation(Class<T> annoClazz){
        return getAnnotations().stream()
                .filter(annotation -> annotation.getClass().equals(annoClazz))
                .map(annotation -> (T)annotation)
                .findFirst();
    }

    default <T extends Annotation<?>> boolean testAnnotation(Class<T> annoClazz, Predicate<T> predicate){
        return getAnnotations().stream()
                .filter(annotation -> annotation.getClass().equals(annoClazz))
                .map(annotation -> (T)annotation)
                .allMatch(predicate);
    }

    default void accept(AdtVisitor visitor) throws AdtException {
        AdtVisitorUtil.visit(visitor, getType());
        visitor.visit(new HashSet<>(this.getAnnotations()));
        this.getAnnotations().forEach(LambdaExceptionUtil.consumer(visitor::visit));
    }

    void setType(AnyType anyType);
}
