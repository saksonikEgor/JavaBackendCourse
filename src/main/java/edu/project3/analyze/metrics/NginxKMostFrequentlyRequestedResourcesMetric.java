package edu.project3.analyze.metrics;

import edu.project3.model.NginxLogRecord;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NginxKMostFrequentlyRequestedResourcesMetric implements Metric {
    private final Map<String, Long> freqDict = new HashMap<>();
    private final int kMostFrequency;

    public NginxKMostFrequentlyRequestedResourcesMetric(int kMostFrequency) {
        this.kMostFrequency = kMostFrequency;
    }

    @Override
    public void takeStock(NginxLogRecord log) {
        String resource = log.resource();
        freqDict.put(resource, freqDict.getOrDefault(resource, 0L) + 1);
    }

    @Override
    public List<Map.Entry<String, Long>> getStockedResult() {
        return freqDict.entrySet()
            .stream()
            .sorted(Map.Entry.comparingByValue((v1, v2) -> Long.compare(v2, v1)))
            .limit(kMostFrequency)
            .toList();
    }
}
