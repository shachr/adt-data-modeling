package adt.hiveddlToAdt.mappers;

import org.apache.spark.sql.types.DecimalType;

public class DecimalTypeMapper extends DataTypeMapper<DecimalType, data.modeling.adt.typedefs.DecimalType> {

    @Override
    public boolean canMap(DecimalType value) {
        return true;
    }

    @Override
    public data.modeling.adt.typedefs.DecimalType toAdt(DecimalType value) {
        return new data.modeling.adt.typedefs.DecimalType(value.precision(), value.scale());
    }
}
