package data.modeling.adt.compatibility;

import java.util.Arrays;

public enum DifferenceTypes {

    TypeRemoved,
    TypeAdded,
    TypeAddedWithDefault,

    TypeChanged,

    LabelChanged,

    ReferenceChanged,

    FieldRemoved,
    FieldRemovedOptional,
    FieldAdded,
    FieldAddedOptional,

    FieldChangedToMandatory,
    FieldChangedToOptional,
    FieldLabelChanged,

    FieldOrderChanged,

    AnnotationRemoved,
    AnnotationChanged,

    AnnotationAdded;

    public boolean isEither(DifferenceTypes... diffTypes){
        return Arrays.asList(diffTypes).contains(this);
    }
}
