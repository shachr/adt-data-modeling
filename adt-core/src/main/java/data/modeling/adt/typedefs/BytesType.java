package data.modeling.adt.typedefs;

public class BytesType extends ScalarType {

    @Override
    public boolean isValueOf(Object value) {
        return value instanceof Boolean;
    }
}
