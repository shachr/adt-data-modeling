package data.modeling.adt.abstraction.annotations;

public interface AnnotationVisitor<R> {
    R visit(Annotation<?> annotation);
}
