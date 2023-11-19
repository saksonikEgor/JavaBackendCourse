package edu.project3.filter.dateFilter;

import edu.project3.filter.LogFilter;
import edu.project3.model.NginxLogRecord;

import java.time.OffsetDateTime;
import java.util.Optional;

public class NginxLogsDateFilter implements LogFilter {
    private final OffsetDateTime timeLowerBound;
    private final OffsetDateTime timeUpperBound;

    public NginxLogsDateFilter(
        Optional<OffsetDateTime> timeLowerBound,
        Optional<OffsetDateTime> timeUpperBound
    ) {
        this.timeLowerBound = timeLowerBound.orElse(OffsetDateTime.MIN);
        this.timeUpperBound = timeUpperBound.orElse(OffsetDateTime.MAX);
    }

    @Override
    public boolean accept(NginxLogRecord log) {
        OffsetDateTime date = log.timeLocal();
        return date.isAfter(timeLowerBound) && date.isBefore(timeUpperBound);
    }
}
