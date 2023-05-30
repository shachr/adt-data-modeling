package data.modeling.adt.compatibility;

import data.modeling.adt.abstraction.annotations.Annotation;
import data.modeling.adt.compatibility.checks.BasicCompatibilityCheck;
import data.modeling.adt.typedefs.*;
import data.modeling.adt.util.JsonPathTraversingContext;

import java.util.*;

public class AnyTypeComparator {
    private final ComparatorContext comparatorContext;
    private final AnyType obj1;
    private final AnyType obj2;

    public AnyTypeComparator(AnyType obj1, AnyType obj2){
        this(new ComparatorContext(new BasicCompatibilityCheck(), new ArrayList<>(), new JsonPathTraversingContext()), obj1, obj2);
    }

    public AnyTypeComparator(ComparatorContext comparatorContext, AnyType obj1, AnyType obj2){
        this.obj1 = obj1;
        this.obj2 = obj2;
        this.comparatorContext = comparatorContext;
    }

    // todo: field index is not compared
    public static List<Difference> compare(AnyType obj1, AnyType obj2) {
        AnyTypeComparator anyTypeComparator = new AnyTypeComparator(obj1, obj2);
        anyTypeComparator.compare();
        return anyTypeComparator.comparatorContext.getDiffs();
    }
    public void compare() {
        comparatorContext.getCompatibilityCheck().compareAnyType(comparatorContext, obj1, obj2);
        if(obj1.getClass().equals(obj2.getClass())) {
            if (obj1 instanceof TypeDefinition) {
                compareNamedType(comparatorContext, (TypeDefinition) obj1, (TypeDefinition) obj2);
            } else if (obj1 instanceof ProductType) {
                compareProductType(comparatorContext, (ProductType) obj1, (ProductType) obj2);
            }
            if (obj1 instanceof UnionType) {
                compareUnionType(comparatorContext, (UnionType) obj1, (UnionType) obj2);
            } else if (obj1 instanceof EnumType) {
                compareEnumType(comparatorContext, (EnumType) obj1, (EnumType) obj2);
            } else if (obj1 instanceof CollectionType) {
                new AnyTypeComparator(comparatorContext, ((CollectionType) obj1).getItemType(), ((CollectionType) obj2).getItemType()).compare();
            } else if (obj1 instanceof ReferencedDefinition) {
                comparatorContext.getCompatibilityCheck().compareReferenceNamedType(comparatorContext, (ReferencedDefinition) obj1, (ReferencedDefinition) obj2);
            }
        }
    }

    private void compareEnumType(ComparatorContext comparatorContext, EnumType enumType1, EnumType enumType2) {
        comparatorContext.getCompatibilityCheck().compareAnyType(comparatorContext, enumType1, enumType2);
        comparatorContext.getCompatibilityCheck().enterCompositionType(enumType2);
        comparatorContext.getCompatibilityCheck().comparePrimitiveType(comparatorContext, enumType1.getBaseType(), enumType2.getBaseType());
        compareCollections(comparatorContext, enumType1.getItems(), enumType2.getItems());
        comparatorContext.getCompatibilityCheck().exitCompositionType(enumType2);
    }

    private void compareProductType(ComparatorContext comparatorContext, ProductType productType1, ProductType productType2) {
        compareCollections(comparatorContext, productType1.getImplements(), productType2.getImplements());
        compareFieldTypeCollection(comparatorContext, productType1.getOwnFields(), productType2.getOwnFields());
    }

    private void compareFieldTypeCollection(ComparatorContext comparatorContext, Set<FieldDefinition> coll1, Set<FieldDefinition> coll2) {
        Iterator<FieldDefinition> iterator1 = coll1.iterator();
        Iterator<FieldDefinition> iterator2 = coll2.iterator();
        while(iterator1.hasNext()) {
            FieldDefinition fieldDefinition1 = iterator1.next();
            if (!iterator2.hasNext()) {
                comparatorContext.getCompatibilityCheck().enterLabeledType(fieldDefinition1);
                comparatorContext.getCompatibilityCheck().compareFieldType(comparatorContext, fieldDefinition1, null);
                comparatorContext.getCompatibilityCheck().exitLabeledType(fieldDefinition1);
            } else {
                FieldDefinition fieldDefinition2 = iterator2.next();
                comparatorContext.getCompatibilityCheck().enterLabeledType(fieldDefinition2);
                compareFieldTypes(comparatorContext, fieldDefinition1, fieldDefinition2);
                comparatorContext.getCompatibilityCheck().exitLabeledType(fieldDefinition2);
            }
        }

        while(iterator2.hasNext()){
            FieldDefinition fieldDefinition2 = iterator2.next();
            comparatorContext.getCompatibilityCheck().enterLabeledType(fieldDefinition2);
            comparatorContext.getCompatibilityCheck().compareFieldType(comparatorContext, null, fieldDefinition2);
            comparatorContext.getCompatibilityCheck().exitLabeledType(fieldDefinition2);
        }
    }

    private void compareFieldTypes(ComparatorContext comparatorContext, FieldDefinition fieldDefinition1, FieldDefinition fieldDefinition2) {
        // todo: compare additional types!!!
        if(fieldDefinition1 instanceof FieldAdditionalDefinition || fieldDefinition2 instanceof FieldAdditionalDefinition) {
            if (fieldDefinition1 instanceof FieldAdditionalDefinition && !(fieldDefinition2 instanceof FieldAdditionalDefinition)) {

            } else if (!(fieldDefinition1 instanceof FieldAdditionalDefinition)) {

            }
        } else {
            comparatorContext.getJsonPathTraversingContext().addSegment("/" + fieldDefinition1.getName(), context -> {
                ComparatorContext fieldComparatorContext = comparatorContext.overrideJsonPathTraversingContext(context);
                comparatorContext.getCompatibilityCheck().compareFieldType(fieldComparatorContext, fieldDefinition1, fieldDefinition2);
                this.compareAnnotations(comparatorContext, fieldDefinition1, fieldDefinition2);
                new AnyTypeComparator(fieldComparatorContext, fieldDefinition1.getType(), fieldDefinition2.getType()).compare();
            });
        }
    }

    private void compareUnionType(ComparatorContext context, UnionType unionType1, UnionType unionType2) {
        comparatorContext.getCompatibilityCheck().compareAnyType(comparatorContext, unionType1, unionType2);
        comparatorContext.getCompatibilityCheck().enterCompositionType(unionType2);
        compareCollections(context, unionType1.getTypes(), unionType2.getTypes());
        comparatorContext.getCompatibilityCheck().exitCompositionType(unionType2);
    }

    private void compareNamedType(ComparatorContext comparatorContext, TypeDefinition obj1, TypeDefinition obj2) {
        comparatorContext.getCompatibilityCheck().enterLabeledType(obj2);
        comparatorContext.getCompatibilityCheck().compareNamedType(comparatorContext, obj1, obj2);
        this.compareAnnotations(comparatorContext, obj1, obj2);
        AnyTypeComparator anyTypeComparator = new AnyTypeComparator(comparatorContext, obj1.getType(), obj2.getType());
        anyTypeComparator.compare();
        comparatorContext.getCompatibilityCheck().exitLabeledType(obj2);
    }

    private void compareCollections(ComparatorContext comparatorContext, Collection<? extends AnyType> coll1, Collection<? extends AnyType> coll2) {
        int index=-1;
        Iterator<? extends AnyType> iterator1 = coll1.iterator();
        Iterator<? extends AnyType> iterator2 = coll2.iterator();
        while(iterator1.hasNext()) {
            index++;
            AnyType item1 = iterator1.next();
            if (!iterator2.hasNext()) {
                comparatorContext.getJsonPathTraversingContext().addSegment(""+ index, subContext ->
                        comparatorContext.getCompatibilityCheck().compareAnyType(comparatorContext.overrideJsonPathTraversingContext(subContext), item1, null));
            } else {
                AnyType item2 = iterator2.next();
                comparatorContext.getJsonPathTraversingContext().addSegment("" + index, context ->
                        new AnyTypeComparator(comparatorContext.overrideJsonPathTraversingContext(context), item1, item2).compare());
            }
        }

        while(iterator2.hasNext()){
            AnyType item2 = iterator2.next();
            comparatorContext.getJsonPathTraversingContext().addSegment(""+ index, subContext ->
                    comparatorContext.getCompatibilityCheck().compareAnyType(comparatorContext.overrideJsonPathTraversingContext(subContext), null, item2));
        }
    }

    // todo: think more of the best way to do this
    //  might be best to ignore and let users override if the need to compare custom annotations
    //  "golden" annotations should be compared if needed.
    public void compareAnnotations(ComparatorContext comparatorContext, Definition definition1, Definition definition2) {
        Set<Annotation<?>> annotations1 = definition1.getAnnotations();
        Set<Annotation<?>> annotations2 = definition2.getAnnotations();

        Iterator<? extends Annotation<?>> iterator1 = annotations1.iterator();
        Iterator<? extends Annotation<?>> iterator2 = annotations2.iterator();

        while(iterator1.hasNext()) {
            Annotation<?> item1 = iterator1.next();
            if (!iterator2.hasNext()) {
                comparatorContext.getDiffs().add(new Difference(
                        DifferenceTypes.AnnotationRemoved,
                        comparatorContext.getJsonPathTraversingContext().getJsonPointer(),
                        item1,
                        null));
            } else {
                Annotation<?> item2 = iterator2.next();
                if(!item2.equals(item1)){
                    comparatorContext.getDiffs().add(new Difference(
                            DifferenceTypes.AnnotationChanged,
                            comparatorContext.getJsonPathTraversingContext().getJsonPointer(),
                            item1.getName(),
                            item2.getName()));
                }
            }
        }

        while(iterator2.hasNext()){
            Annotation<?> item2 = iterator2.next();
            comparatorContext.getDiffs().add(new Difference(
                    DifferenceTypes.AnnotationAdded,
                    comparatorContext.getJsonPathTraversingContext().getJsonPointer(),
                    null,
                    item2.getName()));
        }
    }
}
