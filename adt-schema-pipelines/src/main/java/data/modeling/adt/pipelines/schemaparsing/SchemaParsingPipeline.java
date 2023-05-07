package data.modeling.adt.pipelines.schemaparsing;

import data.modeling.adt.messages.SchemaParsingMessage;
import data.modeling.adt.messages.SchemaParsedMessage;
import data.modeling.processing.abstraction.Task;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class SchemaParsingPipeline {
    private List<Task<SchemaParsingMessage, SchemaParsedMessage>> filters = new LinkedList<>();

    public SchemaParsingPipeline() {}

    public void registerParser(Task<SchemaParsingMessage, SchemaParsedMessage> parser){
        filters.add(parser);
    }

    public SchemaParsedMessage apply(SchemaParsingMessage msg) throws Exception {
        Optional<Task<SchemaParsingMessage, SchemaParsedMessage>> filterOpt = filters.stream()
                .filter(rawSchemaMessageParser -> rawSchemaMessageParser.shouldExecute(msg))
                .findFirst();
        return filterOpt.get().execute(msg);
    }
}
