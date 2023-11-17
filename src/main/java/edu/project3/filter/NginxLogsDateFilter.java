package edu.project3.filter;

import edu.project3.util.NginxLogParserUtils;
import java.time.OffsetDateTime;

public class NginxLogsDateFilter implements LogFilter {
    private final OffsetDateTime timeLowerBound;
    private final OffsetDateTime timeUpperBound;

    public NginxLogsDateFilter(OffsetDateTime timeLowerBound, OffsetDateTime timeUpperBound) {
        this.timeLowerBound = timeLowerBound;
        this.timeUpperBound = timeUpperBound;
    }

    @Override
    public boolean accept(String log) {
        OffsetDateTime date = NginxLogParserUtils.getDateFromLog(log);
        return date.isAfter(timeLowerBound) && date.isBefore(timeUpperBound);
    }
}
