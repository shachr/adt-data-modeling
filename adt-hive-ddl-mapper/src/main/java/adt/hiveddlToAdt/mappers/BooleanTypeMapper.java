package adt.hiveddlToAdt.mappers;

import org.apache.spark.sql.types.BooleanType;

public class BooleanTypeMapper extends DataTypeMapper<BooleanType, data.modeling.adt.typedefs.BoolType> {

    @Override
    public boolean canMap(BooleanType value) {
        return true;
    }

    @Override
    public data.modeling.adt.typedefs.BoolType toAdt(BooleanType value) {
        return new data.modeling.adt.typedefs.BoolType();
    }
}
