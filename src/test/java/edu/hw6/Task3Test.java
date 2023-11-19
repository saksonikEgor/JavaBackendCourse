package edu.hw6;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw6.task3.AbstractFiltersUtil.globMatches;
import static edu.hw6.task3.AbstractFiltersUtil.largerThan;
import static edu.hw6.task3.AbstractFiltersUtil.lessThan;
import static edu.hw6.task3.AbstractFiltersUtil.magicNumber;
import static edu.hw6.task3.AbstractFiltersUtil.readable;
import static edu.hw6.task3.AbstractFiltersUtil.regexContains;
import static edu.hw6.task3.AbstractFiltersUtil.regularFile;
import static edu.hw6.task3.AbstractFiltersUtil.writable;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task3Test {
    private static final String PATHNAME = "src/test/resources/hw6/task3";

    private static Set<String> directoryStreamToPathSet(DirectoryStream<Path> ds) {
        return StreamSupport
            .stream(ds.spliterator(), false)
            .map(Path::toString)
            .collect(Collectors.toSet());
    }

    @DisplayName("Фильтрация файлов")
    @Test
    void filterFiles() {
        DirectoryStream.Filter<Path> filter1 = regularFile
            .and(readable)
            .and(largerThan(100_000))
            .and(magicNumber(0x89, 'P', 'N', 'G'))
            .and(globMatches("*.png"))
            .and(regexContains("[-]"));
        assertDoesNotThrow(() -> {
            try (DirectoryStream<Path> entries = Files.newDirectoryStream(Path.of(PATHNAME), filter1)) {
                entries.forEach(System.out::println);
            }
        });

        DirectoryStream.Filter<Path> filter2 = regularFile
            .and(writable)
            .and(lessThan(100_000))
            .and(globMatches("*.txt"));
        try (DirectoryStream<Path> entries = Files.newDirectoryStream(Path.of(PATHNAME), filter2)) {
            assertEquals(
                directoryStreamToPathSet(Files.newDirectoryStream(Path.of(PATHNAME))),
                directoryStreamToPathSet(entries)
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
