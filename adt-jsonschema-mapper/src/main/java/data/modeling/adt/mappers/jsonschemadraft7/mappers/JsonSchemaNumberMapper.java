package data.modeling.adt.mappers.jsonschemadraft7.mappers;

import data.modeling.adt.abstraction.typedefs.DecimalType;
import data.modeling.adt.abstraction.typedefs.DoubleType;
import data.modeling.adt.abstraction.typedefs.NumericType;

import java.math.BigDecimal;
import java.util.Map;

public class JsonSchemaNumberMapper extends JsonSchemaMapper<NumericType> {
    @Override
    public boolean canMap(Map<String, Object> value) {
        return value.containsKey("type") && value.get("type").equals("number");
    }

    @Override
    public NumericType toAdt(Map<String, Object> value) {
        if(value.containsKey("multipleOf")){
            BigDecimal bigDecimal = new BigDecimal(value.remove("multipleOf").toString());
            return new DecimalType(bigDecimal.precision(), bigDecimal.scale());
        }
        value.remove("type");
        return new DoubleType();
    }
}
