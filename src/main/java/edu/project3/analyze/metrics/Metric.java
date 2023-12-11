package edu.project3.analyze.metrics;

import edu.project3.model.NginxLogRecord;

public interface Metric {
    void takeStock(NginxLogRecord log);

    Object getStockedResult();
}
