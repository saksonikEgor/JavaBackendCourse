package edu.project3.model;

import java.time.OffsetDateTime;

public record NginxLogRecord(
    String remoteIP,
    OffsetDateTime timeLocal,
    HttpRequestType requestType,
    String resource,
    String httpVersion,
    int responseCodeStatus,
    long bodyBytesSent,
    String httpUserAgent
) {
}
