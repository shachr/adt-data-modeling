package adt.hiveddlToAdt.mappers;

import org.apache.spark.sql.types.ByteType;

public class ByteTypeMapper extends DataTypeMapper<ByteType, data.modeling.adt.typedefs.ByteType> {

    @Override
    public boolean canMap(ByteType value) {
        return true;
    }

    @Override
    public data.modeling.adt.typedefs.ByteType toAdt(ByteType value) {
        return new data.modeling.adt.typedefs.ByteType();
    }
}
