package edu.project3.io.file;

import edu.project3.io.LogReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileLogsNginxReader implements LogReader {
    private final BufferedReader bufferedReader;

    public FileLogsNginxReader(Path path) throws IOException {
        if (Files.notExists(path)) {
            throw new FileNotFoundException("File not found");
        }

        bufferedReader = Files.newBufferedReader(path);
    }

    @Override
    public String readLine() throws IOException {
        return bufferedReader.readLine();
    }
}
