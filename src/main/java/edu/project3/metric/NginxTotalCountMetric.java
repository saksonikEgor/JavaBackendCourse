package edu.project3.metric;

public class NginxTotalCountMetric {
    private long totalCount = 0;

    public void takeStock(String log) {
        totalCount++;
    }

    public long getTotalCount() {
        return totalCount;
    }
}
