package data.modeling.adt.util;

import java.util.stream.Stream;

public class StreamUtil {
    public static <T> Stream<T> concatStreams(Stream<T>... streams) {
        Stream<T> concatenatedStream = Stream.empty();

        for (Stream<T> stream : streams) {
            concatenatedStream = Stream.concat(concatenatedStream, stream);
        }

        return concatenatedStream;
    }
}
