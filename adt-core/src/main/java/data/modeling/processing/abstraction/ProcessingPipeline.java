package data.modeling.processing.abstraction;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

public abstract class ProcessingPipeline<T> {
    private List<Filter<T>> filters = new LinkedList<>();


    public ProcessingPipeline() {}

    public void registerFilter(Filter<T> filter){
        filters.add(filter);
    }

    public void execute(Stream<T> messages){
        messages.forEach(msg -> filters.forEach((filter)-> filter.executeTask(msg)));
    }
}
