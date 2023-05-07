package data.modeling.adt.typedefs;

import java.math.BigDecimal;

public class StringType extends PrimitiveType {
    public static ConstantPrimitiveType constantOf(String value){
        return new ConstantPrimitiveType(new StringType(), value);
    }

    @Override
    public boolean isValueOf(Object value) {
        return value instanceof String;
    }
}
