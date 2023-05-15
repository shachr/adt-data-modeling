package data.modeling.adt.abstraction.compatibility;

import data.modeling.adt.compatibility.ComparatorContext;
import data.modeling.adt.typedefs.*;

public interface CompatibilityCheck {

    void enterLabeledType(LabeledType labeledType);
    void exitLabeledType(LabeledType labeledType);

    void enterCompositionType(CompositionType compositionType);
    void exitCompositionType(CompositionType compositionType);

    void compareAnyType(ComparatorContext comparatorContext, AnyType anyType1, AnyType anyType2);

    void compareNamedType(ComparatorContext comparatorContext, NamedType obj1, NamedType obj2);

    void comparePrimitiveType(ComparatorContext comparatorContext, PrimitiveType primitiveType1, PrimitiveType primitiveType2);

    void compareReferenceNamedType(ComparatorContext comparatorContext, ReferenceNamedType obj1, ReferenceNamedType obj2);

    void compareFieldType(ComparatorContext comparatorContext, FieldType fieldType1, FieldType fieldType2);
}
