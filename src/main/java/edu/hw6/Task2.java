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
    private static final char EXTENSION_DELIMITER_CHARACTER = '.';
    private static final String PATTERN = "(FILENAME)( - копия( \\(\\d+\\))?)?EXTENSION";

    private Task2() {
    }

    public static void cloneFile(Path path) throws IOException {
        if (path == null) {
            throw new NullPointerException(NULL_POINTER_EXCEPTION_MESSAGE);
        }
        if (Files.notExists(path)) {
            throw new FileNotFoundException(FILE_NOT_FOUND_EXCEPTION_MESSAGE);
        }

        String fileFullName = path.getFileName().toString();
        int extensionCharIdx = fileFullName.indexOf(EXTENSION_DELIMITER_CHARACTER);
        String fileName = fileFullName.substring(0, extensionCharIdx);
        String fileExtension = fileFullName.substring(extensionCharIdx);

        String cloneName;
        int fileId = 1;
        if (!fileName.contains(CLONE_SUFFIX)) {
            cloneName = fileName + CLONE_SUFFIX;
        } else {
            cloneName = fileName;
            fileName = fileName.substring(0, fileName.indexOf(CLONE_SUFFIX));
        }

        String pattern = PATTERN.replaceAll("FILENAME", fileName)
            .replaceAll("EXTENSION", fileExtension);

        try (Stream<Path> paths = Files.list(path.getParent())) {

            Set<String> fileNames = paths
                .map(p -> p.getFileName().toString())
                .filter(name -> name.matches(pattern))
                .map(name -> name.substring(0, name.indexOf(EXTENSION_DELIMITER_CHARACTER)))
                .collect(Collectors.toSet());

            while (fileNames.contains(cloneName)) {
                cloneName = concatFileNameAndId(fileName, fileId);
                fileId++;
            }

            Files.createFile(path.resolveSibling(cloneName + fileExtension));
        }
    }

    private static String concatFileNameAndId(String fileName, int fileId) {
        return fileName + CLONE_SUFFIX + " (" + fileId + ")";
    }
}
