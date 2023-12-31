package edu.project3.util;

import edu.project3.exception.WrongInputLineException;
import edu.project3.exception.WrongLogException;
import edu.project3.model.HttpRequestType;
import edu.project3.model.InputArguments;
import edu.project3.model.InputKey;
import edu.project3.model.NginxLogRecord;
import edu.project3.model.OutputFormat;
import edu.project3.parser.LogParser;
import edu.project3.parser.fileParser.NginxFileLogParser;
import edu.project3.parser.urlParser.NginxURLLogParser;
import java.io.FileNotFoundException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static edu.project3.properties.ApplicationProperties.BODY_BYTES_SENT_GROUP;
import static edu.project3.properties.ApplicationProperties.HTTP_USER_AGENT_GROUP;
import static edu.project3.properties.ApplicationProperties.HTTP_VERSION_GROUP;
import static edu.project3.properties.ApplicationProperties.INPUT_DATE_PATTERN;
import static edu.project3.properties.ApplicationProperties.LOG_DATE_PATTERN;
import static edu.project3.properties.ApplicationProperties.NGINX_LOG_PATTERN;
import static edu.project3.properties.ApplicationProperties.REMOTE_IP_GROUP;
import static edu.project3.properties.ApplicationProperties.REQUEST_TYPE_GROUP;
import static edu.project3.properties.ApplicationProperties.RESOURCE_GROUP;
import static edu.project3.properties.ApplicationProperties.RESPONSE_CODE_STATUS_GROUP;
import static edu.project3.properties.ApplicationProperties.TIME_LOCAL_GROUP;
import static edu.project3.properties.ApplicationProperties.URL_PREFIX_PATTERN;
import static edu.project3.properties.ApplicationProperties.VALID_GROUP_COUNT;

public class ParserUtils {
    private ParserUtils() {
    }

    public static NginxLogRecord parseLog(String line) {
        Pattern patternLog = Pattern.compile(NGINX_LOG_PATTERN);
        Matcher matcher = patternLog.matcher(line);

        if (!matcher.find() || matcher.groupCount() != VALID_GROUP_COUNT) {
            throw new WrongLogException("Cant parse log: " + line);
        }

        try {
            return new NginxLogRecord(
                matcher.group(REMOTE_IP_GROUP),
                OffsetDateTime.parse(
                    matcher.group(TIME_LOCAL_GROUP),
                    DateTimeFormatter.ofPattern(LOG_DATE_PATTERN, Locale.ENGLISH)
                ),
                HttpRequestType.valueOf(matcher.group(REQUEST_TYPE_GROUP)),
                matcher.group(RESOURCE_GROUP),
                matcher.group(HTTP_VERSION_GROUP),
                Integer.parseInt(matcher.group(RESPONSE_CODE_STATUS_GROUP)),
                Integer.parseInt(matcher.group(BODY_BYTES_SENT_GROUP)),
                matcher.group(HTTP_USER_AGENT_GROUP)
            );
        } catch (IllegalArgumentException | DateTimeParseException e) {
            throw new WrongLogException("Wrong log content");
        }
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

        try {
            Optional<OffsetDateTime> from = afterPathParams.containsKey(InputKey.From.toString())
                ? Optional.of(parseStringToDate(afterPathParams.get(InputKey.From.toString())))
                : Optional.empty();

            Optional<OffsetDateTime> to = afterPathParams.containsKey(InputKey.To.toString())
                ? Optional.of(parseStringToDate(afterPathParams.get(InputKey.To.toString())))
                : Optional.empty();
            return new InputArguments(
                paths.toArray(new String[] {}),
                from,
                to,
                getOutTypeOrDefault(afterPathParams.get(InputKey.Format.toString()))
            );
        } catch (DateTimeParseException e) {
            throw new WrongInputLineException("Wrong input date");
        }
    }

    private static OffsetDateTime parseStringToDate(String date) {
        return date == null
            ? null
            : OffsetDateTime.parse(date, DateTimeFormatter.ofPattern(INPUT_DATE_PATTERN, Locale.ENGLISH));
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
