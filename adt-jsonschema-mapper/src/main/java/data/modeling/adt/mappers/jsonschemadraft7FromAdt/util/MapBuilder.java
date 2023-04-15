package data.modeling.adt.mappers.jsonschemadraft7FromAdt.util;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class MapBuilder {
    private final Map<String, Object> map;

    public MapBuilder() {
        this.map = new LinkedHashMap<>();
    }

    public static MapBuilder create(){
        return new MapBuilder();
    }

    public MapBuilder put(String key, Object value) {
        this.map.put(key, value);
        return this;
    }

    public MapBuilder putNestedMap(String key) {
        Map<String, Object> nestedMap = new LinkedHashMap<>();
        this.map.put(key, nestedMap);
        return new MapBuilder(nestedMap);
    }

    public Map<String, Object> build() {
        return this.map;
    }

    private MapBuilder(Map<String, Object> map) {
        this.map = map;
    }

    public void merge(Map<String, Object> mapToMerge) {
        this.map.putAll(mapToMerge);
    }
}