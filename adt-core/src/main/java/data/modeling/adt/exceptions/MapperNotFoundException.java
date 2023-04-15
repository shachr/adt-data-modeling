package data.modeling.adt.exceptions;

public class MapperNotFoundException extends AdtException {
    public MapperNotFoundException(Object value){
        super("unable to find mapper for:\n\n" + value.toString());
    }
}
