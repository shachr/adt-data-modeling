package data.modeling.adt.abstraction.visitors;

import data.modeling.adt.abstraction.annotations.Annotation;
import data.modeling.adt.exceptions.AdtException;
import data.modeling.adt.typedefs.*;

import java.util.Set;

public interface AdtVisitor {
    void enterLabeledType(Definition<?> type) throws AdtException;
    void exitLabeledType(Definition<?> type) throws AdtException;

    void visit(ProductType type) throws AdtException;

    void visit(SumType type) throws AdtException;

    void visit(AllOfType type) throws AdtException;

    void visit(AnyOfType type) throws AdtException;

    void visit(ReferencedDefinition type) throws AdtException;

    void visit(Set<Annotation<?>> annotations) throws AdtException;
    void visit(Annotation<?> annotation) throws AdtException;
    void visit(NumericType type) throws AdtException;
    void visit(ScalarType type) throws AdtException;
    void visit(CollectionType type) throws AdtException;
    void visit(TimestampType type) throws AdtException;
    void visit(AnyType type) throws AdtException;

    void visit(TypeModifier type) throws AdtException;
}
