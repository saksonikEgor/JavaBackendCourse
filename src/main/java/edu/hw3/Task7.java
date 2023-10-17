package edu.hw3;

import java.util.Map;
import java.util.TreeMap;

public class Task7 {
    private Task7() {
    }

    public static Map<String, String> getTreeMap() {
        return new TreeMap<>((o1, o2) -> {
            String first = o1;
            String second = o2;

            if (first == null) {
                first = "";
            }
            if (second == null) {
                second = "";
            }
            return String.CASE_INSENSITIVE_ORDER.compare(first, second);
        });
    }
}
