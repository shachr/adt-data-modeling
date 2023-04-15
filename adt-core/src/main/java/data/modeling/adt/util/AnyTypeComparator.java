package data.modeling.adt.util;

import data.modeling.adt.abstraction.annotations.Annotation;
import data.modeling.adt.typedefs.*;


import java.util.*;

public class AnyTypeComparator {
    List<Difference> diffs = new ArrayList<>();
    JsonPathTraversingContext jsonPathTraversingContext;
    private AnyType obj1;
    private AnyType obj2;

    public class Difference{
        private final String message;
        private final String jsonPointer;
        private final Object expected;
        private final Object actual;

        public Difference(String message, String jsonPointer, Object expected, Object actual) {
            this.message = message;
            this.jsonPointer = jsonPointer;
            this.expected = expected;
            this.actual = actual;
        }

        public String getMessage() {
            return message;
        }

        public String getJsonPointer() {
            return jsonPointer;
        }

        public Object getExpected() {
            return expected;
        }

        public Object getActual() {
            return actual;
        }

        @Override
        public String toString() {
            return "Difference{" +
                    "message='" + message + '\'' +
                    ", jsonPointer='" + jsonPointer + '\'' +
                    ", expected=" + expected +
                    ", actual=" + actual +
                    '}';
        }
    }
    public AnyTypeComparator(AnyType obj1, AnyType obj2){
        this(new JsonPathTraversingContext(), obj1, obj2);
    }
    public AnyTypeComparator(JsonPathTraversingContext context, AnyType obj1, AnyType obj2){
        this.obj1 = obj1;
        this.obj2 = obj2;
        jsonPathTraversingContext = context;
    }

    private void setJsonPathTraversingContext(JsonPathTraversingContext value){
        this.jsonPathTraversingContext = value;
    }

    public static List<Difference> compare(AnyType obj1, AnyType obj2) {
        return new AnyTypeComparator(obj1, obj2).findDiff();
    }
    public List<Difference> findDiff() {
        if (obj1.getClass() != obj2.getClass()) {
            diffs.add(new Difference("type is different", jsonPathTraversingContext.getJsonPointer(), obj1.getClass().getSimpleName(), obj2.getClass().getSimpleName()));
        }
        else if (obj1 instanceof NamedType) {
            compareNamedType((NamedType) obj1, (NamedType) obj2);
        }
        else if (obj1 instanceof ProductType) {
            compareProductType((ProductType) obj1, (ProductType) obj2);
        }
        else if (obj1 instanceof SumType) {
            compareSumType((SumType) obj1, (SumType) obj2);
        }
        else if (obj1 instanceof CollectionType) {
            compareCollectionType((CollectionType) obj1, (CollectionType) obj2);
        }
        else if(obj1 instanceof ReferenceNamedType){
            compareReferenceNamedType((ReferenceNamedType)obj1, (ReferenceNamedType)obj2);
        }
        else if(obj1 instanceof FieldType){
            compareFieldTypes((FieldType) obj1, (FieldType) obj2);
        }

        return diffs;
    }

    private void compareNamedType(NamedType obj1, NamedType obj2) {
        if(!obj1.getName().equals(obj2.getName())){
            diffs.add(new Difference("name is different", jsonPathTraversingContext.getJsonPointer(), obj1.getName(), obj2.getName()));
        } else {
            compareAnnotations(jsonPathTraversingContext, obj1.getAnnotations(), obj2.getAnnotations());
            AnyTypeComparator anyTypeComparator = new AnyTypeComparator(jsonPathTraversingContext, obj1.getType(), obj2.getType());
            diffs.addAll(anyTypeComparator.findDiff());
        }
    }

    private void compareSumType(SumType obj1, SumType obj2) {
        if(obj1 instanceof UnionType){
            compareUnionType((UnionType)obj1, (UnionType)obj2);
        } else if(obj1 instanceof EnumType){
            compareEnumType((EnumType)obj1, (EnumType)obj2);
        }
    }

    private void compareUnionType(UnionType obj1, UnionType obj2) {
        compareCollections(obj1.getTypes(), obj2.getTypes());
    }

    private void compareEnumType(EnumType obj1, EnumType obj2) {
        if(!obj1.getBaseType().equals(obj2.getBaseType())){
            diffs.add(new Difference("enum base type mismatch", jsonPathTraversingContext.getJsonPointer(), obj1.getBaseType().getClass().getSimpleName(), obj2.getBaseType().getClass().getSimpleName()));;
        }

        compareCollections(obj1.getValues(), obj2.getValues());
    }

    private void compareProductType(ProductType obj1, ProductType obj2) {
        compareCollections(obj1.getExtendedProductTypes(), obj2.getExtendedProductTypes());
        compareFieldTypeCollection(obj1.getOwnFields(), obj2.getOwnFields());
    }

    private void compareFieldTypeCollection(Set<FieldType> coll1, Set<FieldType> coll2) {

        int index=-1;
        Iterator<FieldType> iterator1 = coll1.iterator();
        Iterator<FieldType> iterator2 = coll2.iterator();
        while(iterator1.hasNext()) {
            index++;
            FieldType fieldType1 = iterator1.next();
            if (!iterator2.hasNext()) {
                diffs.add(new Difference(
                        "field not found: " + fieldType1.getName(),
                        jsonPathTraversingContext.getJsonPointer() + fieldType1.getName(),
                        fieldType1.getName(),
                        null));
            } else {
                FieldType fieldType2 = iterator2.next();
                compareFieldTypes(fieldType1, fieldType2);
            }
        }

        while(iterator2.hasNext()){
            FieldType fieldType2 = iterator2.next();
            diffs.add(new Difference(
                    "unexpected field: " + fieldType2.getName(),
                    jsonPathTraversingContext.getJsonPointer(),
                    null,
                    fieldType2.getName()));
        }
    }
    private void compareFieldTypes(FieldType fieldType1, FieldType fieldType2) {
        jsonPathTraversingContext.addSegment("/" + fieldType1.getName(), context -> {
            if (!fieldType1.getName().equals(fieldType2.getName())) {
                diffs.add(new Difference("field name mismatch", context.getJsonPointer(), fieldType1.getName(), fieldType2.getName()));
            }
            compareAnnotations(context, fieldType1.getAnnotations(), fieldType2.getAnnotations());
            // todo: traverse
            diffs.addAll(new AnyTypeComparator(context, fieldType1.getType(), fieldType2.getType()).findDiff());
        });
    }

    private void compareAnnotations(JsonPathTraversingContext jsonPathTraversingContext, Set<Annotation> annotations1, Set<Annotation> annotations2) {
        int index=-1;
        Iterator<? extends Annotation> iterator1 = annotations1.iterator();
        Iterator<? extends Annotation> iterator2 = annotations2.iterator();

        while(iterator1.hasNext()) {
            index++;
            Annotation item1 = iterator1.next();
            if (!iterator2.hasNext()) {
                diffs.add(new Difference(
                        "annotation not found: " + item1.getName(),
                        jsonPathTraversingContext.getJsonPointer(),
                        item1,
                        null));
            } else {
                Annotation item2 = iterator2.next();
                if(!item2.equals(item1)){
                    diffs.add(new Difference(
                            "annotation value mismatch: " + item2.getValue(),
                            jsonPathTraversingContext.getJsonPointer(),
                            item1.getName(),
                            item2.getName()));
                }
            }
        }

        while(iterator2.hasNext()){
            Annotation item2 = iterator2.next();
            diffs.add(new Difference(
                    "unexpected annotation: " + item2.getClass().getSimpleName(),
                    jsonPathTraversingContext.getJsonPointer(),
                    null,
                    item2.getName()));
        }
    }

    private void compareCollections(Collection<? extends AnyType> coll1, Collection<? extends AnyType> coll2) {
        int index=-1;
        Iterator<? extends AnyType> iterator1 = coll1.iterator();
        Iterator<? extends AnyType> iterator2 = coll2.iterator();
        while(iterator1.hasNext()) {
            index++;
            AnyType item1 = iterator1.next();
            if (!iterator2.hasNext()) {
                diffs.add(new Difference(
                        "item not found: " + item1.getClass().getSimpleName(),
                        jsonPathTraversingContext.getJsonPointer("" + index),
                        item1,
                        null));
            } else {
                AnyType item2 = iterator2.next();
                jsonPathTraversingContext.addSegment("" + index, context -> {
                    diffs.addAll(new AnyTypeComparator(context, item1, item2).findDiff());
                });

            }
        }

        while(iterator2.hasNext()){
            AnyType item2 = iterator2.next();
            diffs.add(new Difference(
                    "unexpected field: " + item2.getClass().getSimpleName(),
                    jsonPathTraversingContext.getJsonPointer(),
                    null,
                    item2));
        }
    }

    private void compareReferenceNamedType(ReferenceNamedType obj1, ReferenceNamedType obj2) {
        if(!obj1.getReferenceName().equals(obj2.getReferenceName())){
            diffs.add(new Difference("reference name mismatch", jsonPathTraversingContext.getJsonPointer(), obj1.getReferenceName(), obj2.getReferenceName()));;
        }
    }

    private void compareCollectionType(CollectionType obj1, CollectionType obj2) {
        diffs.addAll(new AnyTypeComparator(jsonPathTraversingContext, obj1.getItemType(), obj2.getItemType()).findDiff());
    }
}
