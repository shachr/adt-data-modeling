package adt.hiveddlFromAdt.mappers;

import org.apache.spark.sql.types.DecimalType;

public class DecimalTypeMapper extends DataTypeMapper<data.modeling.adt.typedefs.DecimalType, DecimalType> {

    @Override
    public boolean canMap(data.modeling.adt.typedefs.DecimalType value) {
        return true;
    }

    @Override
    public DecimalType fromAdt(data.modeling.adt.typedefs.DecimalType value) {
        return new DecimalType(value.getPrecision(), value.getScale());
    }
}
