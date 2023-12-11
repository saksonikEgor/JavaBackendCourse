package edu.project3.filter;

import edu.project3.model.NginxLogRecord;

@FunctionalInterface
public interface LogFilter {
    boolean accept(NginxLogRecord log);

    default LogFilter and(LogFilter other) {
        return log -> this.accept(log) && other.accept(log);
    }
}
