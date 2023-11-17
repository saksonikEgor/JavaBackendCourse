package edu.project3.filter;

@FunctionalInterface
public interface LogFilter {
    boolean accept(String log);

    default LogFilter and(LogFilter other) {
        return log -> this.accept(log) && other.accept(log);
    }
}
