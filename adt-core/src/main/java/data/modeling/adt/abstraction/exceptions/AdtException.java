package data.modeling.adt.abstraction.exceptions;

public class AdtException extends Exception {
    public AdtException(){}
    public AdtException(String message){
        super(message);
    }
    public AdtException(String message, Throwable throwable){
        super(message, throwable);
    }

}
