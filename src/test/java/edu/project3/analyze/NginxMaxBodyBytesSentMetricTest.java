package edu.project3.analyze;

import edu.project3.analyze.metrics.NginxMaxBodyBytesSentMetric;
import edu.project3.model.HttpRequestType;
import edu.project3.model.NginxLogRecord;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.project3.properties.ApplicationProperties.LOG_DATE_PATTERN;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class NginxMaxBodyBytesSentMetricTest {
    @Test
    @DisplayName("Подсчет максимального количества переданный байт")
    void countAvgBodyBytes() {
        var metric = new NginxMaxBodyBytesSentMetric();

        metric.takeStock(new NginxLogRecord(
            "31.22.86.126",
            OffsetDateTime.parse(
                "17/May/2015:08:05:24 +0000",
                DateTimeFormatter.ofPattern(LOG_DATE_PATTERN, Locale.ENGLISH)
            ),
            HttpRequestType.GET,
            "/downloads/product_1",
            "HTTP/1.1",
            304,
            220,
            "Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.16)"

        ));

        metric.takeStock(new NginxLogRecord(
            "31.22.76.126",
            OffsetDateTime.parse(
                "17/May/2015:08:07:24 +0000",
                DateTimeFormatter.ofPattern(LOG_DATE_PATTERN, Locale.ENGLISH)
            ),
            HttpRequestType.GET,
            "/downloads/product_1",
            "HTTP/1.1",
            304,
            280,
            "Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.16)"
        ));

        metric.takeStock(new NginxLogRecord(
            "31.22.76.126",
            OffsetDateTime.parse(
                "17/May/2015:06:07:21 +0000",
                DateTimeFormatter.ofPattern(LOG_DATE_PATTERN, Locale.ENGLISH)
            ),
            HttpRequestType.GET,
            "/downloads/product_2",
            "HTTP/1.1",
            340,
            380,
            "Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.16)"
        ));

        assertEquals(380, metric.getStockedResult());
    }
}
