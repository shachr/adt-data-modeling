package data.modeling.adt.typedefs;

import data.modeling.adt.SchemaContext;
import data.modeling.adt.abstraction.collections.FieldTypeCollection;
import data.modeling.adt.abstraction.visitors.AdtVisitor;
import data.modeling.adt.exceptions.AdtException;
import data.modeling.adt.util.LambdaExceptionUtil;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class ProductType implements CompositionType {
    private final FieldTypeCollection fieldTypeCollection = new FieldTypeCollection();
    private final Set<ReferenceNamedType> extendedProductTypes;

    private final Set<ProductTypeConstructor> constructors = new LinkedHashSet<>();

    private final boolean isSealed;

    public ProductType() {
        this.extendedProductTypes = new HashSet<>();
        this.isSealed = false;
    }
    public ProductType(Set<FieldType> fields) {
        this.isSealed = false;
        this.fieldTypeCollection.addAll(fields);
        this.extendedProductTypes = new HashSet<>();
    }
    public ProductType(Set<FieldType> fields, boolean isSealed) {
        this.isSealed = isSealed;
        this.fieldTypeCollection.addAll(fields);
        this.extendedProductTypes = new HashSet<>();
    }
    public ProductType(Set<FieldType> fields, LinkedHashSet<ReferenceNamedType> extendedProductTypes, boolean isSealed) {
        this.isSealed = isSealed;
        this.fieldTypeCollection.addAll(fields);
        this.extendedProductTypes = extendedProductTypes;
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
        return fieldTypeCollection.equals(that.fieldTypeCollection) && extendedProductTypes.equals(that.extendedProductTypes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fieldTypeCollection, extendedProductTypes);
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

    public AnyType resolveSubSchemes(SchemaContext schemaContext) throws AdtException {
        // todo: create a visitor with the schema context as state
        //  visit return may return a new AnyType instead of the original

        // todo: throw exception when there is no way to unwrap, this will impact conversion to code gen
        //  or languages that do not support all the ADT types that are mostly aligned with json-schema.
        // todo: how to get inheriting attributes???
        Set<AnyType> extendingTypes = resolveExtendingReferences(schemaContext);
        Map<Boolean, List<FieldType>> splitFieldList = this.getOwnFields().stream().collect(Collectors.partitioningBy((fieldType -> fieldType instanceof ResolvableType)));

        // Get the list of non-resolvable fields
        ProductType productType1 = ProductType.of(splitFieldList.get(false).stream());
        List<FieldType> resolvableFields = splitFieldList.get(true);

        if(extendingTypes.isEmpty() && resolvableFields.isEmpty()) {
            // Get the list of resolvable fields
            ProductType baseFields = mergeInheritanceIn(schemaContext, Stream.empty(), productType1);
            return baseFields;
        }

        // extending products
        Stream<ProductType> extendingProductTypes = extendingTypes.stream().map(
                LambdaExceptionUtil.function(extendingType -> (ProductType) ((ProductType)extendingType).resolveSubSchemes(schemaContext)));

        ProductType baseFields = mergeInheritanceIn(schemaContext, extendingProductTypes, productType1);

        // Get the list of resolvable fields
        ProductType resolvedFields = mergeResolvableFields(schemaContext, resolvableFields.stream());

        return mergeProductTypes(baseFields, resolvedFields);
    }

    private Set<AnyType> resolveExtendingReferences(SchemaContext schemaContext) {
        return this.extendedProductTypes.stream()
                .map(referencedType -> {
                    NamedType namedType = schemaContext.getNamedType(referencedType.getReferenceName());
                    return namedType.getType();
                })
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    private ProductType mergeInheritanceIn(SchemaContext schemaContext, Stream<ProductType> resolvedProductTypes, ProductType extendedProductType) throws AdtException {
        Set<FieldType> extendingFields = extendedProductType.getOwnFields().stream()
                .peek(fieldType -> resolveCompositionType(schemaContext, fieldType))
                .collect(Collectors.toCollection(LinkedHashSet::new));

        Set<FieldType> fieldTypeSet = resolvedProductTypes
                .flatMap(productType -> productType.getOwnFields().stream())
                .map(fieldType -> {
                    FieldType fType = extendedProductType.getField(fieldType.getName());
                    if(Objects.isNull(fType)){
                        return fieldType;
                    } else {
                        extendingFields.remove(fType);
                        return fType;
                    }

                })
                .peek(fieldType -> resolveCompositionType(schemaContext, fieldType))
                .collect(Collectors.toCollection(LinkedHashSet::new));

        fieldTypeSet.addAll(extendingFields);
        return new ProductType(fieldTypeSet);
    }

    private ProductType mergeResolvableFields(SchemaContext schemaContext, Stream<FieldType> resolvableFieldsStream) throws AdtException {
        Set<FieldType> fields = resolvableFieldsStream
                .map(LambdaExceptionUtil.function(fieldType -> ((ResolvableType)fieldType).resolveSubSchemes(schemaContext)))
                .map(anyType -> (ProductType)anyType)
                .flatMap(productType -> productType.getOwnFields().stream())
                .peek(fieldType -> resolveCompositionType(schemaContext, fieldType))
                .collect(Collectors.toCollection(LinkedHashSet::new));

        Map<String, Set<FieldType>> fieldMap = fields.stream()
                .collect(Collectors.groupingBy(
                        fieldType -> Objects.isNull(fieldType.getName()) ? "" : fieldType.getName(),
                        Collectors.toSet()));

        Set<Map.Entry<String, Set<FieldType>>> entries = fieldMap.entrySet()
                .stream()
                .filter(entry-> entry.getValue().size()>1)
                .collect(Collectors.toSet());

        // todo: check if a field type is also resolvable and resolve

        // happy case
        if(entries.isEmpty())
            return new ProductType(fields);

        // todo: need resolve work
        throw new AdtException("can't resolve");
    }

    private static void resolveCompositionType(SchemaContext schemaContext, FieldType fieldType) {
        if(fieldType.getType() instanceof CompositionType){
            String name = generateUniqueFQN(schemaContext.getName());
            schemaContext.registerNamedType(new NamedType(name, fieldType.getType()));
            fieldType.setType(new ReferenceNamedType(name));
        }
    }

    private ProductType mergeProductTypes(ProductType baseFields, ProductType resolvableFields){
        resolvableFields.getOwnFields().forEach(baseFields::addField);
        return baseFields;
    }

    @Override
    public int size(){
        return fieldTypeCollection.getFields().size();
    }

    private static String generateUniqueName() {
        String uniqueID = UUID.randomUUID().toString().replace("-", "");
        return "Anonymous_" + uniqueID;
    }

    private static String generateUniqueFQN(String packageName) {
        String uniqueName = generateUniqueName();
        if (packageName != null && !packageName.isEmpty()) {
            return packageName + "." + uniqueName;
        }
        return uniqueName;
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

