package data.modeling.adt.compatibility.checks;

import data.modeling.adt.abstraction.compatibility.CompatibilityCheck;
import data.modeling.adt.annotations.syntactic.DefaultValue;
import data.modeling.adt.compatibility.ComparatorContext;
import data.modeling.adt.compatibility.Difference;
import data.modeling.adt.compatibility.DifferenceTypes;
import data.modeling.adt.typedefs.*;

import java.util.Objects;

public class BasicCompatibilityCheck implements CompatibilityCheck {
    Definition<?> lastSeenDefinition;
    CompositionType lastCompositionType;

    @Override
    public void enterLabeledType(Definition<?> definition) {
        lastSeenDefinition = definition;
    }

    @Override
    public void exitLabeledType(Definition<?> definition) {
        lastSeenDefinition = null;
    }

    @Override
    public void enterCompositionType(CompositionType compositionType) {
        lastCompositionType = compositionType;
    }

    @Override
    public void exitCompositionType(CompositionType compositionType) {
        lastCompositionType = null;
    }

    @Override
    public void compareAnyType(ComparatorContext comparatorContext, AnyType anyType1, AnyType anyType2) {
        if(!Objects.isNull(anyType1) && Objects.isNull(anyType2)){
            comparatorContext.getDiffs().add(new Difference(
                    DifferenceTypes.TypeRemoved,
                    comparatorContext.getJsonPathTraversingContext().getJsonPointer(),
                    anyType1,
                    null));
        }
        else if(Objects.isNull(anyType1) && !Objects.isNull(anyType2)){
            if(!Objects.isNull(lastSeenDefinition)
                    && lastSeenDefinition.testAnnotation(DefaultValue.class, anno-> !Objects.isNull(anno.getValue()))){
                comparatorContext.getDiffs().add(new Difference(
                        DifferenceTypes.TypeAddedWithDefault,
                        comparatorContext.getJsonPathTraversingContext().getJsonPointer(),
                        null,
                        anyType2));
            } else {
                comparatorContext.getDiffs().add(new Difference(
                        DifferenceTypes.TypeAdded,
                        comparatorContext.getJsonPathTraversingContext().getJsonPointer(),
                        null,
                        anyType2));
            }
        }
        else if (anyType1.getClass() != anyType2.getClass()) {
            comparatorContext.getDiffs().add(
                    new Difference(DifferenceTypes.TypeChanged,
                            comparatorContext.getJsonPathTraversingContext().getJsonPointer(),
                            anyType1.getClass().getSimpleName(),
                            anyType2.getClass().getSimpleName()));
        }
    }

    @Override
    public void compareNamedType(ComparatorContext comparatorContext, TypeDefinition obj1, TypeDefinition obj2) {
        if(!obj1.getName().equals(obj2.getName())) {
            comparatorContext.getDiffs().add(new Difference(DifferenceTypes.LabelChanged, comparatorContext.getJsonPathTraversingContext().getJsonPointer(), obj1.getName(), obj2.getName()));
        }
    }

    @Override
    public void comparePrimitiveType(ComparatorContext context, ScalarType scalarType1, ScalarType scalarType2) {
        if(!scalarType1.equals(scalarType2)){
            context.getDiffs().add(new Difference(DifferenceTypes.TypeChanged, context.getJsonPathTraversingContext().getJsonPointer(), scalarType1.getClass().getSimpleName(), scalarType2.getClass().getSimpleName()));;
        }
    }

    @Override
    public void compareReferenceNamedType(ComparatorContext comparatorContext, ReferencedDefinition obj1, ReferencedDefinition obj2) {
        if(!obj1.getReferenceName().equals(obj2.getReferenceName())){
            comparatorContext.getDiffs().add(new Difference(DifferenceTypes.ReferenceChanged, comparatorContext.getJsonPathTraversingContext().getJsonPointer(), obj1.getReferenceName(), obj2.getReferenceName()));;
        }
    }

    @Override
    public void compareFieldType(ComparatorContext comparatorContext, FieldDefinition fieldDefinition1, FieldDefinition fieldDefinition2) {
        if(!Objects.isNull(fieldDefinition1) && Objects.isNull(fieldDefinition2)){
            comparatorContext.getDiffs().add(new Difference(
                    fieldDefinition1.isRequired() ? DifferenceTypes.FieldRemoved : DifferenceTypes.FieldRemovedOptional,
                    comparatorContext.getJsonPathTraversingContext().getJsonPointer() + fieldDefinition1.getName(),
                    fieldDefinition1,
                    null));
        }
        else if(Objects.isNull(fieldDefinition1) && !Objects.isNull(fieldDefinition2)){
            comparatorContext.getDiffs().add(new Difference(
                    fieldDefinition2.isRequired() ? DifferenceTypes.FieldAdded : DifferenceTypes.FieldAddedOptional,
                    comparatorContext.getJsonPathTraversingContext().getJsonPointer(),
                    null,
                    fieldDefinition2));
        }
        else {
            if (!fieldDefinition1.getName().equals(fieldDefinition2.getName())) {
                comparatorContext.getDiffs().add(new Difference(DifferenceTypes.FieldLabelChanged, comparatorContext.getJsonPathTraversingContext().getJsonPointer("name"), fieldDefinition1.getName(), fieldDefinition2.getName()));
            }
            if (!fieldDefinition1.getIndex().equals(fieldDefinition2.getIndex())) {
                comparatorContext.getDiffs().add(new Difference(DifferenceTypes.FieldOrderChanged, comparatorContext.getJsonPathTraversingContext().getJsonPointer("index"), fieldDefinition1.getIndex(), fieldDefinition2.getIndex()));
            }
            if (fieldDefinition1.isRequired() && !fieldDefinition2.isRequired()) {
                comparatorContext.getDiffs().add(new Difference(DifferenceTypes.FieldChangedToOptional, comparatorContext.getJsonPathTraversingContext().getJsonPointer("isRequired"), fieldDefinition1.isRequired(), fieldDefinition2.isRequired()));
            } else if (!fieldDefinition1.isRequired() && fieldDefinition2.isRequired()) {
                comparatorContext.getDiffs().add(new Difference(DifferenceTypes.FieldChangedToMandatory, comparatorContext.getJsonPathTraversingContext().getJsonPointer("isRequired"), fieldDefinition1.isRequired(), fieldDefinition2.isRequired()));
            }
        }
    }
}
