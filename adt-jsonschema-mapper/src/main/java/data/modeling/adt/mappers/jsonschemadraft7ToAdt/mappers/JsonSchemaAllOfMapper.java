package data.modeling.adt.mappers.jsonschemadraft7ToAdt.mappers;

import data.modeling.adt.SchemaContext;
import data.modeling.adt.exceptions.AdtException;
import data.modeling.adt.abstraction.mappers.MapToAdt;
import data.modeling.adt.mappers.registries.ToAdtMapperRegistry;
import data.modeling.adt.typedefs.AnyType;
import data.modeling.adt.typedefs.ProductType;
import data.modeling.adt.typedefs.ReferenceNamedType;
import data.modeling.adt.util.LambdaExceptionUtil;
import data.modeling.adt.util.MapsUtil;

import java.util.*;
import java.util.stream.Collectors;

public class JsonSchemaAllOfMapper extends data.modeling.adt.mappers.jsonschemadraft7ToAdt.mappers.JsonSchemaMapper<ProductType> {

    private ToAdtMapperRegistry toAdtMapperRegistry;
    private SchemaContext schemaContext;

    public JsonSchemaAllOfMapper(ToAdtMapperRegistry toAdtMapperRegistry, SchemaContext schemaContext) {

        this.toAdtMapperRegistry = toAdtMapperRegistry;
        this.schemaContext = schemaContext;
    }

    @Override
    public boolean canMap(Map<String, Object> value) {
        return value.containsKey("allOf");
    }

    @Override
    public ProductType toAdt(Map<String, Object> value) throws AdtException {
        value.remove("type");
        List<Object> allOf = (List<Object>)value.remove("allOf");
        // if list has one item, there is no inheritance
        if(allOf.size() == 1){
            // expected to find an object w/ properties inside.
            MapToAdt mapper = toAdtMapperRegistry.findToMapper(allOf.get(0)).get();
            return (ProductType) mapper.toAdt(allOf.get(0));
        } else {
            List<Map> jsonSchemaAllOfObjects = extractJsonSchemaProperties(allOf);
            LinkedHashSet<ReferenceNamedType> extendedProductTypes = extractAllReferenceNamedTypes(allOf);

            if(!jsonSchemaAllOfObjects.isEmpty()) {
                Map mergedJsonSchemaObjects = MapsUtil.deepMergeMaps(jsonSchemaAllOfObjects);
                return new data.modeling.adt.mappers.jsonschemadraft7ToAdt.mappers.JsonSchemaObjectMapper(toAdtMapperRegistry, extendedProductTypes).toAdt(mergedJsonSchemaObjects);
            } else {
                return new ProductType(new HashSet<>(), extendedProductTypes);
            }
        }
    }

    private List<Map> extractJsonSchemaProperties(List<Object> allOf) {
        List<Map> jsonSchemaAllOfObjects = allOf.stream()
                .filter(map->isObjectWithProperties((Map)map))
                .map(map->(Map<String, Object>)map)
                .collect(Collectors.toList());
        return jsonSchemaAllOfObjects;
    }

    private LinkedHashSet<ReferenceNamedType> extractAllReferenceNamedTypes(List<Object> allOf) {
        Set<Object> jsonSchemaAllOfRefs = allOf.stream()
                .filter(map->!isObjectWithProperties((Map)map))
                .collect(Collectors.toSet());

        LinkedHashSet<ReferenceNamedType> extendedProductTypes = jsonSchemaAllOfRefs.stream()
                .map(LambdaExceptionUtil.function(jsonSchemaElem -> {
                    AnyType anyType = toAdtMapperRegistry.toAdt(jsonSchemaElem);
                    if(anyType instanceof ReferenceNamedType){
                        // #definitions
                        return (ReferenceNamedType)anyType;
                    } else {
                        // not supported
                        throw new AdtException("not supported");
                    }
                }))
                .collect(Collectors.toCollection(LinkedHashSet::new));
        return extendedProductTypes;
    }

    private boolean isObjectWithProperties(Map<String, Object> jsonSchemaMap){
        return jsonSchemaMap.containsKey("properties");
    }
}
