package adt;

import com.fasterxml.jackson.databind.ObjectMapper;
import data.modeling.adt.SchemaContext;
import data.modeling.adt.exceptions.AdtException;
import data.modeling.adt.mappers.graphqlToAdt.GraphQLToAdt;
import data.modeling.adt.mappers.jsonschemadraft7FromAdt.JsonSchemaDraft7FromAdt;
import data.modeling.adt.util.LambdaExceptionUtil;
import data.modeling.adt.util.ResourcesUtil;
import data.modeling.adt.util.SchemaContextUtil;

import java.io.IOException;
import java.util.stream.Collectors;

import static data.modeling.adt.util.StreamExtensions.toMap;

public class Main {
    public static void main(String[] args) throws IOException, AdtException {
        System.out.println("Hello world!");

        String commonGql = new ResourcesUtil(Main.class.getClassLoader()).asString("common.gql");
        SchemaContext schemaContext = new SchemaContext();
        SchemaContextUtil schemaContextUtil = new SchemaContextUtil(schemaContext);
        GraphQLToAdt graphQlToAdt = new GraphQLToAdt(commonGql, schemaContext);

        graphQlToAdt.stream().toList();
        schemaContextUtil.rename("^(.*)$", "com.foo.bar.$1");

        schemaContext.stream().forEach(LambdaExceptionUtil.consumer(namedType -> {
            System.out.println(namedType.getName());
            JsonSchemaDraft7FromAdt jsonSchemaDraft7FromAdt = new JsonSchemaDraft7FromAdt(namedType, schemaContext);
            jsonSchemaDraft7FromAdt.stream().forEach(LambdaExceptionUtil.consumer(jsonSchemaMap -> {
                ObjectMapper objectMapper = new ObjectMapper();
                System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonSchemaMap));
            }));
            System.out.println("----");
        }));





    }
}