package data.modeling.adt.exceptions;

public class AdtException extends Exception {
    public AdtException(){}
    public AdtException(String message){
        super(message);
    }
    public AdtException(String message, Throwable throwable){
        super(message, throwable);
    }

}
