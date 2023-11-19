package edu.hw6;

import edu.hw6.task1.DiskMap;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task1Test {
    private static final String PATHNAME = "src/test/resources/hw6/task1/storage";

    private static void clearFile() throws IOException {
        new FileWriter(PATHNAME, false).close();
    }

    @DisplayName("Изменение содержимого хранилища")
    @Test
    void modificationStorage() {
        try {
            clearFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        DiskMap mapDiskMap = new DiskMap(new HashMap<>(), Path.of(PATHNAME));

        mapDiskMap.put("firstKey", "firstVal");
        mapDiskMap.put("secondKey", "secondVal");
        assertEquals(
            Map.of(
                "firstKey", "firstVal",
                "secondKey", "secondVal"
            ), mapDiskMap
        );

        mapDiskMap.put("firstKey", "anotherVal");
        assertEquals(
            Map.of(
                "firstKey", "anotherVal",
                "secondKey", "secondVal"
            ), mapDiskMap
        );

        mapDiskMap.putAll(Map.of(
            "secondKey", "secondVal",
            "k2", "v2",
            "k3", "v3"
        ));
        assertEquals(
            Map.of(
                "firstKey", "anotherVal",
                "secondKey", "secondVal",
                "k2", "v2",
                "k3", "v3"
            ), mapDiskMap
        );

        mapDiskMap.remove("firstKey");
        assertEquals(
            Map.of(
                "secondKey", "secondVal",
                "k2", "v2",
                "k3", "v3"
            ), mapDiskMap
        );

        mapDiskMap = new DiskMap(new HashMap<>(), Path.of(PATHNAME));
        assertEquals(
            Map.of(
                "secondKey", "secondVal",
                "k2", "v2",
                "k3", "v3"
            ), mapDiskMap
        );
        mapDiskMap.clear();

        assertTrue(new DiskMap(new HashMap<>(), Path.of(PATHNAME)).isEmpty());
    }
}
