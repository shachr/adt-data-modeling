package data.modeling.adt.abstraction.visitors;

import data.modeling.adt.abstraction.annotations.Annotation;
import data.modeling.adt.typedefs.*;

import java.util.Set;

public interface AdtVisitor {
    void enterNamedType(LabeledType type);
    void exitNamedType(LabeledType type);

    void visit(ProductType type);

    void visit(SumType type);
    void visit(ReferenceObjectType type);

    void visit(Set<Annotation<?>> annotations);
    void visit(Annotation<?> annotation);
    void visit(NumericType type);
    void visit(PrimitiveType type);
    void visit(CollectionType type);
    void visit(TemporalType type);
    void visit(AnyType type);
}
