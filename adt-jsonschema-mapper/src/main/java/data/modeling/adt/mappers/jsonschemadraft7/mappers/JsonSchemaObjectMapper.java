package data.modeling.adt.mappers.jsonschemadraft7.mappers;

import data.modeling.adt.SchemaContext;
import data.modeling.adt.abstraction.annotations.GenericAnnotation;
import data.modeling.adt.abstraction.registries.ToAdtMapperRegistry;
import data.modeling.adt.abstraction.typedefs.FieldType;
import data.modeling.adt.abstraction.typedefs.ProductType;
import data.modeling.adt.abstraction.typedefs.ReferenceNamedType;
import data.modeling.adt.util.LambdaExceptionUtil;

import java.util.LinkedHashSet;
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
        Stream<FieldType> namedTypeStream = properties.entrySet().stream().map(LambdaExceptionUtil.function(
                (entry) ->
                {
                    Map<String, Object> fieldMap = (Map<String, Object>)entry.getValue();
                    FieldType fieldType = FieldType.of(entry.getKey(), toAdtMapperRegistry.toAdt(fieldMap));
                    fieldMap.keySet().forEach(key->{
                        fieldType.getAnnotations().add(new GenericAnnotation(key, fieldMap.get(key)));
                    });
                    return fieldType;
                }
        ));
        return ProductType.of(namedTypeStream, extendedProducts);
    }
}
