package data.modeling.adt.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class ResourcesUtil {
    private ClassLoader classLoader;

    public ResourcesUtil(ClassLoader classLoader){

        this.classLoader = classLoader;
    }
    public InputStream asInputStream(String path) throws IOException {
        return classLoader.getResourceAsStream(path);
    }

    public String asString(String path) throws IOException {
        try(InputStream stream = asInputStream(path)){
            assert stream != null;
            return new String(stream.readAllBytes(), StandardCharsets.UTF_8);
        }
    }
}
