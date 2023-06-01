package data.modeling.adt;

import data.modeling.adt.adapters.protobuf3ToAdt.Protobuf3ToAdt;
import data.modeling.adt.util.ResourcesUtil;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws Exception {
        // Create a CharStream from your Proto file
        SchemaContext schemaContext = new SchemaContext();
        new Protobuf3ToAdt(schemaContext, readFromResources()).stream();
        System.out.println(schemaContext);

    }

    private static String readFromResources() throws IOException {
        return new ResourcesUtil(Main.class.getClassLoader()).asString("example.proto");
    }
}

