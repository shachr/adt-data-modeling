package data.modeling.adt.abstraction.monads;

import java.util.stream.Stream;

public interface SchemaTypeStream<T> {

    Stream<T> stream();
}
