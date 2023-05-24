package adt.hiveddlToAdt.mappers;

import org.apache.spark.sql.types.BinaryType;

public class BinaryTypeMapper extends DataTypeMapper<BinaryType, data.modeling.adt.typedefs.BinaryType> {

    @Override
    public boolean canMap(BinaryType value) {
        return true;
    }

    @Override
    public data.modeling.adt.typedefs.BinaryType toAdt(BinaryType value) {
        return new data.modeling.adt.typedefs.BinaryType();
    }
}
