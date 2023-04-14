package data.modeling.adt.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

public class JsonPathTraversingContext {
    private final String name;
    private final JsonPathTraversingContext parent;
    private final List<JsonPathTraversingContext> segments;

    public JsonPathTraversingContext() {
        this("", null);
    }
    public JsonPathTraversingContext(String name, JsonPathTraversingContext parent) {
        this.name = name;
        this.parent = parent;
        this.segments = new ArrayList<>();
    }

    public void addSegment(String segment, Consumer<JsonPathTraversingContext> function) {
        if(!segment.startsWith("/"))
            segment = "/" + segment;

        JsonPathTraversingContext child = new JsonPathTraversingContext(segment, this);
        this.segments.add(child);
        try {
            function.accept(child);
        } finally {
            this.segments.remove(child);
        }
    }
    public String getJsonPointer() {
        return getJsonPointer(null);
    }
    public String getJsonPointer(String lastSegment) {
        List<String> stringBuilder = new ArrayList<>();
        JsonPathTraversingContext current = this;
        while (current != null) {
            stringBuilder.add(current.name);
            current = current.parent;
        }
        Collections.reverse(stringBuilder);
        String jsonPointer = String.join("", stringBuilder);
        if(!jsonPointer.startsWith("/"))
            jsonPointer = "/" + jsonPointer;
        if(null != lastSegment){
            if(!jsonPointer.endsWith("/")){
                jsonPointer += "/" + lastSegment;
            }
        }
        return jsonPointer;
    }

    public JsonPathTraversingContext getParent() {
        return parent;
    }

    public List<JsonPathTraversingContext> getSegments() {
        return segments;
    }
}
