package data.modeling.adt.mappers.jsonschemadraft7ToAdt.mappers;

import data.modeling.adt.SchemaContext;
import data.modeling.adt.exceptions.AdtException;
import data.modeling.adt.mappers.registries.ToAdtMapperRegistry;
import data.modeling.adt.typedefs.ReferencedDefinition;

import java.util.Map;

public class JsonSchemaRefMapper extends JsonSchemaMapper<Map<String, Object>, ReferencedDefinition> {

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
    public ReferencedDefinition toAdt(Map<String, Object> value) throws AdtException {
        // register the reference such that we can lazy load it at the end
        String ref = value.remove("$ref").toString();

        // todo: the following won't work for open-api schemes
        String definitionName = ref.substring("#/definitions/".length());
        String id = (String)jsonSchemaDoc.get("$id"); // todo: change to context property
        ReferencedDefinition referencedDefinition = new ReferencedDefinition(createReferencedNamed(id, definitionName));
        this.getJsonSchemaReferences().addReference(referencedDefinition);

        return referencedDefinition;
    }

    public static String createReferencedNamed(String id, String definitionName){
        return id+"_"+definitionName;
    }

}
