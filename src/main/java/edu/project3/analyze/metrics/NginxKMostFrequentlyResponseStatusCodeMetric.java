package edu.project3.analyze.metrics;

import edu.project3.model.NginxLogRecord;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NginxKMostFrequentlyResponseStatusCodeMetric implements Metric {
    private final Map<Integer, Long> freqDict = new HashMap<>();
    private final int kMostFrequency;

    public NginxKMostFrequentlyResponseStatusCodeMetric(int kMostFrequency) {
        this.kMostFrequency = kMostFrequency;
    }

    @Override
    public void takeStock(NginxLogRecord log) {
        int statusCode = log.responseCodeStatus();
        freqDict.put(statusCode, freqDict.getOrDefault(statusCode, 0L) + 1);
    }

    @Override
    public List<Map.Entry<Integer, Long>> getStockedResult() {
        return freqDict.entrySet()
            .stream()
            .sorted(Map.Entry.comparingByValue((v1, v2) -> Long.compare(v2, v1)))
            .limit(kMostFrequency)
            .toList();
    }
}
