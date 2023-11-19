package edu.project3.filter;

import edu.project3.filter.dateFilter.NginxLogsDateFilter;
import edu.project3.model.HttpRequestType;
import edu.project3.model.NginxLogRecord;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;

import static edu.project3.properties.ApplicationProperties.LOG_DATE_PATTERN;
import static org.assertj.core.api.Assertions.assertThat;

public class NginxLogDateFilterTest {
    @Test
    @DisplayName("Фильтрация логов по дате")
    void dateFilter() {
        LogFilter filter = new NginxLogsDateFilter(
            OffsetDateTime.parse(
                "17/May/2015:08:03:24 +0000",
                DateTimeFormatter.ofPattern(LOG_DATE_PATTERN, Locale.ENGLISH)
            ),
            OffsetDateTime.parse(
                "17/May/2015:08:08:24 +0000",
                DateTimeFormatter.ofPattern(LOG_DATE_PATTERN, Locale.ENGLISH)
            )
        );

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
                    "31.22.86.126",
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
        ).hasSameElementsAs(
            Stream.of(
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
                    "31.22.86.126",
                    OffsetDateTime.parse(
                        "17/May/2015:23:07:24 +0000",
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
                    "31.22.86.126",
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
                ),
                new NginxLogRecord(
                    "31.22.86.126",
                    OffsetDateTime.parse(
                        "17/May/2015:02:01:24 +0000",
                        DateTimeFormatter.ofPattern(LOG_DATE_PATTERN, Locale.ENGLISH)
                    ),
                    HttpRequestType.GET,
                    "/downloads/product_1",
                    "HTTP/1.1",
                    304,
                    0,
                    "Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.16)"
                )
            ).filter(filter::accept).toList()
        );
    }

}
