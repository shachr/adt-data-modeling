package data.modeling.adt.mappers.jsonschemadraft7ToAdt.mappers;

import data.modeling.adt.SchemaContext;
import data.modeling.adt.exceptions.AdtException;
import data.modeling.adt.mappers.jsonschemadraft7ToAdt.annotations.JsonSchemaAnnotation;
import data.modeling.adt.mappers.registries.ToAdtMapperRegistry;
import data.modeling.adt.typedefs.NamedType;
import data.modeling.adt.typedefs.ReferenceNamedType;

import java.util.Map;

public class JsonSchemaRefMapper extends JsonSchemaMapper<ReferenceNamedType> {

    private Map<String, Object> jsonSchemaDoc;
    private ToAdtMapperRegistry adtMapperRegistry;
    private SchemaContext schemaContext;

    public JsonSchemaRefMapper(Map<String, Object> jsonSchemaDoc, ToAdtMapperRegistry adtMapperRegistry, SchemaContext schemaContext){
        this.jsonSchemaDoc = jsonSchemaDoc;

        this.adtMapperRegistry = adtMapperRegistry;
        this.schemaContext = schemaContext;
    }

    @Override
    public boolean canMap(Map<String, Object> value) {
        return value.containsKey("$ref");
    }

    @Override
    public ReferenceNamedType toAdt(Map<String, Object> value) throws AdtException {
        // todo: add logic
        // register the reference such that we can lazy load it at the end
        String ref = value.remove("$ref").toString();

        // todo: the following won't work for open-api schemes
        String definitionName = ref.substring("#/definitions/".length());
        ReferenceNamedType referenceNamedType = new ReferenceNamedType(ref);
        this.getJsonSchemaReferences().addReference(referenceNamedType);

        // read definition
        Map definitions = (Map)this.jsonSchemaDoc.get("definitions");
        Map definition = (Map)definitions.get(definitionName);
        NamedType namedType = NamedType.of(referenceNamedType.getReferenceName(), this.adtMapperRegistry.toAdt(definition));
        definition.keySet().forEach(key->{
            namedType.getAnnotations().add(new JsonSchemaAnnotation(key.toString(), definition.get(key)));
        });
        schemaContext.registerNamedType(namedType);

        return  referenceNamedType;
    }
}
