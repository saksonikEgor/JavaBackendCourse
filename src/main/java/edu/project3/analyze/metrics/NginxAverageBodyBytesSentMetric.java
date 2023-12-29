package edu.project3.analyze.metrics;

import edu.project3.model.NginxLogRecord;

public class NginxAverageBodyBytesSentMetric implements Metric {
    private long totalCount = 0;
    private long totalBodyBytesSent = 0;

    @Override
    public void takeStock(NginxLogRecord log) {
        totalCount++;
        totalBodyBytesSent += log.bodyBytesSent();
    }

    @Override
    public Long getStockedResult() {
        return totalBodyBytesSent / totalCount;
    }
}
