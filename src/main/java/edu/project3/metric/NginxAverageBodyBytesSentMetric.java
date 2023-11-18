package edu.project3.metric;

import edu.project3.util.NginxLogParserUtils;

public class NginxAverageBodyBytesSentMetric {
    private long totalCount = 0;
    private long totalBodyBytesSent = 0;

    public void takeStock(String log) {
        totalCount++;
        totalBodyBytesSent = NginxLogParserUtils.getBodyBytesSent(log);
    }

    public long getAverageBodyBytesSent() {
        return totalBodyBytesSent / totalCount;
    }
}
