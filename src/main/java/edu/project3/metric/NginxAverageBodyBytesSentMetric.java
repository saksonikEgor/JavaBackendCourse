package edu.project3.metric;

import edu.project3.model.NginxLogRecord;

public class NginxAverageBodyBytesSentMetric {
    private long totalCount = 0;
    private long totalBodyBytesSent = 0;

    public void takeStock(NginxLogRecord log) {
        totalCount++;
        totalBodyBytesSent += log.bodyBytesSent();
    }

    public long getAverageBodyBytesSent() {
        return totalBodyBytesSent / totalCount;
    }
}
