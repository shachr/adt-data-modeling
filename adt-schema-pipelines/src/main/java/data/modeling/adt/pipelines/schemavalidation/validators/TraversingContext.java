package data.modeling.adt.pipelines.schemavalidation.validators;

import data.modeling.adt.SchemaContext;
import data.modeling.adt.messages.SchemaValidationMessage;
import data.modeling.adt.typedefs.Definition;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Stack;

public class TraversingContext {
        private Set<SchemaValidationMessage.ValidationError> validationErrors = new LinkedHashSet<>();
        private Stack<Definition> definitionStack = new Stack<>();
    private SchemaContext schemaContext;

    public Stack<Definition> getNamedTypeStack() {
            return definitionStack;
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