package edu.hw3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Task3 {
    public static final String NULL_POINTER_EXCEPTION_MESSAGE = "List \"list\" cant be null";

    private Task3() {
    }

    public static <T> Map<T, Integer> freqDict(List<T> list) {
        if (list == null) {
            throw new NullPointerException(NULL_POINTER_EXCEPTION_MESSAGE);
        }

        Map<T, Integer> freqDict = new HashMap<>();
        list.forEach(el -> freqDict.put(el, freqDict.getOrDefault(el, 0) + 1));
        return freqDict;
    }
}
