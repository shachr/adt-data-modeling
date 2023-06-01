package data.modeling.adt.mappers.jsonschemadraft7ToAdt.visitors;

import data.modeling.adt.SchemaContext;
import data.modeling.adt.abstraction.annotations.Annotation;
import data.modeling.adt.abstraction.visitors.AdtVisitor;
import data.modeling.adt.exceptions.AdtException;
import data.modeling.adt.typedefs.*;
import data.modeling.adt.util.LambdaExceptionUtil;
import data.modeling.adt.util.StreamUtil;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SchemaCompositionToAdt implements AdtVisitor {

    // todo: [IDL] `patternProperties` keyword is purely validation oriented and not supported??
    // todo: [IDL] 'not' keyword is purely validation oriented and not supported??
    // todo: [IDL] `unevaluatedProperties` keyword is purely validation oriented and not supported??
    // todo: [IDL] `propertyNames` keyword is purely validation oriented and not supported??
    // todo: [IDL] `min/maxProperties` keyword, is not forward transitive, thus not supported?
    // todo: [IDL] `additionalProperties`: false, is sealing a class, should fail on an extension attempt
    // todo: https://github.com/json-schema-org/vocab-idl

    /*
    allOf:
    To adapt allOf to a Product type, one of the following conditions must be met:
     - All items in the allOf array are $ref references.
     - All first items in the allOf array are $ref references, and all last items are of type object.
     - All items in the allOf array are of type object and have compatible property types, allowing for property merging.

    anyOf:
    To adapt anyOf to a Product type, one of the following conditions must be met:
     - All items in the anyOf array are of type object and have the same set of properties.

     if/else/then:
     To adapt if/else/then to a Product type, one of the following conditions must be met:
     TBD
     */

    private final SchemaContext schemaContext;
    private Definition lastSeenDefinition;

    public SchemaCompositionToAdt(SchemaContext schemaContext){

        this.schemaContext = schemaContext;
    }

    public SchemaContext apply(){
        this.schemaContext.accept(this);
        return this.schemaContext;
    }

    @Override
    public void enterLabeledType(Definition<?> type) {
        lastSeenDefinition = type;
    }

    @Override
    public void exitLabeledType(Definition<?> type) {
        lastSeenDefinition = null;
    }

    @Override
    public void visit(ProductType type) throws AdtException {
        if(lastSeenDefinition instanceof TypeDefinition) {
            ProductType productType = productTypeFieldsToAdt(type);
            lastSeenDefinition.setType(productType);
        }
    }
    private CompositionType compositionTypeToIDL(CompositionType compositionType) throws AdtException {
        if(compositionType instanceof ProductType productType) {
            return productTypeFieldsToAdt(productType);
        } else if(compositionType instanceof AllOfType allOfType) {
            return allOfTypeToProductType(allOfType);
        } else if(compositionType instanceof AnyOfType anyOfType){
            return anyOfToProductType(anyOfType);
        }  else if(compositionType instanceof SumType sumType) {
            return sumTypeToIDL(sumType);
        }
        return compositionType;
    }

    private ProductType anyOfToProductType(AnyOfType anyOfType) throws AdtException{
        if(anyOfType.getTypes().isEmpty()){
            return new ProductType();
        }
        else if(checkAnyOfValidForProductType(anyOfType)){
            Map<Boolean, List<AnyType>> partitions =  anyOfType.getTypes().stream().collect(Collectors.partitioningBy(item -> item instanceof ProductType));
            Stream<ProductType> productTypes = partitions.get(true).stream().map(item -> (ProductType)item);
            Stream<ProductType> productTypes2 = partitions.get(false).stream()
                    .map(item -> (ReferencedDefinition)item)
                    .map(ref -> (ProductType)schemaContext.getNamedType(ref.getReferenceName()).getType());
            Stream<ProductType> mergedStream = StreamUtil.concatStreams(productTypes, productTypes2);
            return mergedStream.reduce(this::mergeProductTypes).get();
        }
        throw new AdtException("not supported");
    }


    private boolean checkAnyOfValidForProductType(AnyOfType anyOfType) {
        List<? extends AnyType> types = anyOfType.getTypes().stream().toList();

        int lastIndex = types.size() - 1;
        boolean isValid = true;

        for (int i = 0; i < lastIndex; i++) {
            AnyType item = types.get(i);
            if(item instanceof ReferencedDefinition referencedDefinition){
                Definition<ComplexType> definition = schemaContext.getNamedType(referencedDefinition.getReferenceName());
                if(!(definition.isProductTypeDefinition())){
                    isValid = false;
                    break;
                }
            } else if(!(item instanceof ProductType)){
                isValid = false;
                break;
            }
        }

        return isValid;
    }


    /**
     *To adapt allOf to a Product type, one of the following conditions must be met:
     * 1) All items in the allOf array are $ref references.
     * 2) All items in the allOf array start with types of ReferenceNamedType and end with ProductType types.
     * 3) All items in the allOf array are of type object and have compatible property types, allowing for property merging.
     * @param allOfType the allOf type
     * @return product type
     * @throws AdtException when its impossible to adapt to product type
     */
    private ProductType allOfTypeToProductType(AllOfType allOfType) throws AdtException {
        if (allOfType.getTypes().isEmpty()) {
            return new ProductType();
        } else if (checkAllOfIsValidForProductType(allOfType)) {

            Map<Boolean, List<AnyType>> partitionMap = allOfType
                    .getTypes().stream()
                    .map(item -> (AnyType) item)
                    .collect(Collectors.partitioningBy(item -> item instanceof ProductType));

            LinkedHashSet<ReferencedDefinition> referencedDefinitions = partitionMap.get(false).stream()
                    .map(item -> (ReferencedDefinition) item)
                    .collect(Collectors.toCollection(LinkedHashSet::new));

            List<ProductType> productTypes = partitionMap.get(true).stream()
                    .map(LambdaExceptionUtil.function(item -> productTypeFieldsToAdt((ProductType) item)))
                    .toList();

            ProductType mergedProductType = productTypes.stream()
                    .reduce(this::mergeProductTypes)
                    .orElse(new ProductType());

            mergedProductType.setImplements(referencedDefinitions);
            return mergedProductType;
        } else {
            throw new AdtException("not supported");
        }
    }

    private boolean checkAllOfIsValidForProductType(AllOfType allOfType) {
        List<? extends AnyType> types = allOfType.getTypes().stream().toList();

        int lastIndex = types.size() - 1;

        boolean isValid = true;
        boolean isRefFound = false;
        boolean isProductTypeFound = false;

        for (int i = 0; i < lastIndex; i++) {
            AnyType item = types.get(i);
            if(!(item instanceof ReferencedDefinition || item instanceof ProductType)) {
                isValid = false;
                break;
            }
            if(item instanceof ReferencedDefinition) {
                if(isProductTypeFound) {
                    isValid = false;
                    break;
                }
                isRefFound = true;
            } else {
                isProductTypeFound = true;
            }
        }

        isValid = isValid && (isRefFound || isProductTypeFound);
        return isValid;
    }

    private CompositionType sumTypeToIDL(SumType sumType) throws AdtException {
        //  !(compositionType instanceof EnumType)
        return sumType;
    }
    private ProductType productTypeFieldsToAdt(ProductType productType) throws AdtException {
        //todo: handle sum types as well.
        Map<Boolean, List<FieldDefinition>> splitFieldList = productType.getOwnFields().stream()
                .collect(Collectors.partitioningBy((fieldType -> fieldType.getType() instanceof CompositionType && !(fieldType.getType() instanceof SumType))));

        // Get the list of non-resolvable fields
        List<FieldDefinition> fieldsOfTypeNotComposition = splitFieldList.get(false);
        List<FieldDefinition> fieldsOfTypeComposition = splitFieldList.get(true);

        if(fieldsOfTypeComposition.isEmpty()) {
            return productType;
        }

        ProductType baseProductType = ProductType.of(productType.getImplements(), fieldsOfTypeNotComposition.stream());
        ProductType resolvedFields = mergeFieldsCompositions(fieldsOfTypeComposition.stream());

        return mergeProductTypes(baseProductType, resolvedFields);
    }

    private ProductType mergeFieldsCompositions(Stream<FieldDefinition> resolvableFieldsStream) {

        Set<FieldDefinition> fields = resolvableFieldsStream
                .peek(LambdaExceptionUtil.consumer(fieldType -> {
                    fieldType.setType(compositionTypeToIDL((CompositionType)fieldType.getType()));
                }))
                .collect(Collectors.toCollection(LinkedHashSet::new));

        Map<String, Set<FieldDefinition>> fieldMap = fields.stream()
                .collect(Collectors.groupingBy(
                        fieldType -> Objects.isNull(fieldType.getName()) ? "" : fieldType.getName(),
                        Collectors.toCollection(LinkedHashSet::new)));

        return fieldMap.entrySet().stream()
                .map(LambdaExceptionUtil.function(entry -> {
                    if(!entry.getValue().stream().allMatch(item -> item.getType() instanceof ProductType)){
                        throw new AdtException("not supported");
                    }

                    return entry.getValue().stream()
                            .map(fieldType -> (ProductType)fieldType.getType())
                            .reduce(this::mergeProductTypes).orElse(new ProductType());
                }))
                .reduce(this::mergeProductTypes).orElse(new ProductType());
    }


    private ProductType mergeProductTypes(ProductType baseFields, ProductType resolvableFields){
        resolvableFields.getOwnFields().forEach(baseFields::addField);
        return baseFields;
    }

    @Override
    public void visit(SumType type) throws AdtException {
        if(lastSeenDefinition instanceof TypeDefinition) {
            CompositionType compositionType = sumTypeToIDL(type);
            lastSeenDefinition.setType(compositionType);
        }
    }

    @Override
    public void visit(AllOfType type) throws AdtException {
        if(lastSeenDefinition instanceof TypeDefinition) {
            CompositionType compositionType = allOfTypeToProductType(type);
            lastSeenDefinition.setType(compositionType);
        }

    }

    @Override
    public void visit(AnyOfType type) {

    }

    @Override
    public void visit(ReferencedDefinition type) {

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
    public void visit(ScalarType type) {

    }

    @Override
    public void visit(CollectionType type) {

    }

    @Override
    public void visit(TimestampType type) {

    }

    @Override
    public void visit(AnyType type) {

    }

    @Override
    public void visit(TypeModifier type) throws AdtException {

    }
}
