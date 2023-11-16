package edu.hw6;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Task2 {
    public static final String NULL_POINTER_EXCEPTION_MESSAGE = "Path cant be null";
    public static final String FILE_NOT_FOUND_EXCEPTION_MESSAGE = "File with path \"{}\" does not exist";
    private static final String CLONE_SUFFIX = " - копия";
    private static final char EXTENSION_CHARACTER = '.';

    private Task2() {
    }

    public static void cloneFile(Path path) throws IOException {
        if (path == null) {
            throw new NullPointerException(NULL_POINTER_EXCEPTION_MESSAGE);
        }
        if (!Files.exists(path)) {
            throw new FileNotFoundException(FILE_NOT_FOUND_EXCEPTION_MESSAGE);
        }

        try (Stream<Path> paths = Files.list(path.getParent())) {
            Set<String> fileNames = paths
                .map(p -> p.getFileName().toString())
                .map(name -> name.substring(0, name.indexOf(EXTENSION_CHARACTER)))
                .collect(Collectors.toSet());

            String fileFullName = path.getFileName().toString();
            int extensionCharIdx = fileFullName.indexOf(EXTENSION_CHARACTER);
            String fileName = fileFullName.substring(0, extensionCharIdx);
            String fileExtension = fileFullName.substring(extensionCharIdx);

            String cloneName = fileName + CLONE_SUFFIX;
            int fileId = 1;
            while (fileNames.contains(cloneName)) {
                cloneName = concatFileNameAndId(fileName, fileId);
                fileId++;
            }

            Files.createFile(path.resolveSibling(cloneName + fileExtension));
        }
    }

    private static String concatFileNameAndId(String fileName, int fileId) {
        return fileName
            .concat(CLONE_SUFFIX)
            .concat(" (")
            .concat(String.valueOf(fileId))
            .concat(")");
    }
}
