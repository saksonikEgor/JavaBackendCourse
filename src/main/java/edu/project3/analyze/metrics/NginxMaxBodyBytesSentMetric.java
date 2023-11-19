package edu.project3.analyze.metrics;

import edu.project3.model.NginxLogRecord;

public class NginxMaxBodyBytesSentMetric {
    private long max = 0;

    public void takeStock(NginxLogRecord log) {
        max = Math.max(max, log.bodyBytesSent());
    }

    public long getMax() {
        return max;
    }
}
