package adt.hiveddlFromAdt.mappers;

import data.modeling.adt.typedefs.BoolType;
import org.apache.spark.sql.types.BooleanType;

public class BooleanTypeMapper extends DataTypeMapper<BoolType, BooleanType> {

    @Override
    public boolean canMap(BoolType value) {
        return true;
    }

    @Override
    public BooleanType fromAdt(BoolType value) {
        return new BooleanType();
    }
}
