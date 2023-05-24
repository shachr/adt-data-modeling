package data.modeling.adt.typedefs;

public class ShortType extends ScalarType {

    @Override
    public boolean isValueOf(Object value) {
        return value instanceof Short;
    }
}
