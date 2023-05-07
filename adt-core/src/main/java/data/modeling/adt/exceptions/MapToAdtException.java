package data.modeling.adt.exceptions;

public class MapToAdtException extends AdtException {
    public MapToAdtException(){ super(); }
    public MapToAdtException(String message){
        super(message);
    }
    public MapToAdtException(String message, Throwable throwable){
        super(message, throwable);
    }
}
