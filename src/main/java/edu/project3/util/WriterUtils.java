package edu.project3.util;

import static edu.project3.properties.ApplicationProperties.HTTP_STATUS;

public class WriterUtils {
    private WriterUtils() {
    }

    public static String getNameByCode(int code) {
        return HTTP_STATUS.get(code);
    }
}
