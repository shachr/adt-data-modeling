package data.modeling.adt.mappers;

import static org.junit.Assert.*;

import java.util.*;

public class MapAssert {

    public static <K,V>  void assertMapsEqual(Map<K,V> expected, Map<K,V>  actual) {
        assertMapsEqual("", expected, actual);
    }

    public static <K,V> void assertMapsEqualIgnoreOrder(Map<K, V> expected, Map<K,V> actual) {
        Map sorted = sortMap(actual);
        assertMapsEqual("", sortMap(expected), sortMap(actual));
    }

    private static <K,V> void assertMapsEqual(String path, Map<K,V>  expected, Map<K,V>  actual) {
        assertEquals(path + "size is different", expected.keySet(), actual.keySet());
        for (Map.Entry<K,V>  expectedEntry : expected.entrySet()) {
            K key = expectedEntry.getKey();
            V expectedValue = expectedEntry.getValue();
            V actualValue = actual.get(key);
            String entryPath = path + "key " + key + " : ";
            if (expectedValue instanceof Map && actualValue instanceof Map) {
                assertMapsEqual(entryPath, (Map<K,V>) expectedValue, (Map<K,V>) actualValue);
            } else {
                assertEquals(entryPath + "value is different", expectedValue, actualValue);
            }
        }
    }

    private static <K,V>  Map<K,V>  sortMap(Map<K,V>  map) {
        Map<K,V> sortedMap = new TreeMap<>();
        for (Map.Entry<K,V> entry : map.entrySet()) {
            K key = entry.getKey();
            V value = entry.getValue();
            if (value instanceof Map) {
                value = (V)sortMap((Map<K,V>) value);
            } else if(value instanceof List){
                sortList(((List)value));
            }
            sortedMap.put(key, value);
        }
        return sortedMap;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private static void sortList(List<?> list) {
        if (list.isEmpty()) {
            return;
        }
        Object firstElement = list.get(0);
        if (firstElement instanceof Map) {
            List<Map<?, ?>> casted = (List) list;
            Comparator<Map<?, ?>> comparator = (o1, o2) -> {
                if (o1 instanceof Comparable && o2 instanceof Comparable) {
                    return ((Comparable) o1).compareTo(o2);
                } else {
                    return Objects.hashCode(o1) - Objects.hashCode(o2);
                }
            };
            casted.sort(comparator);
            for (int i = 0; i < casted.size(); i++) {
                Map<?, ?> sortedMap = sortMap(casted.get(i));
                casted.set(i, sortedMap);
            }
        } else if (firstElement instanceof Comparable) {
            Collections.sort((List<Comparable>) list);
        }
    }
}

