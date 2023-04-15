package data.modeling.adt.mappers.jsonschemadraft7ToAdt.mappers;

import data.modeling.adt.SchemaContext;
import data.modeling.adt.mappers.jsonschemadraft7ToAdt.annotations.JsonSchemaAnnotation;
import data.modeling.adt.mappers.registries.ToAdtMapperRegistry;
import data.modeling.adt.typedefs.FieldType;
import data.modeling.adt.typedefs.ProductType;
import data.modeling.adt.typedefs.ReferenceNamedType;
import data.modeling.adt.util.LambdaExceptionUtil;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class JsonSchemaObjectMapper extends JsonSchemaMapper<ProductType> {

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
        return value.containsKey("properties");
    }

    @Override
    public ProductType toAdt(Map<String, Object> value) {
        // todo: add logic
        value.remove("type");
        Map<String, Object> properties = (Map<String, Object>)value.remove("properties");
        Stream<FieldType> fieldTypeStream = properties.entrySet().stream().map(LambdaExceptionUtil.function(
                (entry) ->
                {
                    Map<String, Object> fieldMap = (Map<String, Object>)entry.getValue();
                    FieldType fieldType = FieldType.of(entry.getKey(), toAdtMapperRegistry.toAdt(fieldMap));
                    fieldMap.keySet().forEach(key->{
                        fieldType.getAnnotations().add(new JsonSchemaAnnotation(key, fieldMap.get(key)));
                    });
                    return fieldType;
                }
        ));

        ProductType productType = ProductType.of(extendedProducts, fieldTypeStream);
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
