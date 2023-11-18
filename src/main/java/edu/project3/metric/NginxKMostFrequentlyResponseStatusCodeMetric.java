package edu.project3.metric;

import edu.project3.util.NginxLogParserUtils;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NginxKMostFrequentlyResponseStatusCodeMetric {
    private final Map<Integer, Integer> freqDict = new HashMap<>();
    private final int kMostFrequency;

    public NginxKMostFrequentlyResponseStatusCodeMetric(int kMostFrequency) {
        this.kMostFrequency = kMostFrequency;
    }

    public void takeStock(String log) {
        int statusCode = NginxLogParserUtils.getResponseStatusCode(log);
        freqDict.put(statusCode, freqDict.getOrDefault(statusCode, 0) + 1);
    }

    public List<Map.Entry<Integer, Integer>> getMostFrequencyResponseStatusCodes() {
        return freqDict.entrySet()
            .stream()
            .sorted(Map.Entry.comparingByValue((v1, v2) -> Integer.compare(v2, v1)))
            .limit(kMostFrequency)
            .toList();
    }
}
