package data.modeling.adt.pipelines.schemavalidation.validators;

import data.modeling.adt.SchemaContext;
import data.modeling.adt.messages.SchemaValidationMessage;
import data.modeling.adt.typedefs.LabeledType;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Stack;

public class TraversingContext {
        private Set<SchemaValidationMessage.ValidationError> validationErrors = new LinkedHashSet<>();
        private Stack<LabeledType> labeledTypeStack = new Stack<>();
    private SchemaContext schemaContext;

    public Stack<LabeledType> getNamedTypeStack() {
            return labeledTypeStack;
        }

        public Set<SchemaValidationMessage.ValidationError> getValidationErrors() {
            return validationErrors;
        }

        public TraversingContext(SchemaContext schemaContext){

            this.schemaContext = schemaContext;
        }

    public SchemaContext getSchemaContext() {
        return schemaContext;
    }
}