package edu.project3.analyze;

import edu.project3.analyze.metrics.Metric;
import edu.project3.analyze.metrics.NginxAverageBodyBytesSentMetric;
import edu.project3.analyze.metrics.NginxKMostFrequentlyRequestedResourcesMetric;
import edu.project3.analyze.metrics.NginxKMostFrequentlyResponseStatusCodeMetric;
import edu.project3.analyze.metrics.NginxMaxBodyBytesSentMetric;
import edu.project3.analyze.metrics.NginxMostPopularDayOfWeekMetric;
import edu.project3.analyze.metrics.NginxTotalCountMetric;
import edu.project3.model.NginxLogRecord;
import edu.project3.model.NginxLogReport;
import edu.project3.properties.ApplicationProperties;
import java.time.DayOfWeek;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class NginxLogStatisticsCollector {
    private final List<Metric> metrics;

    public NginxLogStatisticsCollector() {
        // order is important
        metrics = new ArrayList<>(List.of(
            new NginxTotalCountMetric(),
            new NginxKMostFrequentlyRequestedResourcesMetric(ApplicationProperties.MOST_FREQUENTLY_RESOURCE_COUNT),
            new NginxKMostFrequentlyResponseStatusCodeMetric(ApplicationProperties.MOST_FREQUENTLY_STATUS_CODE_COUNT),
            new NginxAverageBodyBytesSentMetric(),
            new NginxMaxBodyBytesSentMetric(),
            new NginxMostPopularDayOfWeekMetric()
        ));
    }

    public void takeStock(NginxLogRecord log) {
        metrics.forEach(m -> m.takeStock(log));
    }

    public NginxLogReport getReport(Optional<OffsetDateTime> from, Optional<OffsetDateTime> to) {
        return new NginxLogReport(
            from,
            to,
            (Long) metrics.removeFirst().getStockedResult(),
            (List<Map.Entry<String, Long>>) metrics.removeFirst().getStockedResult(),
            (List<Map.Entry<Integer, Long>>) metrics.removeFirst().getStockedResult(),
            (Long) metrics.removeFirst().getStockedResult(),
            (Long) metrics.removeFirst().getStockedResult(),
            (DayOfWeek) metrics.removeFirst().getStockedResult()
        );
    }
}
