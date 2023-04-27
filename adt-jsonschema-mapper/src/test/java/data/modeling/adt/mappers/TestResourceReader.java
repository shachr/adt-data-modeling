package data.modeling.adt.mappers;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

public class TestResourceReader {

    public Map<String, Object> readJsonSchema(String fileName) throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("data.modeling.adt.mappers/json-schema/" + fileName);

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder jsonStringBuilder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            jsonStringBuilder.append(line);
        }

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(jsonStringBuilder.toString(), Map.class);
    }
}
