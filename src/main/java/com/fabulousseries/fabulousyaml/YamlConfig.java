package com.fabulousseries.fabulousyaml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class YamlConfig implements IYaml {
    protected Map<String, Object> config;
    public YamlConfig(Map<String, Object> config) {
        this.config = config;
    }

    private <T> T getValue(String dottedKey, Class<T> type) {
        String[] keys = dottedKey.split("\\.");
        Object value = config;
        for (String key : keys) {
            value = ((Map<String, Object>) value).get(key);
            if (value == null) {
                return null;
            }
        }
        return type.cast(value);
    }

    private void setValue(String dottedKey, Object newValue) {
        String[] keys = dottedKey.split("\\.");
        Map<String, Object> map = config;

        for (int i = 0; i < keys.length - 1; i++) {
            map = (Map<String, Object>) map.computeIfAbsent(keys[i], k -> new HashMap<>());
        }

        map.put(keys[keys.length - 1], newValue);
    }

    private void getDynamicKeysRecursive(Map<String, Object> map, List<String> keys) {
        for (String key : map.keySet()) {
            keys.add(key);
            Object value = map.get(key);
            if (value instanceof Map) {
                getDynamicKeysRecursive((Map<String, Object>) value, keys);
            }
        }
    }

    @Override
    public List<String> getKeys(String parentKey) {
        Object value = getValue(parentKey, Map.class);
        List<String> keys = new ArrayList<>();
        if (value instanceof Map) {
            getDynamicKeysRecursive((Map<String, Object>) value, keys);
        }
        return keys;
    }
    @Override
    public int getInt(String value) {
        return getValue(value, Integer.class);
    }

    @Override
    public boolean getBoolean(String value) {
        return getValue(value, Boolean.class);
    }

    @Override
    public String getString(String value) {
        return getValue(value, String.class);
    }

    @Override
    public ArrayList getList(String value) {
        return getValue(value, ArrayList.class);
    }

    @Override
    public void setInt(String key, int value) {
        setValue(key, value);
    }

    @Override
    public void setBoolean(String key, boolean value) {
        setValue(key, value);
    }

    @Override
    public void setString(String key, String value) {
        setValue(key, value);
    }

    @Override
    public void setList(String key, ArrayList value) {
        setValue(key, value);
    }


}