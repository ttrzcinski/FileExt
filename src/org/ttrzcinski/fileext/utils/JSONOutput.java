package org.ttrzcinski.fileext.utils;

import org.json.JSONObject;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class JSONOutput {

    private Map<String, String> convertToHashMapStringString(final HashMap<String, Path> map) {
        Map<String,String> newMap = new HashMap<>();
        if (map != null && !map.isEmpty()) {
            newMap = map.entrySet().stream()
                    .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().toAbsolutePath().toString()));
        }
        return newMap;
    }

    public static String toJSON(final HashMap<String, Path> map) {
        return new JSONObject(map).toString();
    }
}
