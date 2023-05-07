package data.modeling.adt.messages;

import data.modeling.adt.abstraction.Message;

public class SchemaConvertedMessage implements Message {
    private String schemaContent;
    private String contentType;

    public SchemaConvertedMessage(String contentType, String schemaContent){
        this.contentType = contentType;
        this.schemaContent = schemaContent;
    }

    public String getSchemaContent() {
        return schemaContent;
    }

    public String getContentType() {
        return contentType;
    }
}
