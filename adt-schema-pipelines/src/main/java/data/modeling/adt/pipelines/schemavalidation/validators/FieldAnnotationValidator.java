package data.modeling.adt.pipelines.schemavalidation.validators;

import data.modeling.adt.abstraction.annotations.Annotation;
import data.modeling.adt.annotations.golden.DataHandlingClassification;
import data.modeling.adt.annotations.golden.DefaultValue;
import data.modeling.adt.annotations.golden.Description;
import data.modeling.adt.messages.SchemaValidationMessage;
import data.modeling.adt.typedefs.*;

import java.util.Set;
import java.util.stream.Collectors;

public class FieldAnnotationValidator {

        private final TraversingContext traversingContext;

        public FieldAnnotationValidator(TraversingContext traversingContext) {
            this.traversingContext = traversingContext;
        }

        public void validateAnnotations(Set<Annotation<?>> annotations) {

            boolean xDataHandlingFound = false;
            boolean descriptionFound = false;

            AnyType lastType = traversingContext.getNamedTypeStack().lastElement().getType();
            boolean isOfSumType = lastType instanceof SumType;
            boolean defaultFound = !isOfSumType;

            String jsonPointer = "/" + String.join("/", traversingContext.getNamedTypeStack().stream().map(LabeledType::getName).collect(Collectors.toList()));
            for (Annotation<?> annotation : annotations) {
                if (annotation instanceof DataHandlingClassification) {
                    xDataHandlingFound = true;
                } else if (annotation instanceof Description) {
                    descriptionFound = true;
                } else if(isOfSumType && annotation instanceof DefaultValue){
                    // default value is mandatory
                    // default can be empty, PrimitiveType or a ReferencedObjectType.
                    boolean isPrimitiveCondition = lastType instanceof PrimitiveType && !((PrimitiveType)lastType).isValueOf(annotation.getValue());
                    boolean isPrimitiveEnumTypeCondition = lastType instanceof EnumType && !((EnumType) lastType).isValueOf(annotation.getValue());
                    // todo: default value of a union can be anything, how do we validate that the value make sense?
                    if(isPrimitiveCondition || isPrimitiveEnumTypeCondition) {
                        traversingContext.getValidationErrors().add(new SchemaValidationMessage.ValidationError(jsonPointer, 400, "invalid value: default"));
                    }
                    defaultFound = true;
                }
            }

            if (!xDataHandlingFound) {
                traversingContext.getValidationErrors().add(new SchemaValidationMessage.ValidationError(jsonPointer, 404, "missing: x-data-handling-classification"));
            }

            if (!descriptionFound) {
                traversingContext.getValidationErrors().add(new SchemaValidationMessage.ValidationError(jsonPointer, 404, "missing: description"));
            }

            if(!defaultFound){
                traversingContext.getValidationErrors().add(new SchemaValidationMessage.ValidationError(jsonPointer, 404, "missing: default"));
            }
        }
    }