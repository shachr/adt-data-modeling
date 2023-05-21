package data.modeling.adt.mappers.jsonschemadraft7ToAdt.mappers;

import data.modeling.adt.SchemaContext;
import data.modeling.adt.exceptions.AdtException;
import data.modeling.adt.mappers.jsonschemadraft7ToAdt.annotations.JsonSchemaAnnotation;
import data.modeling.adt.mappers.registries.ToAdtMapperRegistry;
import data.modeling.adt.typedefs.*;
import data.modeling.adt.util.LambdaExceptionUtil;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JsonSchemaObjectMapper extends JsonSchemaMapper<Map<String, Object>, ProductType> {

    private ToAdtMapperRegistry toAdtMapperRegistry;
    private LinkedHashSet<ReferenceNamedType> extendedProducts;
    private SchemaContext schemaContext;

    public JsonSchemaObjectMapper(ToAdtMapperRegistry toAdtMapperRegistry, SchemaContext schemaContext) {
        this.schemaContext = schemaContext;
        this.extendedProducts = new LinkedHashSet<>();
        this.toAdtMapperRegistry = toAdtMapperRegistry;
    }

    public JsonSchemaObjectMapper(ToAdtMapperRegistry toAdtMapperRegistry, LinkedHashSet<ReferenceNamedType> extendedProducts) {

        this.toAdtMapperRegistry = toAdtMapperRegistry;
        this.extendedProducts = extendedProducts;
    }

    @Override
    public boolean canMap(Map<String, Object> value) {
        return value.containsKey("properties") || (value.getOrDefault("type", "object").equals("object"));
    }

    @Override
    public ProductType toAdt(Map<String, Object> value) throws AdtException {
        // todo: add logic
        value.remove("type");
        Object additionalProperties = value.remove("additionalProperties");
        final boolean isSealed = !Objects.isNull(additionalProperties) && additionalProperties.equals(false);

        Stream<FieldType> fieldTypeStream = Stream.empty();
        Map<String, Object> properties = (Map<String, Object>)value.remove("properties");
        if(!Objects.isNull(properties)) {
            fieldTypeStream = properties.entrySet().stream().map(LambdaExceptionUtil.function(
                    (entry) ->
                    {
                        Map<String, Object> fieldMap = (Map<String, Object>) entry.getValue();
                        AnyType fieldAdtType = toAdtMapperRegistry.toAdt(fieldMap);
                        FieldType fieldType = FieldType.of(entry.getKey(), fieldAdtType);
                        fieldMap.keySet().forEach(key ->
                                fieldType.getAnnotations().add(new JsonSchemaAnnotation(key, fieldMap.get(key))));
                        return fieldType;
                    }
            ));
        }

        if(!Objects.isNull(additionalProperties) && !isSealed){
            FieldAdditionalTypes fieldType = new FieldAdditionalTypes(ProductType.of(new FieldType("additionalProperties", new MapType(new StringType(), toAdtMapperRegistry.toAdt(additionalProperties)))));
            List<FieldType> fields = fieldTypeStream.collect(Collectors.toList());
            fields.add(fieldType);
            fieldTypeStream = fields.stream();
        }
        
        ProductType productType = ProductType.of(extendedProducts, fieldTypeStream, isSealed);
        List<String> required = (List<String>)value.remove("required");
        if(null != required){
            required.forEach(fieldName -> {
                FieldType fieldType = productType.getField(fieldName);
                if(null != fieldType) {
                    fieldType.setRequired(true);
                }
            });
        }
        return productType;
    }
}