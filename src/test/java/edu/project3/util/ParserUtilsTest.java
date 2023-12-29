package edu.project3.util;

import edu.project3.exception.WrongInputLineException;
import edu.project3.exception.WrongLogException;
import edu.project3.model.HttpRequestType;
import edu.project3.model.InputArguments;
import edu.project3.model.NginxLogRecord;
import edu.project3.model.OutputFormat;
import edu.project3.parser.fileParser.NginxFileLogParser;
import edu.project3.parser.urlParser.NginxURLLogParser;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserUtilsTest {
    private static final String LOG_DATE_PATTERN = "dd/MMM/yyyy:HH:mm:ss Z";

    @Test
    @DisplayName("Парсинг логов")
    void parseLogs() {
        assertEquals(
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
            ParserUtils.parseLog(
                "31.22.86.126 - - [17/May/2015:08:05:24 +0000] \"GET /downloads/product_1 HTTP/1.1\" "
                    + "304 0 \"-\" \"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.16)\"\n")
        );

        assertThatThrownBy(() -> ParserUtils.parseLog("wrong log"))
            .isInstanceOf(WrongLogException.class);
    }

    @Test
    @DisplayName("Парсинг входных параметров")
    void parseInput() {
        assertEquals(
            new InputArguments(
                new String[] {"first/second.log", "another.log"},
                Optional.empty(),
                Optional.empty(),
                OutputFormat.markdown
            ),
            ParserUtils.parseInput(new String[] {
                "--path", "first/second.log",
                "--path", "another.log",
                "--format", "markdown"})
        );

        assertThatThrownBy(() -> ParserUtils.parseInput(null))
            .isInstanceOf(NullPointerException.class);

        assertThatThrownBy(() -> ParserUtils.parseInput(new String[] {"wrong", "log"}))
            .isInstanceOf(WrongInputLineException.class);
    }

    @Test
    @DisplayName("Получение LogParser по пути")
    void getLogParserByPath() {
        assertEquals(
            NginxFileLogParser.class,
            ParserUtils.getLogParserByPath("src/test/resources/project3/goodLogs.txt").getClass()
        );

        assertEquals(
            NginxURLLogParser.class,
            ParserUtils.getLogParserByPath("https://raw.githubusercontent.com/elastic/examples/"
                + "master/Common%20Data%20Formats/nginx_logs/nginx_logs").getClass()
        );

        assertThatThrownBy(() -> ParserUtils.getLogParserByPath("dir/a/logs.log"))
            .isInstanceOf(RuntimeException.class);
    }

}
