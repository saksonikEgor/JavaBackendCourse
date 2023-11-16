package edu.hw6;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task2Test {
    private static final String DIR_PATH = "src/test/resources/hw6/task2";
    private static final String PATH_NAME_1 = "src/test/resources/hw6/task2/file1.txt";
    private static final String PATH_NAME_2 = "src/test/resources/hw6/task2/file2.txt";
    private static final String PATH_NAME_3 = "src/test/resources/hw6/task2/file3.txt";

    @DisplayName("Клонирование файла")
    @Test
    void cloneFile() {
        deleteFilesIfExists(List.of(
            "file1 - копия.txt",
            "file1 - копия (1).txt",
            "file1 - копия (2).txt",
            "file2 - копия.txt",
            "file2 - копия (1).txt",
            "file3 - копия.txt"
        ));

        Set<String> beforeClone = getFilesInDir();
        beforeClone.add("file1 - копия.txt");
        try {
            Task2.cloneFile(Path.of(PATH_NAME_1));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        assertEquals(beforeClone, getFilesInDir());

        beforeClone = getFilesInDir();
        beforeClone.add("file1 - копия (1).txt");
        try {
            Task2.cloneFile(Path.of(PATH_NAME_1));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        assertEquals(beforeClone, getFilesInDir());

        beforeClone = getFilesInDir();
        beforeClone.add("file1 - копия (2).txt");
        try {
            Task2.cloneFile(Path.of(PATH_NAME_1));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        assertEquals(beforeClone, getFilesInDir());

        beforeClone = getFilesInDir();
        beforeClone.add("file3 - копия.txt");
        try {
            Task2.cloneFile(Path.of(PATH_NAME_3));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        assertEquals(beforeClone, getFilesInDir());

        beforeClone = getFilesInDir();
        beforeClone.add("file2 - копия.txt");
        try {
            Task2.cloneFile(Path.of(PATH_NAME_2));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        assertEquals(beforeClone, getFilesInDir());

        beforeClone = getFilesInDir();
        beforeClone.add("file2 - копия (1).txt");
        try {
            Task2.cloneFile(Path.of(PATH_NAME_2));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        assertEquals(beforeClone, getFilesInDir());

        assertThatThrownBy(() -> Task2.cloneFile(null))
            .isInstanceOf(NullPointerException.class)
            .hasMessageContaining(Task2.NULL_POINTER_EXCEPTION_MESSAGE);

        assertThatThrownBy(() -> Task2.cloneFile(Path.of("wrong path")))
            .isInstanceOf(FileNotFoundException.class)
            .hasMessageContaining(Task2.FILE_NOT_FOUND_EXCEPTION_MESSAGE);
    }

    private static void deleteFilesIfExists(List<String> fileNames) {
        fileNames.forEach(name -> {
            try {
                Files.deleteIfExists(Path.of(DIR_PATH).resolve(name));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private static Set<String> getFilesInDir() {
        try {
            return Files
                .list(Path.of(DIR_PATH))
                .map(p -> p.getFileName().toString())
                .collect(Collectors.toSet());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
