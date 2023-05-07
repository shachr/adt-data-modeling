package data.modeling.adt.messages;

import data.modeling.adt.SchemaContext;
import data.modeling.adt.abstraction.Message;

public class SchemaParsedMessage implements Message {
    private SchemaContext schemaContext;

    public SchemaParsedMessage(SchemaContext schemaContext){

        this.schemaContext = schemaContext;
    }

    public SchemaContext getSchemaContext() {
        return schemaContext;
    }
}
