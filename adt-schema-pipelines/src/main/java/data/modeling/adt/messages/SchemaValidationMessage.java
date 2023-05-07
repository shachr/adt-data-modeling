package data.modeling.adt.messages;


import data.modeling.adt.SchemaContext;
import data.modeling.adt.abstraction.Message;
import data.modeling.adt.abstraction.annotations.Annotation;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SchemaValidationMessage implements Message {
    private SchemaContext schemaContext;
    private Set<ValidationError> validationErrors;

    public SchemaValidationMessage(SchemaContext schemaContext, Stream<ValidationError> validationErrorStream) {
        this.schemaContext = schemaContext;
        validationErrors = validationErrorStream.collect(Collectors.toSet());
    }

    public Set<ValidationError> getValidationErrors() {
        return validationErrors;
    }

    public SchemaContext getSchemaContext() {
        return schemaContext;
    }

    public static class ValidationError{
        private String jsonPointer;
        private int errorCode;
        private String message;

        public ValidationError(String jsonPointer, int errorCode, String message){
            this.jsonPointer = jsonPointer;

            this.errorCode = errorCode;
            this.message = message;
        }

        public int getErrorCode() {
            return errorCode;
        }

        public String getMessage() {
            return message;
        }

        public String getJsonPointer() {
            return jsonPointer;
        }
    }
}
