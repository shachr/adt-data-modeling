package adt.hiveddlToAdt.mappers;

import data.modeling.adt.exceptions.AdtException;
import data.modeling.adt.mappers.registries.ToAdtMapperRegistry;
import data.modeling.adt.typedefs.FieldType;
import data.modeling.adt.typedefs.NamedType;
import data.modeling.adt.util.LambdaExceptionUtil;
import org.apache.spark.sql.types.StructType;

import java.util.Arrays;

public class StructTypeMapper extends DataTypeMapper<StructType, data.modeling.adt.typedefs.NamedType> {

    private String name;
    private ToAdtMapperRegistry toAdtMapperRegistry;

    public StructTypeMapper(String name, ToAdtMapperRegistry toAdtMapperRegistry){
        this.name = name;

        this.toAdtMapperRegistry = toAdtMapperRegistry;
    }

    @Override
    public boolean canMap(StructType value) {
        return true;
    }

    @Override
    public data.modeling.adt.typedefs.NamedType toAdt(StructType value) throws AdtException {
        return new NamedType(name, data.modeling.adt.typedefs.ProductType.of(
                Arrays.stream(value.fields()).map(LambdaExceptionUtil.function(
                        field->(FieldType)toAdtMapperRegistry.toAdt(field)))));
    }
}
