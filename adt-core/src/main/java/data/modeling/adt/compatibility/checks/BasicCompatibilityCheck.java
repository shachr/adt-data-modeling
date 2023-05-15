package data.modeling.adt.compatibility.checks;

import data.modeling.adt.abstraction.compatibility.CompatibilityCheck;
import data.modeling.adt.annotations.golden.DefaultValue;
import data.modeling.adt.compatibility.ComparatorContext;
import data.modeling.adt.compatibility.Difference;
import data.modeling.adt.compatibility.DifferenceTypes;
import data.modeling.adt.typedefs.*;

import java.util.Objects;

public class BasicCompatibilityCheck implements CompatibilityCheck {
    LabeledType lastSeenLabeledType;
    CompositionType lastCompositionType;

    @Override
    public void enterLabeledType(LabeledType labeledType) {
        lastSeenLabeledType = labeledType;
    }

    @Override
    public void exitLabeledType(LabeledType labeledType) {
        lastSeenLabeledType = null;
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
            if(!Objects.isNull(lastSeenLabeledType)
                    && lastSeenLabeledType.testAnnotation(DefaultValue.class, anno-> !Objects.isNull(anno.getValue()))){
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
    public void compareNamedType(ComparatorContext comparatorContext, NamedType obj1, NamedType obj2) {
        if(!obj1.getName().equals(obj2.getName())) {
            comparatorContext.getDiffs().add(new Difference(DifferenceTypes.LabelChanged, comparatorContext.getJsonPathTraversingContext().getJsonPointer(), obj1.getName(), obj2.getName()));
        }
    }

    @Override
    public void comparePrimitiveType(ComparatorContext context, PrimitiveType primitiveType1, PrimitiveType primitiveType2) {
        if(!primitiveType1.equals(primitiveType2)){
            context.getDiffs().add(new Difference(DifferenceTypes.TypeChanged, context.getJsonPathTraversingContext().getJsonPointer(), primitiveType1.getClass().getSimpleName(), primitiveType2.getClass().getSimpleName()));;
        }
    }

    @Override
    public void compareReferenceNamedType(ComparatorContext comparatorContext, ReferenceNamedType obj1, ReferenceNamedType obj2) {
        if(!obj1.getReferenceName().equals(obj2.getReferenceName())){
            comparatorContext.getDiffs().add(new Difference(DifferenceTypes.ReferenceChanged, comparatorContext.getJsonPathTraversingContext().getJsonPointer(), obj1.getReferenceName(), obj2.getReferenceName()));;
        }
    }

    @Override
    public void compareFieldType(ComparatorContext comparatorContext, FieldType fieldType1, FieldType fieldType2) {
        if(!Objects.isNull(fieldType1) && Objects.isNull(fieldType2)){
            comparatorContext.getDiffs().add(new Difference(
                    fieldType1.isRequired() ? DifferenceTypes.FieldRemoved : DifferenceTypes.FieldRemovedOptional,
                    comparatorContext.getJsonPathTraversingContext().getJsonPointer() + fieldType1.getName(),
                    fieldType1,
                    null));
        }
        else if(Objects.isNull(fieldType1) && !Objects.isNull(fieldType2)){
            comparatorContext.getDiffs().add(new Difference(
                    fieldType2.isRequired() ? DifferenceTypes.FieldAdded : DifferenceTypes.FieldAddedOptional,
                    comparatorContext.getJsonPathTraversingContext().getJsonPointer(),
                    null,
                    fieldType2));
        }
        else {
            if (!fieldType1.getName().equals(fieldType2.getName())) {
                comparatorContext.getDiffs().add(new Difference(DifferenceTypes.FieldLabelChanged, comparatorContext.getJsonPathTraversingContext().getJsonPointer("name"), fieldType1.getName(), fieldType2.getName()));
            }
            if (!fieldType1.getIndex().equals(fieldType2.getIndex())) {
                comparatorContext.getDiffs().add(new Difference(DifferenceTypes.FieldOrderChanged, comparatorContext.getJsonPathTraversingContext().getJsonPointer("index"), fieldType1.getIndex(), fieldType2.getIndex()));
            }
            if (fieldType1.isRequired() && !fieldType2.isRequired()) {
                comparatorContext.getDiffs().add(new Difference(DifferenceTypes.FieldChangedToOptional, comparatorContext.getJsonPathTraversingContext().getJsonPointer("isRequired"), fieldType1.isRequired(), fieldType2.isRequired()));
            } else if (!fieldType1.isRequired() && fieldType2.isRequired()) {
                comparatorContext.getDiffs().add(new Difference(DifferenceTypes.FieldChangedToMandatory, comparatorContext.getJsonPathTraversingContext().getJsonPointer("isRequired"), fieldType1.isRequired(), fieldType2.isRequired()));
            }
        }
    }
}
