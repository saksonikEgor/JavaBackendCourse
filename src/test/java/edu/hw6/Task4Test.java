package edu.hw6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task4Test {
    private static final String PATHNAME = "src/test/resources/hw6/task4/file.txt";

    @DisplayName("Запись в файл")
    @Test
    void writeToTheFile() {
        try {
            deleteFileIfExists();
            Task4.writeToTheFile(Path.of(PATHNAME));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (BufferedReader br = new BufferedReader(new FileReader(PATHNAME))) {
            assertEquals(br.lines().collect(Collectors.joining()), Task4.TEXT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        assertThatThrownBy(() -> Task4.writeToTheFile(null))
            .isInstanceOf(NullPointerException.class)
            .hasMessageContaining(Task4.NULL_POINTER_EXCEPTION_MESSAGE);

        assertThatThrownBy(() -> Task4.writeToTheFile(Path.of(PATHNAME)))
            .isInstanceOf(FileAlreadyExistsException.class)
            .hasMessageContaining(Task4.FILE_EXIST_EXCEPTION_MESSAGE);
    }

    private static void deleteFileIfExists() throws IOException {
        Files.deleteIfExists(Path.of(PATHNAME));
    }
}
