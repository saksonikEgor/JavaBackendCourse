package edu.project3.analyze.metrics;

import edu.project3.model.NginxLogRecord;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NginxKMostFrequentlyRequestedResourcesMetric {
    private final Map<String, Long> freqDict = new HashMap<>();
    private final int kMostFrequency;

    public NginxKMostFrequentlyRequestedResourcesMetric(int kMostFrequency) {
        this.kMostFrequency = kMostFrequency;
    }

    public void takeStock(NginxLogRecord log) {
        String resource = log.resource();
        freqDict.put(resource, freqDict.getOrDefault(resource, 0L) + 1);
    }

    public List<Map.Entry<String, Long>> getMostFrequencyResources() {
        return freqDict.entrySet()
            .stream()
            .sorted(Map.Entry.comparingByValue((v1, v2) -> Long.compare(v2, v1)))
            .limit(kMostFrequency)
            .toList();
    }
}
