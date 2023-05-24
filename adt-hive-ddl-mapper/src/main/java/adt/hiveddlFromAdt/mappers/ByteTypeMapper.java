package adt.hiveddlFromAdt.mappers;

import org.apache.spark.sql.types.ByteType;

public class ByteTypeMapper extends DataTypeMapper<data.modeling.adt.typedefs.ByteType, ByteType> {

    @Override
    public boolean canMap(data.modeling.adt.typedefs.ByteType value) {
        return true;
    }

    @Override
    public ByteType fromAdt(data.modeling.adt.typedefs.ByteType value) {
        return new ByteType();
    }
}
