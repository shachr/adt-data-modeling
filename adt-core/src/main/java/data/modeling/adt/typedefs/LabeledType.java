package data.modeling.adt.typedefs;

import data.modeling.adt.abstraction.annotations.Annotation;
import data.modeling.adt.abstraction.visitors.AdtVisitor;
import data.modeling.adt.util.AdtVisitorUtil;

import java.util.HashSet;
import java.util.Set;

public interface LabeledType extends AnyType {
    String getName();

    AnyType getType();

    Set<Annotation<?>> getAnnotations();

    default void accept(AdtVisitor visitor) {
        AdtVisitorUtil.visit(visitor, getType());
        visitor.visit(new HashSet<>(this.getAnnotations()));
        this.getAnnotations().forEach(visitor::visit);
    }
}
