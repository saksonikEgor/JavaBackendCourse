package edu.project3.analyze;

import edu.project3.analyze.metrics.NginxAverageBodyBytesSentMetric;
import edu.project3.analyze.metrics.NginxKMostFrequentlyRequestedResourcesMetric;
import edu.project3.analyze.metrics.NginxKMostFrequentlyResponseStatusCodeMetric;
import edu.project3.analyze.metrics.NginxTotalCountMetric;
import edu.project3.model.NginxLogRecord;
import edu.project3.model.NginxLogReport;
import edu.project3.properties.ApplicationProperties;
import java.time.OffsetDateTime;
import java.util.Optional;

public class NginxLogStatisticsCollector {
    private final NginxAverageBodyBytesSentMetric averageBodyBytesSentMetric;
    private final NginxKMostFrequentlyRequestedResourcesMetric mostFrequentlyRequestedResourcesMetric;
    private final NginxKMostFrequentlyResponseStatusCodeMetric mostFrequentlyResponseStatusCodeMetric;
    private final NginxTotalCountMetric totalCountMetric;

    public NginxLogStatisticsCollector() {
        averageBodyBytesSentMetric = new NginxAverageBodyBytesSentMetric();
        mostFrequentlyRequestedResourcesMetric = new NginxKMostFrequentlyRequestedResourcesMetric(
            ApplicationProperties.MOST_FREQUENTLY_RESOURCE_COUNT
        );
        mostFrequentlyResponseStatusCodeMetric = new NginxKMostFrequentlyResponseStatusCodeMetric(
            ApplicationProperties.MOST_FREQUENTLY_STATUS_CODE_COUNT
        );
        totalCountMetric = new NginxTotalCountMetric();
    }

    public void takeStock(NginxLogRecord log) {
        averageBodyBytesSentMetric.takeStock(log);
        mostFrequentlyResponseStatusCodeMetric.takeStock(log);
        mostFrequentlyRequestedResourcesMetric.takeStock(log);
        totalCountMetric.takeStock(log);
    }

    public NginxLogReport getReport(Optional<OffsetDateTime> from, Optional<OffsetDateTime> to) {
        return new NginxLogReport(
            from,
            to,
            totalCountMetric.getTotalCount(),
            mostFrequentlyRequestedResourcesMetric.getMostFrequencyResources(),
            mostFrequentlyResponseStatusCodeMetric.getMostFrequencyResponseStatusCodes(),
            averageBodyBytesSentMetric.getAverageBodyBytesSent()
        );
    }
}
