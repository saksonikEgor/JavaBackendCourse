package edu.project3.metric;

import edu.project3.util.NginxLogParserUtils;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NginxKMostFrequentlyRequestedResourcesMetric {
    private final Map<String, Integer> freqDict = new HashMap<>();
    private final int kMostFrequency;

    public NginxKMostFrequentlyRequestedResourcesMetric(int kMostFrequency) {
        this.kMostFrequency = kMostFrequency;
    }

    public void takeStock(String log) {
        String resource = NginxLogParserUtils.getResourceFromLog(log);
        freqDict.put(resource, freqDict.getOrDefault(resource, 0) + 1);
    }

    public List<Map.Entry<String, Integer>> getMostFrequencyResources() {
        return freqDict.entrySet()
            .stream()
            .sorted(Map.Entry.comparingByValue((v1, v2) -> Integer.compare(v2, v1)))
            .limit(kMostFrequency)
            .toList();
    }
}
