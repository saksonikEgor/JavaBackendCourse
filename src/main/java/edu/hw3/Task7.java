package edu.hw3;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class Task7 {
    private Task7() {
    }

    public static Map<String, String> getTreeMap() {
        return new TreeMap<>(Comparator.nullsFirst(String.CASE_INSENSITIVE_ORDER));
    }
}
