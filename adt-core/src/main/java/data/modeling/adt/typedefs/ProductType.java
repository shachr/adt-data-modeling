package data.modeling.adt.typedefs;

import data.modeling.adt.SchemaContext;
import data.modeling.adt.abstraction.collections.FieldTypeCollection;
import data.modeling.adt.abstraction.visitors.AdtVisitor;
import data.modeling.adt.util.LambdaExceptionUtil;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class ProductType implements CompositionType, AdtType {
    private final FieldTypeCollection fieldTypeCollection = new FieldTypeCollection();
    private LinkedHashSet<ReferencedDefinition> implements_;

    private final Set<ProductTypeConstructor> constructors = new LinkedHashSet<>();

    private final boolean isSealed;

    public ProductType() {
        this.implements_ = new LinkedHashSet<>();
        this.isSealed = false;
    }
    public ProductType(Set<FieldDefinition> fields) {
        this.isSealed = false;
        this.fieldTypeCollection.addAll(fields);
        this.implements_ = new LinkedHashSet<>();
    }
    public ProductType(Set<FieldDefinition> fields, boolean isSealed) {
        this.isSealed = isSealed;
        this.fieldTypeCollection.addAll(fields);
        this.implements_ = new LinkedHashSet<>();
    }
    public ProductType(Set<FieldDefinition> fields, LinkedHashSet<ReferencedDefinition> implements_, boolean isSealed) {
        this.isSealed = isSealed;
        this.fieldTypeCollection.addAll(fields);
        this.implements_ = implements_;
    }

    public Set<FieldDefinition> getOwnFields() {
        return fieldTypeCollection.getFields();
    }

    public boolean addField(FieldDefinition fieldDefinition){
        if(!fieldTypeCollection.containsName(fieldDefinition.getName()))
            return fieldTypeCollection.add(fieldDefinition);
        return false;
    }

    public FieldDefinition getField(String name) {
        return fieldTypeCollection.get(name);
    }

    public void setImplements(LinkedHashSet<ReferencedDefinition> value){
        implements_ = value;
    }

    public LinkedHashSet<ReferencedDefinition> getImplements(){
        return implements_;
    }

    public Set<FieldDefinition> resolveAllFields(SchemaContext schemaContext) {
        List<Set<FieldDefinition>> inheritanceChain = new ArrayList<>();
        implements_.stream().forEach(referenceNamedType -> {
            // resolve ReferenceNamedType to Product Type
            AnyType anyType = schemaContext.getNamedType(referenceNamedType.getReferenceName()).getType();
            ProductType productType = (ProductType)anyType;
            inheritanceChain.add(productType.fieldTypeCollection.cloneFields());
        });
        inheritanceChain.add(fieldTypeCollection.cloneFields());

        Map<String, FieldDefinition> fieldTypesByName = new LinkedHashMap<>();
        for (Set<FieldDefinition> fieldDefinitions : inheritanceChain) {
            for (FieldDefinition fieldDefinition : fieldDefinitions) {
                fieldTypesByName.put(fieldDefinition.getName(), fieldDefinition);
            }
        }

        FieldTypeCollection effectiveFieldTypeCollection = new FieldTypeCollection();
        effectiveFieldTypeCollection.addAll(fieldTypesByName.values());
        return effectiveFieldTypeCollection.getFields();
    }

    public static ProductType of(FieldDefinition... fields){
        return of(new LinkedHashSet<>(), Arrays.stream(fields), false);
    }

    public static ProductType of(Stream<FieldDefinition> fields){
        return of(new LinkedHashSet<>(), fields, false);
    }

    public static ProductType of(boolean isSealed, Stream<FieldDefinition> fieldStream){
        return of(new LinkedHashSet<>(), fieldStream, isSealed);
    }

    public static ProductType of(boolean isSealed, FieldDefinition... fields){
        return of(new LinkedHashSet<>(), Arrays.stream(fields), isSealed);
    }

    public static ProductType of(LinkedHashSet<ReferencedDefinition> extendedProducts, Stream<FieldDefinition> fields){
        return of(extendedProducts, fields, false);
    }
    public static ProductType of(LinkedHashSet<ReferencedDefinition> extendedProducts, Stream<FieldDefinition> fields, boolean isSealed){
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

    public Set<Set<FieldDefinition>> getResolvedConstructors() {
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

    public ProductType clone(){
        return ProductType.of(this.isSealed, this.fieldTypeCollection.getFields().stream());
    }
}

