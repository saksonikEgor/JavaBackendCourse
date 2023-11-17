package edu.hw6.task3;

import java.io.FileInputStream;
import java.nio.file.Files;
import java.util.regex.Pattern;

public class AbstractFiltersUtil {
    public static AbstractFilter regularFile = Files::isRegularFile;
    public static AbstractFilter readable = Files::isReadable;
    public static AbstractFilter writable = Files::isWritable;

    private AbstractFiltersUtil() {
    }

    public static AbstractFilter largerThan(long size) {
        return p -> Files.size(p) > size;
    }

    public static AbstractFilter lessThan(long size) {
        return p -> Files.size(p) < size;
    }

    public static AbstractFilter globMatches(String glob) {
        return p -> Pattern
            .compile(".*\\." + glob.substring(glob.indexOf(".") + 1))
            .matcher(p.toString())
            .matches();
    }

    public static AbstractFilter regexContains(String regex) {
        return p -> Pattern
            .compile(regex)
            .matcher(p.toString())
            .find();
    }

    public static AbstractFilter magicNumber(int... bytes) {
        return p -> {
            try (FileInputStream inputStream = new FileInputStream(String.valueOf(p))) {
                for (int b : bytes) {
                    if (inputStream.available() == 0) {
                        return false;
                    }

                    if (inputStream.read() != b) {
                        return false;
                    }
                }
            }
            return true;
        };
    }
}
