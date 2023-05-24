package data.modeling.adt.typedefs;

public class ByteType extends ScalarType {

    @Override
    public boolean isValueOf(Object value) {
        return value instanceof Byte;
    }
}
