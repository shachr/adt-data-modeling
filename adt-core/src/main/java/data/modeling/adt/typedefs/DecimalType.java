package data.modeling.adt.typedefs;

import java.math.BigDecimal;

public class DecimalType extends NumericType {

    private final int precision;
    private final int scale;

    public DecimalType(int precision, int scale) {
        this.precision = precision;
        this.scale = scale;
    }

    public int getPrecision() {
        return precision;
    }

    public int getScale() {
        return scale;
    }

    public static ConstantPrimitiveType constantOf(BigDecimal value){
        return new ConstantPrimitiveType(new DecimalType(value.precision(), value.scale()), value);
    }
}
