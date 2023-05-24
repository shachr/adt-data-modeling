package adt.hiveddlToAdt;

import adt.hiveddlToAdt.mappers.*;
import data.modeling.adt.abstraction.monads.NamedTypeStream;
import data.modeling.adt.exceptions.AdtException;
import data.modeling.adt.mappers.registries.ToAdtMapperRegistry;
import data.modeling.adt.typedefs.NamedType;
import org.apache.spark.sql.types.StructType;

import java.util.stream.Stream;

public class HiveDDLToAdt implements NamedTypeStream {
    private ToAdtMapperRegistry toAdtMapperRegistry = new ToAdtMapperRegistry();
    private StructType structType;
    private String name;

    public HiveDDLToAdt(String name, String ddl){
        this(name, StructType.fromDDL(ddl));
    }

    public HiveDDLToAdt(String name, StructType structType){
        this.name = name;
        this.structType = structType;
        toAdtMapperRegistry.register(new ArrayTypeMapper(toAdtMapperRegistry));
        toAdtMapperRegistry.register(new BinaryTypeMapper());
        toAdtMapperRegistry.register(new BooleanTypeMapper());
        toAdtMapperRegistry.register(new ByteTypeMapper());
        toAdtMapperRegistry.register(new CharTypeMapper());
        toAdtMapperRegistry.register(new VarcharTypeMapper());
        toAdtMapperRegistry.register(new StringTypeMapper());
        toAdtMapperRegistry.register(new DateTypeMapper());
        toAdtMapperRegistry.register(new DecimalTypeMapper());
        toAdtMapperRegistry.register(new DoubleTypeMapper());
        toAdtMapperRegistry.register(new FloatTypeMapper());
        toAdtMapperRegistry.register(new IntegerTypeMapper());
        toAdtMapperRegistry.register(new LongTypeMapper());
        toAdtMapperRegistry.register(new MapTypeMapper(toAdtMapperRegistry));
        toAdtMapperRegistry.register(new ShortTypeMapper());
        toAdtMapperRegistry.register(new StructTypeMapper(name, toAdtMapperRegistry));
        toAdtMapperRegistry.register(new StructFieldMapper(toAdtMapperRegistry));
        toAdtMapperRegistry.register(new TimestampNTZTypeMapper());
        toAdtMapperRegistry.register(new TimestampTypeMapper());
    }

    @Override
    public Stream<NamedType> stream() throws AdtException {
        return Stream.of((NamedType)toAdtMapperRegistry.toAdt(structType));
    }
}