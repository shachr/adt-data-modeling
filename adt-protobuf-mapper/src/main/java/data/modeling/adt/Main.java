package data.modeling.adt;

import data.modeling.adt.util.ResourcesUtil;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

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

