package data.modeling.adt.abstraction.annotations;

public interface AnnotationVisitor<R> {
    void visit(Annotation<?> annotation);
}
