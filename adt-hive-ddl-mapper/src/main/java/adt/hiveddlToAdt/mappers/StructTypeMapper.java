package adt.hiveddlToAdt.mappers;

import data.modeling.adt.exceptions.AdtException;
import data.modeling.adt.mappers.registries.ToAdtMapperRegistry;
import data.modeling.adt.typedefs.FieldDefinition;
import data.modeling.adt.typedefs.TypeDefinition;
import data.modeling.adt.util.LambdaExceptionUtil;
import org.apache.spark.sql.types.StructType;

import java.util.Arrays;

public class StructTypeMapper extends DataTypeMapper<StructType, TypeDefinition> {

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
    public TypeDefinition toAdt(StructType value) throws AdtException {
        return new TypeDefinition(name, data.modeling.adt.typedefs.ProductType.of(
                Arrays.stream(value.fields()).map(LambdaExceptionUtil.function(
                        field->(FieldDefinition)toAdtMapperRegistry.toAdt(field)))));
    }
}
