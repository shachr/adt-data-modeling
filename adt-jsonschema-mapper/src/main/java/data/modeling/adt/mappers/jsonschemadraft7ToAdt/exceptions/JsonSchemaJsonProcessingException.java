package data.modeling.adt.mappers.jsonschemadraft7ToAdt.exceptions;

import data.modeling.adt.exceptions.MapToAdtException;

public class JsonSchemaJsonProcessingException extends MapToAdtException {
    public JsonSchemaJsonProcessingException(Exception ex){
        super("unable to parse json string", ex);
    }
}
