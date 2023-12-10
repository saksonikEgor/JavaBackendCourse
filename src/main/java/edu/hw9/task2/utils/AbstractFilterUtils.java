package edu.hw9.task2.utils;

import edu.hw9.task2.filter.AbstractFilter;
import java.nio.file.Files;
import java.nio.file.Path;

public class AbstractFilterUtils {
    private AbstractFilterUtils() {
    }

    public static AbstractFilter sizeIs(long size) {
        return p -> Files.size(p) == size;
    }

    public static AbstractFilter extensionIs(String extension) {
        return p -> extension.equals(getFileExtension(p));
    }

    private static String getFileExtension(Path path) {
        String pathString = String.valueOf(path);
        int index = pathString.indexOf('.');

        return index == -1 ? "" : pathString.substring(index + 1);
    }
}
