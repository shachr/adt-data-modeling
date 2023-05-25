package adt.hiveddlFromAdt;

import adt.hiveddlFromAdt.mappers.*;
import adt.hiveddlToAdt.HiveDDLToAdt;
import data.modeling.adt.SchemaContext;
import data.modeling.adt.abstraction.monads.SchemaTypeStream;
import data.modeling.adt.annotations.documentation.Identifier;
import data.modeling.adt.exceptions.AdtException;
import data.modeling.adt.mappers.registries.FromAdtMapperRegistry;
import data.modeling.adt.typedefs.AnyType;
import data.modeling.adt.typedefs.FieldType;
import data.modeling.adt.typedefs.NamedType;
import data.modeling.adt.typedefs.ProductType;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.IntegerType;
import org.apache.spark.sql.types.StringType;
import org.apache.spark.sql.types.StructType;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class HiveDDLFromAdt implements SchemaTypeStream<String> {
    private SchemaContext schemaContext;
    private NamedType namedType;

    private final FromAdtMapperRegistry fromAdtMapperRegistry = new FromAdtMapperRegistry();

    FromAdtMapperRegistry fromAdtMapperRegistry = new FromAdtMapperRegistry();

    public HiveDDLFromAdt(SchemaContext schemaContext, NamedType namedType) {

        this.schemaContext = schemaContext;
        this.namedType = namedType;

        fromAdtMapperRegistry.register(new ArrayTypeMapper(fromAdtMapperRegistry));
        fromAdtMapperRegistry.register(new BinaryTypeMapper());
        fromAdtMapperRegistry.register(new BooleanTypeMapper());
        fromAdtMapperRegistry.register(new ByteTypeMapper());
        fromAdtMapperRegistry.register(new CharTypeMapper());
        fromAdtMapperRegistry.register(new VarcharTypeMapper());
        fromAdtMapperRegistry.register(new StringTypeMapper());
        fromAdtMapperRegistry.register(new DateTypeMapper());
        fromAdtMapperRegistry.register(new DecimalTypeMapper());
        fromAdtMapperRegistry.register(new DoubleTypeMapper());
        fromAdtMapperRegistry.register(new FloatTypeMapper());
        fromAdtMapperRegistry.register(new IntegerTypeMapper());
        fromAdtMapperRegistry.register(new LongTypeMapper());
        fromAdtMapperRegistry.register(new MapTypeMapper(fromAdtMapperRegistry));
        fromAdtMapperRegistry.register(new ShortTypeMapper());
        fromAdtMapperRegistry.register(new StructTypeMapper(fromAdtMapperRegistry, schemaContext));
        fromAdtMapperRegistry.register(new StructFieldMapper(fromAdtMapperRegistry));
        fromAdtMapperRegistry.register(new TimestampNTZTypeMapper());
        fromAdtMapperRegistry.register(new TimestampTypeMapper());
    }

    // todo: add spark dependency
    //  use spark DDL parser to convert to spark schema and use it to transform to ADT
    //  use spark json parser to convert ADT to spark schema and use it to DDL properties
    //  with schema processing, can add the missing "create table <table> if not exists and location, etc"
    public static void main(String[] args) throws AdtException {
        /**
         * CREATE [EXTERNAL] TABLE [IF NOT EXISTS] [db_name.]table_name
         * (col_name data_type [COMMENT 'col_comment'],, ...)
         * [COMMENT 'table_comment']
         * [ROW FORMAT row_format]
         * [FIELDS TERMINATED BY char]
         * [STORED AS file_format];
         */

        /**
         * CREATE EXTERNAL TABLE deltaTable(col1 INT, col2 STRING)
         * STORED BY 'io.delta.hive.DeltaStorageHandler'
         * LOCATION '/delta/table/path'
         */

        NamedType invoice = NamedType.builder("invoice", ProductType.of(
                FieldType.builder("id", new data.modeling.adt.typedefs.StringType())
                        .withAnnotations(new Identifier()).build(),
                FieldType.builder("name", new data.modeling.adt.typedefs.StringType()).build(),
                FieldType.builder("age", new data.modeling.adt.typedefs.Int64Type()).build()
        )).build();

        SchemaContext schemaContext = new SchemaContext();
        schemaContext.registerNamedType(invoice);

        Stream<String> ddlStream = new HiveDDLFromAdt(schemaContext, invoice).stream();
        String ddlString = ddlStream.collect(Collectors.joining(",\n\t"));

        System.out.println("CREATE DATABASE IF NOT EXISTS my_database;");
        System.out.println("CREATE EXTERNAL TABLE IF NOT EXISTS my_table(");
        System.out.println("\t" + ddlString);
        System.out.println(")");
        System.out.println("COMMENT 'this is my table'");
        System.out.println("STORED BY 'io.delta.hive.DeltaStorageHandler'");
        System.out.println("LOCATION '/delta/table/path'");

        List<NamedType> namedTypeList = new HiveDDLToAdt("invoice", ddlString).stream().toList();
        System.out.println("--");
    }

    @Override
    public FromAdtMapperRegistry getMapperRegistry() {
        return fromAdtMapperRegistry;
    }

    @Override
    public Stream<String> stream() throws AdtException {

        StructType structType = fromAdtMapperRegistry.fromAdt(this.namedType);
        return Arrays.stream(structType.toDDL().split(","));

//        return Stream.concat(Arrays.stream(structType.toDDL().split(",")),
//                productType.getOwnFields()
//                        .stream()
//                        .filter(field->field.testAnnotation(Identifier.class))
//                        .map(FieldType::getName)
//                        .map("primary key(%s)"::formatted));
    }
}