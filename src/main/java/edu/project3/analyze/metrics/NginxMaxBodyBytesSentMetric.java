package edu.project3.analyze.metrics;

import edu.project3.model.NginxLogRecord;

public class NginxMaxBodyBytesSentMetric implements Metric {
    private long max = 0;

    @Override
    public void takeStock(NginxLogRecord log) {
        max = Math.max(max, log.bodyBytesSent());
    }

    @Override
    public Long getStockedResult() {
        return max;
    }
}
