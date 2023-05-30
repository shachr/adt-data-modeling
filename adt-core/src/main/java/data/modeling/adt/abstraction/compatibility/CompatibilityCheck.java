package data.modeling.adt.abstraction.compatibility;

import data.modeling.adt.compatibility.ComparatorContext;
import data.modeling.adt.typedefs.*;

public interface CompatibilityCheck {

    void enterLabeledType(Definition<?> definition);
    void exitLabeledType(Definition<?> definition);

    void enterCompositionType(CompositionType compositionType);
    void exitCompositionType(CompositionType compositionType);

    void compareAnyType(ComparatorContext comparatorContext, AnyType anyType1, AnyType anyType2);

    void compareNamedType(ComparatorContext comparatorContext, TypeDefinition obj1, TypeDefinition obj2);

    void comparePrimitiveType(ComparatorContext comparatorContext, ScalarType scalarType1, ScalarType scalarType2);

    void compareReferenceNamedType(ComparatorContext comparatorContext, ReferencedDefinition obj1, ReferencedDefinition obj2);

    void compareFieldType(ComparatorContext comparatorContext, FieldDefinition fieldDefinition1, FieldDefinition fieldDefinition2);
}
