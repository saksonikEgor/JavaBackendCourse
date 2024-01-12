package edu.hw9;

import edu.hw9.task2.searching.CountingSearchTask;
import edu.hw9.task2.searching.PredicateSearchTask;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ForkJoinPool;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task2Test {
    private static final Path path = Path.of("src/test/resources/hw9/task2");
    private static final Path nestedPath1 = Path.of("src/test/resources/hw9/task2/nested1");
    private static final Path nestedPath2 = Path.of("src/test/resources/hw9/task2/nested1/nested2");

    @Test
    @DisplayName("Не нахождение директорий с количеством файлов больше 1000")
    void countSearching() throws IOException {
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();

        final int fileCount = 900;
        for (int i = 1; i <= fileCount; i++) {
            Files.createFile(path.resolve(i + ".txt"));
        }

        CountingSearchTask task = new CountingSearchTask(path);
        forkJoinPool.invoke(task);

        assertTrue(task.getDirectories().isEmpty());

    }

    @Test
    @DisplayName("Нахождение директорий с количеством файлов больше 1000")
    void nestedCountSearching() throws IOException {
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();

        final int fileCount = 1200;
        for (int i = 1; i <= fileCount; i++) {
            Files.createFile(nestedPath1.resolve(i + ".txt"));
            Files.createFile(nestedPath2.resolve(i + ".txt"));
        }

        CountingSearchTask task = new CountingSearchTask(path);
        forkJoinPool.invoke(task);

        assertThat(task.getDirectories())
            .containsAnyElementsOf(List.of(path, nestedPath1, nestedPath2));
    }

    @Test
    @DisplayName("Поиск по предикату")
    void predicateSearching() throws IOException {
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();

        final int searchingSize = 200;
        String searchingExtension = "txt";

        Path file1 = Files.createFile(nestedPath2.resolve("findMe.txt"));
        Path file2 = Files.createFile(nestedPath1.resolve("findMe.txt"));
        Path file3 = Files.createFile(path.resolve("findMe.txt"));
        Path file4 = Files.createFile(path.resolve("dontFineMe.txt"));
        Path file5 = Files.createFile(path.resolve("dontFineMe.csv"));

        Files.write(file1, new byte[searchingSize]);
        Files.write(file2, new byte[searchingSize]);
        Files.write(file3, new byte[searchingSize]);
        Files.write(file4, new byte[searchingSize + 100]);
        Files.write(file5, new byte[searchingSize]);

        PredicateSearchTask task = new PredicateSearchTask(path, searchingExtension, searchingSize);

        assertThat(forkJoinPool.invoke(task)).containsExactlyInAnyOrderElementsOf(List.of(
            file1, file2, file3
        ));
    }

    @AfterEach
    void clearDirectories() {
        for (File myFile : Objects.requireNonNull(path.toFile().listFiles())) {
            if (myFile.isFile()) {
                myFile.delete();
            }
        }

        for (File myFile : Objects.requireNonNull(nestedPath1.toFile().listFiles())) {
            if (myFile.isFile()) {
                myFile.delete();
            }
        }

        for (File myFile : Objects.requireNonNull(nestedPath2.toFile().listFiles())) {
            if (myFile.isFile()) {
                myFile.delete();
            }
        }
    }
}
