package edu.project3.analyze.metrics;

import edu.project3.model.NginxLogRecord;
import java.time.DayOfWeek;
import java.util.HashMap;
import java.util.Map;

public class NginxMostPopulatDayOfWeekMetric {
    private final Map<DayOfWeek, Long> fDict = new HashMap<>();

    public void takeStock(NginxLogRecord log) {
        DayOfWeek day = log.timeLocal().getDayOfWeek();
        fDict.put(day, fDict.getOrDefault(day, 0L) + 1);
    }

    public DayOfWeek getMostPopularDayOfWeek() {
        return fDict.entrySet()
            .stream()
            .sorted(Map.Entry.comparingByValue((v1, v2) -> Long.compare(v2, v1)))
            .findFirst()
            .get()
            .getKey();
    }
}
