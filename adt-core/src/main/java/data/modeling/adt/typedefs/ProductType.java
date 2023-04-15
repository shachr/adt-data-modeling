package data.modeling.adt.typedefs;

import data.modeling.adt.SchemaContext;
import data.modeling.adt.abstraction.collections.FieldTypeCollection;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class ProductType implements AnyType {
    private final FieldTypeCollection fieldTypeCollection = new FieldTypeCollection();
    private final Set<ReferenceNamedType> extendedProductTypes;

    public ProductType() {
        this.extendedProductTypes = new HashSet<>();
    }
    public ProductType(Set<FieldType> fields) {
        this.fieldTypeCollection.addAll(fields);
        this.extendedProductTypes = new HashSet<>();
    }
    public ProductType(Set<FieldType> fields, LinkedHashSet<ReferenceNamedType> extendedProductTypes) {
        this.fieldTypeCollection.addAll(fields);
        this.extendedProductTypes = extendedProductTypes;
    }

    public Set<FieldType> getOwnFields() {
        return fieldTypeCollection.getFields();
    }

    public FieldType getField(String name) {
        return fieldTypeCollection.get(name);
    }

    public Set<ReferenceNamedType> getExtendedProductTypes(){
        return extendedProductTypes;
    }

    public Set<FieldType> resolveAllFields(SchemaContext schemaContext) {
        List<Set<FieldType>> inheritanceChain = new ArrayList<>();
        extendedProductTypes.stream().forEach(referenceNamedType -> {
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
        return of(new LinkedHashSet<>(), Arrays.stream(fields));
    }

    public static ProductType of(LinkedHashSet<ReferenceNamedType> extendedProducts, Stream<FieldType> fields){
        return new ProductType(fields.collect(Collectors.toCollection(LinkedHashSet::new)), extendedProducts);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductType that = (ProductType) o;
        return fieldTypeCollection.equals(that.fieldTypeCollection) && extendedProductTypes.equals(that.extendedProductTypes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fieldTypeCollection, extendedProductTypes);
    }
}

