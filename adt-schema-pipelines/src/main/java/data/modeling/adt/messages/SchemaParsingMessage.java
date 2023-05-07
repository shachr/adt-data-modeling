package data.modeling.adt.messages;


import data.modeling.adt.abstraction.Message;

public class SchemaParsingMessage implements Message {
    private String contentType;
    private Object message;

    public SchemaParsingMessage(String contentType, Object message){

        this.contentType = contentType;
        this.message = message;
    }

    public String getContentType() {
        return contentType;
    }

    public Object getMessage() {
        return message;
    }
}
