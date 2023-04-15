package data.modeling.adt.mappers.jsonschemadraft7FromAdt.util;

import data.modeling.adt.typedefs.DecimalType;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MultipleOfConverter {

    public static DecimalType toDecimalType(Number multipleOf) {
        BigDecimal bigDecimal = new BigDecimal(multipleOf.toString());
        return new DecimalType(bigDecimal.precision(), bigDecimal.scale());
    }

    public static String toMultipleOf(DecimalType decimalType) {
        BigDecimal value = BigDecimal.ONE.divide(BigDecimal.TEN.pow(decimalType.getScale()), decimalType.getPrecision(), RoundingMode.HALF_UP);
        return value.toPlainString();
    }
}
