package adt.hiveddlFromAdt.mappers;

import org.apache.spark.sql.types.DoubleType;

public class DoubleTypeMapper extends DataTypeMapper<data.modeling.adt.typedefs.DoubleType, DoubleType> {

    @Override
    public boolean canMap(data.modeling.adt.typedefs.DoubleType value) {
        return true;
    }

    @Override
    public DoubleType fromAdt(data.modeling.adt.typedefs.DoubleType value) {
        return new DoubleType();
    }
}
