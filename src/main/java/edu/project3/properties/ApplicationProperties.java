package edu.project3.properties;

import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ApplicationProperties {
    public static final Logger LOGGER = LogManager.getLogger();
    public static final int MOST_FREQUENTLY_RESOURCE_COUNT = 5;
    public static final int MOST_FREQUENTLY_STATUS_CODE_COUNT = 5;
    public static final String ADOC_WRITE_PATH = "";
    public static final String MARKDOWN_WRITE_PATH = "";
    public static final Map<Integer, String> HTTP_STATUS = Map.of(
        200, "OK",
        404, "Not found",
        304, "Not Modified",
        206, "Partial Content",
        403, "Forbidden",
        416, "Requested Range Not Satisfiable",
        505, "HTTP Version Not Supported"
    );
    public static final String LOG_DATE_PATTERN = "dd/MMM/yyyy:HH:mm:ss Z";
    public static final String INPUT_DATE_PATTERN = "yyyy-MM-dd";
    public static final String NGINX_LOG_PATTERN =
        "^((\\d+\\.?)+).+(?<=\\[)(.+?)(?=\\]).+\\\"(GET|HEAD|POST|PUT|DELETE|CONNECT|OPTIONS|TRACE|PATCH)"
            + "\\s(.+?)\\s(.+?)\\\"\\s(\\d+)\\s(\\d+).+\\\"(.+)(?=\\\")";
    public static final int VALID_GROUP_COUNT = 9;
    public static final int REMOTE_IP_GROUP = 1;
    public static final int TIME_LOCAL_GROUP = 3;
    public static final int REQUEST_TYPE_GROUP = 4;
    public static final int RESOURCE_GROUP = 5;
    public static final int HTTP_VERSION_GROUP = 6;
    public static final int RESPONSE_CODE_STATUS_GROUP = 7;
    public static final int BODY_BYTES_SENT_GROUP = 8;
    public static final int HTTP_USER_AGENT_GROUP = 9;
    public static final String URL_PREFIX_PATTERN = "http?s://.*";

    private ApplicationProperties() {
    }
}
