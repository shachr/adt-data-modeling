package adt.hiveddlFromAdt.mappers;

import data.modeling.adt.SchemaContext;
import data.modeling.adt.exceptions.AdtException;
import data.modeling.adt.mappers.registries.FromAdtMapperRegistry;
import data.modeling.adt.typedefs.TypeDefinition;
import data.modeling.adt.typedefs.ProductType;
import data.modeling.adt.util.LambdaExceptionUtil;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;

public class StructTypeMapper extends DataTypeMapper<TypeDefinition, StructType> {

    private FromAdtMapperRegistry fromAdtMapperRegistry;
    private SchemaContext schemaContext;

    public StructTypeMapper(FromAdtMapperRegistry fromAdtMapperRegistry, SchemaContext schemaContext){

        this.fromAdtMapperRegistry = fromAdtMapperRegistry;
        this.schemaContext = schemaContext;
    }

    @Override
    public boolean canMap(TypeDefinition value) {
        return true;
    }

    @Override
    public StructType fromAdt(TypeDefinition value) throws AdtException {
        if(!(value.getType() instanceof ProductType)){
            throw new AdtException("not implemented");
        }

        StructField[] structFields = ((ProductType)value.getType()).resolveAllFields(schemaContext)
                .stream()
                .map(LambdaExceptionUtil.function(fromAdtMapperRegistry::fromAdt))
                .map(dataType-> (StructField)dataType)
                .toArray(StructField[]::new);

        // todo: how to set the type name???
        StructType structType = new StructType(structFields);
        return structType;
    }
}
