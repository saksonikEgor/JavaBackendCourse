package edu.project3.metric;

import edu.project3.model.NginxLogRecord;

public class NginxTotalCountMetric {
    private long totalCount = 0;

    public void takeStock(NginxLogRecord log) {
        totalCount++;
    }

    public long getTotalCount() {
        return totalCount;
    }
}
