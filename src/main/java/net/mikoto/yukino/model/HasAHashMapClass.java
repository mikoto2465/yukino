package net.mikoto.yukino.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author mikoto
 * &#064;date 2022/12/11
 * Create for yukino
 */
public abstract class HasAHashMapClass<T> {

    protected final Map<String, T> dataMap = new HashMap<>();

    public T get(String name) {
        return dataMap.get(name);
    }

    public void put(String name, T object) {
        dataMap.put(name, object);
    }

    public void remove(String name) {
        dataMap.remove(name);
    }

    public Set<Map.Entry<String,T>> getAll() {
        return dataMap.entrySet();
    }

    public Collection<T> getAllValues() {
        return dataMap.values();
    }

    public Set<String> getAllKeys() {
        return dataMap.keySet();
    }

    public int size() {
        return dataMap.size();
    }

    public boolean isEmpty() {
        return dataMap.isEmpty();
    }
}
