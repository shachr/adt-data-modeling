package adt.hiveddlFromAdt.mappers;

import org.apache.spark.sql.types.BinaryType;

public class BinaryTypeMapper extends DataTypeMapper<data.modeling.adt.typedefs.BinaryType, BinaryType> {

    @Override
    public boolean canMap(data.modeling.adt.typedefs.BinaryType value) {
        return true;
    }

    @Override
    public BinaryType fromAdt(data.modeling.adt.typedefs.BinaryType value) {
        return new BinaryType();
    }
}
