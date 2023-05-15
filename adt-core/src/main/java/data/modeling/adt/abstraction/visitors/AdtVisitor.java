package data.modeling.adt.abstraction.visitors;

import data.modeling.adt.abstraction.annotations.Annotation;
import data.modeling.adt.exceptions.AdtException;
import data.modeling.adt.typedefs.*;

import java.util.Set;

public interface AdtVisitor {
    void enterLabeledType(LabeledType type) throws AdtException;
    void exitLabeledType(LabeledType type) throws AdtException;

    void visit(ProductType type) throws AdtException;

    void visit(SumType type) throws AdtException;
    void visit(ReferenceNamedType type) throws AdtException;

    void visit(Set<Annotation<?>> annotations) throws AdtException;
    void visit(Annotation<?> annotation) throws AdtException;
    void visit(NumericType type) throws AdtException;
    void visit(PrimitiveType type) throws AdtException;
    void visit(CollectionType type) throws AdtException;
    void visit(TemporalType type) throws AdtException;
    void visit(AnyType type) throws AdtException;
}
