package edu.project3.analyze.metrics;

import edu.project3.model.NginxLogRecord;

public class NginxTotalCountMetric implements Metric {
    private long totalCount = 0;

    @Override
    public void takeStock(NginxLogRecord log) {
        totalCount++;
    }

    @Override
    public Long getStockedResult() {
        return totalCount;
    }
}
