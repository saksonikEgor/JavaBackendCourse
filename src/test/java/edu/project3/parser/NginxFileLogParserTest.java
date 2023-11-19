package edu.project3.parser;

import edu.project3.exception.WrongLogException;
import edu.project3.model.HttpRequestType;
import edu.project3.model.NginxLogRecord;
import edu.project3.parser.fileParser.NginxFileLogParser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import static edu.project3.properties.ApplicationProperties.LOG_DATE_PATTERN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class NginxFileLogParserTest {
    private static final String GOOD_LOGS_PATHNAME = "src/test/resources/project3/goodLogs.txt";
    private static final String BAD_LOGS_PATHNAME = "src/test/resources/project3/badLogs.txt";

    @Test
    @DisplayName("Чтение логов из файла")
    void parseLogs() throws FileNotFoundException {
        assertThat(
            List.of(
                new NginxLogRecord(
                    "31.22.86.126",
                    OffsetDateTime.parse(
                        "17/May/2015:08:05:24 +0000",
                        DateTimeFormatter.ofPattern(LOG_DATE_PATTERN, Locale.ENGLISH)
                    ),
                    HttpRequestType.GET,
                    "/downloads/product_1",
                    "HTTP/1.1",
                    304,
                    0,
                    "Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.16)"
                ),
                new NginxLogRecord(
                    "31.22.76.126",
                    OffsetDateTime.parse(
                        "17/May/2015:08:07:24 +0000",
                        DateTimeFormatter.ofPattern(LOG_DATE_PATTERN, Locale.ENGLISH)
                    ),
                    HttpRequestType.GET,
                    "/downloads/product_1",
                    "HTTP/1.1",
                    304,
                    0,
                    "Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.16)"
                )
            )
        ).hasSameElementsAs(new NginxFileLogParser(Path.of(GOOD_LOGS_PATHNAME)).parseLogs());

        assertThatThrownBy(() -> new NginxFileLogParser(Path.of("wrong")).parseLogs())
            .isInstanceOf(FileNotFoundException.class);

        assertThatThrownBy(() -> new NginxFileLogParser(Path.of(BAD_LOGS_PATHNAME)).parseLogs())
            .isInstanceOf(WrongLogException.class);
    }
}
