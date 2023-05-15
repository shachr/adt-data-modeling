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

    default Optional<Annotation<?>> findAnnotation(Class<? extends Annotation<?>> annoClazz){
        return getAnnotations().stream().filter(annotation -> annotation.getClass().equals(annoClazz)).findFirst();
    }

    default boolean testAnnotation(Class<? extends Annotation<?>> annoClazz, Predicate<Annotation<?>> predicate){
        return getAnnotations().stream()
                .filter(annotation -> annotation.getClass().equals(annoClazz))
                .allMatch(predicate::test);

    }

    default void accept(AdtVisitor visitor) throws AdtException {
        AdtVisitorUtil.visit(visitor, getType());
        visitor.visit(new HashSet<>(this.getAnnotations()));
        this.getAnnotations().forEach(LambdaExceptionUtil.consumer(visitor::visit));
    }

    void setType(AnyType anyType);
}
