package edu.project3.model;

import java.time.OffsetDateTime;

public record NginxLogRecord(
    String remoteAddress,
    String removeUser,
    OffsetDateTime timeLocal,
    HttpRequestType requestType,
    String resource,
    String httpVersion,
    int responseCodeStatus,
    long bodyBytesSent,
    String httpReferer,
    String httpUserAgent
    ) {
//    public LogRecord(String log) {
//        if (log == null) {
//            throw new NullPointerException("Log cant be null");
//        }
//
//        Pattern pattern = Pattern.compile()
//
//    }

}
