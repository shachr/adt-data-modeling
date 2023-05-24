package data.modeling.adt.typedefs;

public class StringVarcharType extends StringBaseType {

    private int length;

    public StringVarcharType(int length){
        super();
        this.length = length;
    }

    @Override
    public boolean isValueOf(Object value) {
        return value instanceof String;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
