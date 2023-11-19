package edu.project3.util;

import java.util.Map;

public class WriterUtils {
    private static final Map<Integer, String> HTTP_STATUS = Map.of(
        200, "OK",
        404, "Not found",
        304, "Not Modified",
        206, "Partial Content",
        403, "Forbidden",
        416, "Requested Range Not Satisfiable",
        505, "HTTP Version Not Supported"
    );

    private WriterUtils() {
    }

    public static String getNameByCode(int code) {
        return HTTP_STATUS.get(code);
    }
}
