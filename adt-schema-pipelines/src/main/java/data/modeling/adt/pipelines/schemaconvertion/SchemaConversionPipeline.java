package data.modeling.adt.pipelines.schemaconvertion;

import data.modeling.adt.messages.SchemaConvertedMessage;
import data.modeling.adt.messages.SchemaConvertionMessage;
import data.modeling.processing.abstraction.Task;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class SchemaConversionPipeline {
    private List<Task<SchemaConvertionMessage, SchemaConvertedMessage>> converters = new LinkedList<>();

    public SchemaConversionPipeline() {}

    public void registerConverter(Task<SchemaConvertionMessage, SchemaConvertedMessage> converter){
        converters.add(converter);
    }

    public SchemaConvertedMessage apply(SchemaConvertionMessage msg) throws Exception {
        Optional<Task<SchemaConvertionMessage, SchemaConvertedMessage>> filterOpt = converters.stream()
                .filter(rawSchemaMessageParser -> rawSchemaMessageParser.shouldExecute(msg))
                .findFirst();
        return filterOpt.get().execute(msg);
    }
}
