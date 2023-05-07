package data.modeling.adt.messages;

import data.modeling.adt.SchemaContext;
import data.modeling.adt.abstraction.Message;

public class SchemaConvertionMessage implements Message {
    private String typeName;
    private SchemaContext schemaContext;
    private String contentType;

    public SchemaConvertionMessage(String contentType, String typeName, SchemaContext schemaContext){
        this.typeName = typeName;

        this.schemaContext = schemaContext;
        this.contentType = contentType;
    }

    public SchemaContext getSchemaContext() {
        return schemaContext;
    }

    public String getContentType() {
        return contentType;
    }

    public String getTypeName() {
        return typeName;
    }
}