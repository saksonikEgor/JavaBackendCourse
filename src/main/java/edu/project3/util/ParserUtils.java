package edu.project3.util;

import edu.project3.exception.WrongInputLineException;
import edu.project3.exception.WrongLogException;
import edu.project3.model.HttpRequestType;
import edu.project3.model.InputArguments;
import edu.project3.model.InputKey;
import edu.project3.model.NginxLogRecord;
import edu.project3.model.OutputFormat;
import edu.project3.parser.logParser.LogParser;
import edu.project3.parser.logParser.fileParser.NginxFileLogParser;
import edu.project3.parser.logParser.urlParser.NginxURLLogParser;
import java.io.FileNotFoundException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParserUtils {
    private static final String LOG_DATE_PATTERN = "dd/MMM/yyyy:HH:mm:ss Z";
    private static final String INPUT_DATE_PATTERN = "yyyy-MM-dd";
    private static final String NGINX_LOG_PATTERN =
        "^((\\d+\\.?)+).+(?<=\\[)(.+?)(?=\\]).+\\\"(GET|HEAD|POST|PUT|DELETE|CONNECT|OPTIONS|TRACE|PATCH)"
            + "\\s(.+?)\\s(.+?)\\\"\\s(\\d+)\\s(\\d+).+\\\"(.+)(?=\\\")";
    private static final int VALID_GROUP_COUNT = 9;
    private static final int REMOTE_IP_GROUP = 1;
    private static final int TIME_LOCAL_GROUP = 3;
    private static final int REQUEST_TYPE_GROUP = 4;
    private static final int RESOURCE_GROUP = 5;
    private static final int HTTP_VERSION_GROUP = 6;
    private static final int RESPONSE_CODE_STATUS_GROUP = 7;
    private static final int BODY_BYTES_SENT_GROUP = 8;
    private static final int HTTP_USER_AGENT_GROUP = 9;
    private static final String URL_PREFIX_PATTERN = "http?s://.*";

    private ParserUtils() {
    }

    public static NginxLogRecord parseLog(String line) {
        Pattern patternLog = Pattern.compile(NGINX_LOG_PATTERN);
        Matcher matcher = patternLog.matcher(line);

        if (matcher.find() && matcher.groupCount() != VALID_GROUP_COUNT) {
            throw new WrongLogException("Cant parse log: " + line);
        }

        return new NginxLogRecord(
            matcher.group(REMOTE_IP_GROUP),
            OffsetDateTime.parse(matcher.group(TIME_LOCAL_GROUP), DateTimeFormatter.ofPattern(LOG_DATE_PATTERN)),
            HttpRequestType.valueOf(matcher.group(REQUEST_TYPE_GROUP)),
            matcher.group(RESOURCE_GROUP),
            matcher.group(HTTP_VERSION_GROUP),
            Integer.parseInt(matcher.group(RESPONSE_CODE_STATUS_GROUP)),
            Integer.parseInt(matcher.group(BODY_BYTES_SENT_GROUP)),
            matcher.group(HTTP_USER_AGENT_GROUP)
        );
    }

    public static InputArguments parseInput(String[] args) {
        if (args == null) {
            throw new NullPointerException("Args cant be null");
        }
        if (args.length % 2 != 0) {
            throw new WrongInputLineException("Wrong arguments in input line");
        }

        Set<String> keys = InputKey.getSetOfKeysName();
        Map<String, String> afterPathParams = new HashMap<>();
        List<String> paths = new ArrayList<>();

        for (int i = 0; i < args.length; i += 2) {
            if (!keys.contains(args[i])) {
                throw new WrongInputLineException("Wrong key: " + args[i]);
            }

            if (args[i].equals(InputKey.Path.toString())) {
                paths.add(args[i + 1]);
            } else {
                if (afterPathParams.containsKey(args[i])) {
                    throw new WrongInputLineException("Key " + args[i] + " cant be repeated");
                }

                afterPathParams.put(args[i], args[i + 1]);
            }
        }

        if (paths.isEmpty()) {
            throw new WrongInputLineException("Key --path is required");
        }

        return new InputArguments(
            paths.toArray(new String[] {}),
            Optional.of(parseStringToDate(afterPathParams.get(InputKey.From.toString()))),
            Optional.of(parseStringToDate(afterPathParams.get(InputKey.To.toString()))),
            getOutTypeOrDefault(afterPathParams.get(InputKey.Format.toString()))
        );
    }

    private static OffsetDateTime parseStringToDate(String date) {
        return date == null ? null : OffsetDateTime.parse(date, DateTimeFormatter.ofPattern(INPUT_DATE_PATTERN));
    }

    private static OutputFormat getOutTypeOrDefault(String format) {
        try {
            return format == null ? null : OutputFormat.valueOf(format);
        } catch (IllegalArgumentException e) {
            throw new WrongInputLineException("Wrong output format: " + format);
        }
    }

    public static LogParser getLogParserByPath(String path) {
        if (path.matches(URL_PREFIX_PATTERN)) {
            try {
                return new NginxURLLogParser(new URI(path));
            } catch (URISyntaxException e) {
                throw new WrongInputLineException("Wrong uri: " + path);
            }
        } else {
            try {
                return new NginxFileLogParser(Path.of(path));
            } catch (FileNotFoundException e) {
                throw new RuntimeException("Cant find file with path: " + path);
            }
        }
    }

}
