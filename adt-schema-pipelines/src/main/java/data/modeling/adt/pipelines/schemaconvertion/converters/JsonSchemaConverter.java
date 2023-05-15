package data.modeling.adt.pipelines.schemaconvertion.converters;

import data.modeling.adt.abstraction.artifacts.Artifact;
import data.modeling.adt.messages.SchemaConvertedMessage;
import data.modeling.adt.messages.SchemaConvertionMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import data.modeling.adt.mappers.jsonschemadraft7FromAdt.JsonSchemaDraft7FromAdt;
import data.modeling.adt.typedefs.NamedType;
import data.modeling.processing.abstraction.Task;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import static data.modeling.adt.util.StreamExtensions.toMap;

public class JsonSchemaConverter implements Task<SchemaConvertionMessage, SchemaConvertedMessage> {
    @Override
    public boolean shouldExecute(SchemaConvertionMessage message) {
        return message.getContentType().equals("application/schema+json");
    }

    @Override
    public SchemaConvertedMessage execute(SchemaConvertionMessage message) throws Exception {
        NamedType namedType = message.getSchemaContext().getNamedType(message.getTypeName());
        JsonSchemaDraft7FromAdt mapper = new JsonSchemaDraft7FromAdt(namedType, message.getSchemaContext());
        // todo: can stream be passed to objectmapper?
        Map<String, Object> map = toMap(mapper.stream());
        ObjectMapper objectMapper = new ObjectMapper();
        JsonSchemaFile jsonSchemaFile = new JsonSchemaFile(namedType.getName(), objectMapper.writer().writeValueAsString(map));
        Set<JsonSchemaFile> files = new LinkedHashSet<>();
        files.add(jsonSchemaFile);
        return new SchemaConvertedMessage(message.getContentType(), files);
    }


}
