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
    private LinkedHashSet<ReferencedDefinition> extendedProducts;
    private SchemaContext schemaContext;

    public JsonSchemaObjectMapper(ToAdtMapperRegistry toAdtMapperRegistry, SchemaContext schemaContext) {
        this.schemaContext = schemaContext;
        this.extendedProducts = new LinkedHashSet<>();
        this.toAdtMapperRegistry = toAdtMapperRegistry;
    }

    public JsonSchemaObjectMapper(ToAdtMapperRegistry toAdtMapperRegistry, LinkedHashSet<ReferencedDefinition> extendedProducts) {

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

        Stream<FieldDefinition> fieldTypeStream = Stream.empty();
        Map<String, Object> properties = (Map<String, Object>)value.remove("properties");
        if(!Objects.isNull(properties)) {
            fieldTypeStream = properties.entrySet().stream().map(LambdaExceptionUtil.function(
                    (entry) ->
                    {
                        Map<String, Object> fieldMap = (Map<String, Object>) entry.getValue();
                        AnyType fieldAdtType = toAdtMapperRegistry.toAdt(fieldMap);
                        FieldDefinition fieldDefinition = FieldDefinition.of(entry.getKey(), fieldAdtType);
                        fieldMap.keySet().forEach(key ->
                                fieldDefinition.getAnnotations().add(new JsonSchemaAnnotation(key, fieldMap.get(key))));
                        return fieldDefinition;
                    }
            ));
        }

        if(!Objects.isNull(additionalProperties) && !isSealed){
            FieldAdditionalDefinition fieldType = new FieldAdditionalDefinition(ProductType.of(new FieldDefinition("additionalProperties", new MapType(new StringType(), toAdtMapperRegistry.toAdt(additionalProperties)))));
            List<FieldDefinition> fields = fieldTypeStream.collect(Collectors.toList());
            fields.add(fieldType);
            fieldTypeStream = fields.stream();
        }
        
        ProductType productType = ProductType.of(extendedProducts, fieldTypeStream, isSealed);
        List<String> required = (List<String>)value.remove("required");
        if(null != required){
            required.forEach(fieldName -> {
                FieldDefinition fieldDefinition = productType.getField(fieldName);
                if(null != fieldDefinition) {
                    fieldDefinition.setRequired(true);
                }
            });
        }
        return productType;
    }
}