package data.modeling.adt.pipelines.schemaconvertion.converters;

import data.modeling.adt.SchemaContext;
import data.modeling.adt.abstraction.annotations.Annotation;
import data.modeling.adt.abstraction.visitors.AdtVisitor;
import data.modeling.adt.exceptions.AdtException;
import data.modeling.adt.typedefs.*;
import data.modeling.adt.util.LambdaExceptionUtil;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AdtToIDL implements AdtVisitor {

    // todo: [IDL] `patternProperties` keyword is purely validation oriented and not supported??
    // todo: [IDL] 'not' keyword is purely validation oriented and not supported??
    // todo: [IDL] `unevaluatedProperties` keyword is purely validation oriented and not supported??
    // todo: [IDL] `propertyNames` keyword is purely validation oriented and not supported??
    // todo: [IDL] `min/maxProperties` keyword, is not forward transitive, thus not supported?
    // todo: [IDL] anything other `additionalProperties`: true, is sealing a class, should fail on an extension attempt

    private final SchemaContext schemaContext;
    private LabeledType lastSeenLabeledType;

    public AdtToIDL(SchemaContext schemaContext){

        this.schemaContext = schemaContext;
    }

    public SchemaContext apply(){
        this.schemaContext.accept(this);
        return this.schemaContext;
    }

    @Override
    public void enterLabeledType(LabeledType type) {
        lastSeenLabeledType = type;
    }

    @Override
    public void exitLabeledType(LabeledType type) {
        lastSeenLabeledType = null;
    }

    @Override
    public void visit(ProductType type) throws AdtException {
        if(lastSeenLabeledType instanceof NamedType) {
            ProductType productType = productTypeToIDL(type);
            lastSeenLabeledType.setType(productType);
        }
    }
    private CompositionType compositionTypeToIDL(CompositionType compositionType) throws AdtException {
        if(compositionType instanceof ProductType) {
            return productTypeToIDL((ProductType) compositionType);
        } else if(compositionType instanceof AllOfType){
            allOfTypeToIDL((AllOfType) compositionType);
        }  else if(compositionType instanceof SumType) {
            return sumTypeToIDL((SumType) compositionType);
        }
        return compositionType;
    }

    private CompositionType allOfTypeToIDL(AllOfType allOfType){
        // todo: support interfaces, open-api style, e.g. allOf
        //  interfaces are referenced from product types, cannot have other references, e.g. from fields
        if(allOfType.getTypes().size()>1) {
            LinkedHashSet<AnyType> potentialInterfaces = allOfType.getTypes().stream()
                    .limit(allOfType.getTypes().size() - 1)
                    .collect(Collectors.toCollection(LinkedHashSet::new));

            // IDLized and label anonymous composition types
            potentialInterfaces = potentialInterfaces.stream()
                    .map(LambdaExceptionUtil.function(type -> {
                        if(type instanceof CompositionType) {
                            // todo: fail and ask to label
                            CompositionType compositionType = compositionTypeToIDL((CompositionType) type);
                            return labelAnonymousCompositionTypes(compositionType);
                        }
                        else {
                            return type;
                        }
                    }))
                    .collect(Collectors.toCollection(LinkedHashSet::new));

            AnyType extendingProduct = allOfType.getTypes().stream()
                    .reduce((prev, curr) -> curr).get();

            boolean IsInterfaces = potentialInterfaces.stream().allMatch(type -> type instanceof ReferenceNamedType || type instanceof ProductType);
            boolean isExtendingProduct = extendingProduct instanceof ProductType;

            if (IsInterfaces && isExtendingProduct) {
                return ProductType.of(potentialInterfaces.stream()
                                .map(type -> (ReferenceNamedType)type)
                                .collect(Collectors.toCollection(LinkedHashSet::new)),
                        ((ProductType) extendingProduct).getOwnFields().stream()
                );
            }
        }

        return allOfType;
    }


    private CompositionType sumTypeToIDL(SumType sumType) throws AdtException {
        //  !(compositionType instanceof EnumType)
        return sumType;
    }
    private ProductType productTypeToIDL(ProductType productType) throws AdtException {
        // todo: throw exception when there is no way to unwrap, this will impact conversion to code gen
        //  or languages that do not support all the ADT types that are mostly aligned with json-schema.

        // todo: json-schema to codegen tool: https://www.jsonschema2pojo.org/ style

        Map<Boolean, List<FieldType>> splitFieldList = productType.getOwnFields().stream()
                .collect(Collectors.partitioningBy((fieldType -> fieldType instanceof CompositionType)));

        // Get the list of non-resolvable fields
        List<FieldType> fieldsOfTypeNotComposition = splitFieldList.get(false);
        List<FieldType> fieldsOfTypeComposition = splitFieldList.get(true);

        ProductType productTypeNoAnon = resolveAnonymousFieldTypes(productType);
        if(productType.getImplements().isEmpty() && fieldsOfTypeComposition.isEmpty()) {
            return productTypeNoAnon;
        }

        ProductType baseProductType = ProductType.of(productTypeNoAnon.getImplements(), fieldsOfTypeNotComposition.stream());


        // extending products
        Set<AnyType> extendingTypes = resolveExtendingReferences(productType);

        // resolve inheritance
//        Stream<ProductType> extendingProductTypes = extendingTypes.stream().map(
//                LambdaExceptionUtil.function(extendingType -> productTypeToIDL((ProductType)extendingType)));

//        ProductType baseFields = mergeInheritanceIn(extendingProductTypes, baseProductType);

        // Get the list of resolvable fields
        ProductType resolvedFields = mergeResolvableFields(fieldsOfTypeComposition.stream());

        return mergeProductTypes(baseProductType, resolvedFields);
    }

    private Set<AnyType> resolveExtendingReferences(ProductType productType) {
        return productType.getImplements().stream()
                .map(referencedType -> {
                    NamedType namedType = schemaContext.getNamedType(referencedType.getReferenceName());
                    return namedType.getType();
                })
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    private ProductType resolveAnonymousFieldTypes(ProductType productType){
        return ProductType.of(productType.getImplements(), productType.getOwnFields().stream()
                .peek(LambdaExceptionUtil.consumer(this::tryLabelAnonymousCompositionTypes)));
    }

    private ProductType mergeInheritanceIn(Stream<ProductType> resolvedProductTypes, ProductType extendedProductType) throws AdtException {
        Set<FieldType> extendingFields = resolveAnonymousFieldTypes(extendedProductType).getOwnFields();

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
                .peek(LambdaExceptionUtil.consumer(this::tryLabelAnonymousCompositionTypes))
                .collect(Collectors.toCollection(LinkedHashSet::new));

        fieldTypeSet.addAll(extendingFields);
        return new ProductType(fieldTypeSet);
    }

    private ProductType mergeResolvableFields(Stream<FieldType> resolvableFieldsStream) throws AdtException {
        Set<FieldType> fields = resolvableFieldsStream
                .map(LambdaExceptionUtil.function(this::fieldTypeToIDL))
                .peek(LambdaExceptionUtil.consumer(this::tryLabelAnonymousCompositionTypes))
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
    private void tryLabelAnonymousCompositionTypes(FieldType fieldType) throws AdtException {
        if(fieldType.getType() instanceof CompositionType){
            fieldType.setType(labelAnonymousCompositionTypes((CompositionType) fieldType.getType()));
        }
    }
    private ReferenceNamedType labelAnonymousCompositionTypes(CompositionType anyType) throws AdtException {
        throw new AdtException("anonymous type found");
//        String name = generateUniqueFQN(schemaContext.getName());
//        schemaContext.registerNamedType(new NamedType(name, anyType));
//        return new ReferenceNamedType(name);
    }

    private static String generateUniqueFQN(String packageName) {
        String uniqueName = generateUniqueName();
        if (packageName != null && !packageName.isEmpty()) {
            return packageName + "." + uniqueName;
        }
        return uniqueName;
    }

    private static String generateUniqueName() {
        String uniqueID = UUID.randomUUID().toString().replace("-", "");
        return "Anonymous_" + uniqueID;
    }

    private ProductType mergeProductTypes(ProductType baseFields, ProductType resolvableFields){
        resolvableFields.getOwnFields().forEach(baseFields::addField);
        return baseFields;
    }

    public FieldType fieldTypeToIDL(FieldType fieldType) throws AdtException {
        if(fieldType.getType() instanceof CompositionType){
            fieldType.setType(compositionTypeToIDL((CompositionType)fieldType.getType()));
        }

        return fieldType;
    }

    @Override
    public void visit(SumType type) throws AdtException {
        if(lastSeenLabeledType instanceof NamedType) {
            CompositionType compositionType = sumTypeToIDL(type);
            lastSeenLabeledType.setType(compositionType);
        }
    }

    @Override
    public void visit(AllOfType type) throws AdtException {
        if(lastSeenLabeledType instanceof NamedType) {
            CompositionType compositionType = allOfTypeToIDL(type);
            lastSeenLabeledType.setType(compositionType);
        }

    }

    @Override
    public void visit(AnyOfType type) throws AdtException {

    }

    @Override
    public void visit(ReferenceNamedType type) {

    }

    @Override
    public void visit(Set<Annotation<?>> annotations) {

    }

    @Override
    public void visit(Annotation<?> annotation) {

    }

    @Override
    public void visit(NumericType type) {

    }

    @Override
    public void visit(PrimitiveType type) {

    }

    @Override
    public void visit(CollectionType type) {

    }

    @Override
    public void visit(TemporalType type) {

    }

    @Override
    public void visit(AnyType type) {

    }
}
