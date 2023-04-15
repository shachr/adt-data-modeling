package data.modeling.adt.util;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamExtensions {
    public static Map<String, Object> toMap(Stream<Map.Entry<String, Object>> stream) {
        return stream.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
