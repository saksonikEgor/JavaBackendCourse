package edu.project3.util;

import edu.project3.properties.ApplicationProperties;
import java.time.OffsetDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NginxLogParserUtils {
    private NginxLogParserUtils() {
    }

    public static String getResourceFromLog(String log) {
        if (log == null) {
            throw new NullPointerException("Log cant be null");
        }

        Pattern pattern = Pattern.compile(ApplicationProperties.REQUEST_PATTERN);
        Matcher matcher = pattern.matcher(log);

        if (!matcher.find()) {
            ApplicationProperties.LOGGER.info("Cant parse nginx log: \"{}\" to the request pattern", log);
            throw new IllegalArgumentException("Wrong nginx log");
        }

        return matcher.group(0).split(" ")[1];
    }

    public static int getResponseStatusCode(String log) {
        if (log == null) {
            throw new NullPointerException("Log cant be null");
        }

        return Integer.parseInt(getHttpResponseStatusAndBodyBytesSent(log).split(" ")[0]);
    }

    public static long getBodyBytesSent(String log) {
        if (log == null) {
            throw new NullPointerException("Log cant be null");
        }

        return Long.parseLong(getHttpResponseStatusAndBodyBytesSent(log).split(" ")[1]);
    }

    public static OffsetDateTime getDateFromLog(String log) {
        if (log == null) {
            throw new NullPointerException("Log cant be null");
        }

        Pattern pattern = Pattern.compile(ApplicationProperties.DATE_PATTERN);
        Matcher matcher = pattern.matcher(log);

        if (!matcher.find()) {
            ApplicationProperties.LOGGER.info("Cant parse nginx log: \"{}\" to the date pattern", log);
            throw new IllegalArgumentException("Wrong nginx log");
        }

        return OffsetDateTime.parse(matcher.group(0));
    }

    private static String getHttpResponseStatusAndBodyBytesSent(String log) {
        Pattern pattern = Pattern.compile(ApplicationProperties.HTTP_RESPONSE_STATUS_AND_BODY_BYTES_SEND_PATTERN);
        Matcher matcher = pattern.matcher(log);

        if (!matcher.find()) {
            ApplicationProperties.LOGGER.info(
                "Cant parse nginx log: \"{}\" http response status and body bytes send pattern",
                log
            );
            throw new IllegalArgumentException("Wrong nginx log");
        }

        return matcher.group(0);
    }

}
