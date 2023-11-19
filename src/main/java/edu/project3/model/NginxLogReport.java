package edu.project3.model;

import java.time.DayOfWeek;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public record NginxLogReport(
    Optional<OffsetDateTime> from,
    Optional<OffsetDateTime> to,
    long totalCount,
    List<Map.Entry<String, Long>> mostFrequentlyResources,
    List<Map.Entry<Integer, Long>> mostFrequentlyStatusCode,
    long avgBodyBytesSent,
    long maxBudyBytesSent,
    DayOfWeek mostPopularDayOfWeek
) {
}
