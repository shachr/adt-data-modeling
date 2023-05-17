package data.modeling.adt.typedefs;

import data.modeling.adt.SchemaContext;
import data.modeling.adt.abstraction.collections.FieldTypeCollection;
import data.modeling.adt.abstraction.visitors.AdtVisitor;
import data.modeling.adt.util.LambdaExceptionUtil;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class ProductType implements CompositionType {
    private final FieldTypeCollection fieldTypeCollection = new FieldTypeCollection();
    private final LinkedHashSet<ReferenceNamedType> implements_;

    private final Set<ProductTypeConstructor> constructors = new LinkedHashSet<>();

    private final boolean isSealed;

    public ProductType() {
        this.implements_ = new LinkedHashSet<>();
        this.isSealed = false;
    }
    public ProductType(Set<FieldType> fields) {
        this.isSealed = false;
        this.fieldTypeCollection.addAll(fields);
        this.implements_ = new LinkedHashSet<>();
    }
    public ProductType(Set<FieldType> fields, boolean isSealed) {
        this.isSealed = isSealed;
        this.fieldTypeCollection.addAll(fields);
        this.implements_ = new LinkedHashSet<>();
    }
    public ProductType(Set<FieldType> fields, LinkedHashSet<ReferenceNamedType> implements_, boolean isSealed) {
        this.isSealed = isSealed;
        this.fieldTypeCollection.addAll(fields);
        this.implements_ = implements_;
    }

    public Set<FieldType> getOwnFields() {
        return fieldTypeCollection.getFields();
    }

    public boolean addField(FieldType fieldType){
        if(!fieldTypeCollection.containsName(fieldType.getName()))
            return fieldTypeCollection.add(fieldType);
        return false;
    }

    public FieldType getField(String name) {
        return fieldTypeCollection.get(name);
    }

    public LinkedHashSet<ReferenceNamedType> getImplements(){
        return implements_;
    }

    public Set<FieldType> resolveAllFields(SchemaContext schemaContext) {
        List<Set<FieldType>> inheritanceChain = new ArrayList<>();
        implements_.stream().forEach(referenceNamedType -> {
            // resolve ReferenceNamedType to Product Type
            AnyType anyType = schemaContext.getNamedType(referenceNamedType.getReferenceName()).getType();
            ProductType productType = (ProductType)anyType;
            inheritanceChain.add(productType.fieldTypeCollection.cloneFields());
        });
        inheritanceChain.add(fieldTypeCollection.cloneFields());

        Map<String, FieldType> fieldTypesByName = new LinkedHashMap<>();
        for (Set<FieldType> fieldTypes : inheritanceChain) {
            for (FieldType fieldType : fieldTypes) {
                fieldTypesByName.put(fieldType.getName(), fieldType);
            }
        }

        FieldTypeCollection effectiveFieldTypeCollection = new FieldTypeCollection();
        effectiveFieldTypeCollection.addAll(fieldTypesByName.values());
        return effectiveFieldTypeCollection.getFields();
    }

    public static ProductType of(FieldType... fields){
        return of(new LinkedHashSet<>(), Arrays.stream(fields), false);
    }

    public static ProductType of(Stream<FieldType> fields){
        return of(new LinkedHashSet<>(), fields, false);
    }

    public static ProductType of(boolean isSealed, FieldType... fields){
        return of(new LinkedHashSet<>(), Arrays.stream(fields), isSealed);
    }

    public static ProductType of(LinkedHashSet<ReferenceNamedType> extendedProducts, Stream<FieldType> fields){
        return of(extendedProducts, fields, false);
    }
    public static ProductType of(LinkedHashSet<ReferenceNamedType> extendedProducts, Stream<FieldType> fields, boolean isSealed){
        return new ProductType(fields.collect(Collectors.toCollection(LinkedHashSet::new)), extendedProducts, isSealed);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductType that = (ProductType) o;
        return fieldTypeCollection.equals(that.fieldTypeCollection) && implements_.equals(that.implements_);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fieldTypeCollection, implements_);
    }

    public void accept(AdtVisitor visitor) {
        //todo: inheritance must be of reference names or potentially a new type that indicate inheritance chain
        this.getOwnFields().forEach(LambdaExceptionUtil.consumer(fieldType -> {
            fieldType.accept(visitor);
        }));
    }

    public boolean isSealed() {
        return isSealed;
    }

    @Override
    public int size(){
        return fieldTypeCollection.getFields().size();
    }

    public Set<ProductTypeConstructor> getConstructors() {
        return constructors;
    }

    public Set<Set<FieldType>> getResolvedConstructors() {
        return constructors.stream()
                .map(ctor ->
                        ctor.fieldNames
                                .stream()
                                .map(this::getField)
                                .collect(Collectors.toCollection(LinkedHashSet::new)))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public class ProductTypeConstructor{
        private Set<String> fieldNames;

        public ProductTypeConstructor(Set<String> fieldNames){

            this.fieldNames = fieldNames;
        }

        public Set<String> getFieldNames() {
            return fieldNames;
        }
    }
}

