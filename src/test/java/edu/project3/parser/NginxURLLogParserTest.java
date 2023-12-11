package edu.project3.parser;

import edu.project3.model.HttpRequestType;
import edu.project3.model.NginxLogRecord;
import edu.project3.parser.fileParser.NginxFileLogParser;
import edu.project3.parser.urlParser.NginxURLLogParser;
import java.io.FileNotFoundException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.project3.properties.ApplicationProperties.LOG_DATE_PATTERN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class NginxURLLogParserTest {
    private static final String URL = "https://raw.githubusercontent.com/elastic/examples/master/"
        + "Common%20Data%20Formats/nginx_logs/nginx_logs";

    @Test
    @DisplayName("Чтение логов из URL")
    void parseLogs() throws URISyntaxException {
        assertThat(new NginxURLLogParser(new URI(URL)).parseLogs())
            .containsAll(
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
                        "93.180.71.3",
                        OffsetDateTime.parse(
                            "17/May/2015:08:05:32 +0000",
                            DateTimeFormatter.ofPattern(LOG_DATE_PATTERN, Locale.ENGLISH)
                        ),
                        HttpRequestType.GET,
                        "/downloads/product_1",
                        "HTTP/1.1",
                        304,
                        0,
                        "Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)"
                    )
                )
            );

        assertThatThrownBy(() -> new NginxFileLogParser(Path.of("wrong")).parseLogs())
            .isInstanceOf(FileNotFoundException.class);
    }
}
