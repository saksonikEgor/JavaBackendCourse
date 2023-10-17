package edu.hw3;

import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class Task7Test {
    @Test
    @DisplayName("Получение TreeMap")
    void getTreeMap() {
        Map<String, String> treeMap = Task7.getTreeMap();
        treeMap.put("k1", "v1");

        assertDoesNotThrow(() -> treeMap.put(null, "v2"));

        treeMap.put("k3", "v3");

        assertThat(treeMap.keySet()).containsOnly("k1", null, "k3");
        assertThat(treeMap.values()).containsOnly("v1", "v2", "v3");
    }
}
