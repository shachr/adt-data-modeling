package data.modeling.adt.pipelines.schemavalidation;

import data.modeling.adt.messages.SchemaConvertedMessage;
import data.modeling.adt.messages.SchemaConvertionMessage;
import data.modeling.adt.messages.SchemaParsedMessage;
import data.modeling.adt.messages.SchemaValidationMessage;
import data.modeling.processing.abstraction.Task;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class SchemaValidationPipeline {
    private List<Task<SchemaParsedMessage, SchemaValidationMessage>> converters = new LinkedList<>();

    public SchemaValidationPipeline() {}

    public void registerConverter(Task<SchemaParsedMessage, SchemaValidationMessage> converter){
        converters.add(converter);
    }

    public SchemaValidationMessage apply(SchemaParsedMessage msg) throws Exception {
        Optional<Task<SchemaParsedMessage, SchemaValidationMessage>> filterOpt = converters.stream()
                .filter(rawSchemaMessageParser -> rawSchemaMessageParser.shouldExecute(msg))
                .findFirst();
        return filterOpt.get().execute(msg);
    }
}
