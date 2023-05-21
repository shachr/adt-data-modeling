package data.modeling.adt.typedefs;

public class BoolType extends ScalarType {

    @Override
    public boolean isValueOf(Object value) {
        return value instanceof Boolean;
    }
}
