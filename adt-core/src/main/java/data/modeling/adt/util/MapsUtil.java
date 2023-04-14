package data.modeling.adt.util;

import java.util.*;

public class MapsUtil {

    public static Map deepMergeMaps(List<Map> maps) {
        Map mergedMap = new LinkedHashMap();
        for (Map map : maps) {
            for (Object obj : map.entrySet()) {
                Map.Entry entry = (Map.Entry)obj;
                Object key = entry.getKey();
                Object value = entry.getValue();
                if (mergedMap.containsKey(key)) {
                    Object existingValue = mergedMap.get(key);
                    if (existingValue instanceof Map && value instanceof Map) {
                        Map deepMergedMap = deepMergeMaps(
                                Arrays.asList((Map)existingValue, (Map)value));
                        mergedMap.put(key, deepMergedMap);
                    } else if (existingValue instanceof List && value instanceof List) {
                        List mergedList = new ArrayList((List) existingValue);
                        mergedList.addAll((List) value);
                        mergedMap.put(key, mergedList);
                    } else {
                        mergedMap.put(key, value);
                    }
                } else {
                    mergedMap.put(key, value);
                }
            }
        }

        return mergedMap;
    }
}
