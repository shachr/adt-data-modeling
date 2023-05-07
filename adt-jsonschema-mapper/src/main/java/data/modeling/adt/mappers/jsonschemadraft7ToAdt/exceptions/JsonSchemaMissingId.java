package data.modeling.adt.mappers.jsonschemadraft7ToAdt.exceptions;

import data.modeling.adt.exceptions.MapToAdtException;

public class JsonSchemaMissingId extends MapToAdtException {
    public JsonSchemaMissingId(){
        super("missing $id");
    }
}
