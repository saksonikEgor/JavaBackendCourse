package edu.hw6;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.Adler32;
import java.util.zip.CheckedOutputStream;

public class Task4 {
    public static final String NULL_POINTER_EXCEPTION_MESSAGE = "Path cant be null";
    public static final String FILE_EXIST_EXCEPTION_MESSAGE = "The file must not exist";
    public static final String TEXT = "Programming is learned by writing programs. ― Brian Kernighan";

    private Task4() {
    }

    public static void writeToTheFile(Path path) throws IOException {
        if (path == null) {
            throw new NullPointerException(NULL_POINTER_EXCEPTION_MESSAGE);
        }
        if (Files.exists(path)) {
            throw new FileAlreadyExistsException(FILE_EXIST_EXCEPTION_MESSAGE);
        }

        try (
            PrintWriter pw = new PrintWriter(
                new OutputStreamWriter(
                    new BufferedOutputStream(
                        new CheckedOutputStream(
                            Files.newOutputStream(
                                Files.createFile(path)
                            ), new Adler32()
                        )
                    ), StandardCharsets.UTF_8
                )
            )
        ) {
            pw.write(TEXT);
        }
    }
}
