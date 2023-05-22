package adt;

import data.modeling.adt.SchemaContext;
import data.modeling.adt.abstraction.monads.SchemaTypeStream;
import data.modeling.adt.annotations.documentation.Identifier;
import data.modeling.adt.exceptions.AdtException;
import data.modeling.adt.typedefs.*;
import data.modeling.adt.util.LambdaExceptionUtil;
import org.apache.spark.sql.types.*;
import org.apache.spark.sql.types.StringType;

import java.util.Arrays;
import java.util.function.IntFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class HiveDDLFromAdt implements SchemaTypeStream<String> {
    private SchemaContext schemaContext;
    private NamedType namedType;

    public HiveDDLFromAdt(SchemaContext schemaContext, NamedType namedType) {

        this.schemaContext = schemaContext;
        this.namedType = namedType;
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
                FieldType.builder("age", new data.modeling.adt.typedefs.Int32Type()).build()
        )).build();

        SchemaContext schemaContext = new SchemaContext();
        schemaContext.registerNamedType(invoice);

        System.out.println("CREATE DATABASE IF NOT EXISTS my_database;");
        System.out.println("CREATE EXTERNAL TABLE IF NOT EXISTS my_table(");
        System.out.println("\t" + new HiveDDLFromAdt(schemaContext, invoice).stream().collect(Collectors.joining(",\n\t")));
        System.out.println(")");
        System.out.println("COMMENT 'this is my table'");
        System.out.println("STORED BY 'io.delta.hive.DeltaStorageHandler'");
        System.out.println("LOCATION '/delta/table/path'");
    }


    @Override
    public Stream<String> stream() {
        String name = this.namedType.getName();
        ProductType productType = ((ProductType)this.namedType.getType());
        StructField[] structFields = productType.getOwnFields().stream()
                .map(LambdaExceptionUtil.function(fieldType ->
                        new StructField(fieldType.getName(), toSparkType(fieldType.getType()), fieldType.getType() instanceof NullValueType, Metadata.empty())))
                .toArray(StructField[]::new);

        StructType structType = new StructType(structFields);

        return Stream.concat(Arrays.stream(structType.toDDL().split(",")),
                productType.getOwnFields()
                        .stream()
                        .filter(field->field.testAnnotation(Identifier.class))
                        .map(FieldType::getName)
                        .map("primary key(%s)"::formatted));
    }

    private DataType toSparkType(AnyType type) throws AdtException {
        if(type instanceof data.modeling.adt.typedefs.StringType)
            return new StringType();
        if(type instanceof data.modeling.adt.typedefs.Int32Type)
            return new IntegerType();
        throw new AdtException("not implemented");
    }
}