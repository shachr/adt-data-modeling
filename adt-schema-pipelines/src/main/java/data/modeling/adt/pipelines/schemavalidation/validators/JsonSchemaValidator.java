package data.modeling.adt.pipelines.schemavalidation.validators;

import data.modeling.adt.SchemaContext;
import data.modeling.adt.abstraction.annotations.Annotation;
import data.modeling.adt.abstraction.visitors.AdtVisitor;
import data.modeling.adt.messages.SchemaParsedMessage;
import data.modeling.adt.messages.SchemaValidationMessage;
import data.modeling.adt.typedefs.*;
import data.modeling.processing.abstraction.Task;

import java.util.*;
import java.util.stream.Stream;

public class JsonSchemaValidator implements Task<SchemaParsedMessage, SchemaValidationMessage>, AdtVisitor {

    private TraversingContext traversingContext;

    @Override
    public boolean shouldExecute(SchemaParsedMessage message) {
        return true;
    }

    @Override
    public SchemaValidationMessage execute(SchemaParsedMessage message) throws Exception {
        SchemaContext schemaContext = message.getSchemaContext();
        traversingContext = new TraversingContext(schemaContext);
        return new SchemaValidationMessage(schemaContext, schemaContext.stream().flatMap(this::validate));
    }

    private Stream<SchemaValidationMessage.ValidationError> validate(NamedType namedType) {
        namedType.accept(this);
        return traversingContext.getValidationErrors().stream();
    }

    @Override
    public void enterNamedType(LabeledType type) {
        // build inheritance graph & dependency graph
        traversingContext.getNamedTypeStack().push(type);
    }

    @Override
    public void exitNamedType(LabeledType type) {
        traversingContext.getNamedTypeStack().pop();
    }

    @Override
    public void visit(ProductType type) {
        // build inheritance graph
    }

    @Override
    public void visit(SumType type) {

    }

    @Override
    public void visit(ReferenceObjectType type) {
        //build dependency graph
    }

    @Override
    public void visit(Set<Annotation<?>> annotations) {
        //validate data-classification and description exists
        if(traversingContext.getNamedTypeStack().lastElement() instanceof FieldType){
            new FieldAnnotationValidator(traversingContext).validateAnnotations(annotations);
        }
    }

    @Override
    public void visit(Annotation<?> type) {}

    @Override
    public void visit(NumericType type) {
        //validate that decimal does not exceed precision and scale if defined.
    }

    @Override
    public void visit(PrimitiveType type) {

    }

    @Override
    public void visit(CollectionType type) {

    }

    @Override
    public void visit(TemporalType type) {

    }

    @Override
    public void visit(AnyType type) {

    }
}
