package data.modeling.adt.util;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamExtensions {
    public static <T> Map<String, T> toMap(Stream<Map.Entry<String, T>> stream) {
        return stream.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
