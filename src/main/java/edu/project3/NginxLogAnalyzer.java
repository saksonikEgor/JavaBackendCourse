package edu.project3;

import edu.project3.analyze.NginxLogStatisticsCollector;
import edu.project3.filter.LogFilter;
import edu.project3.filter.dateFilter.NginxLogsDateFilter;
import edu.project3.model.InputArguments;
import edu.project3.model.NginxLogReport;
import edu.project3.properties.ApplicationProperties;
import edu.project3.util.ParserUtils;
import edu.project3.writer.LogWriter;
import edu.project3.writer.adoc.AdocLogWriter;
import edu.project3.writer.markdown.MarkdownLogWriter;

public class NginxLogAnalyzer {
    private final InputArguments inputArguments;

    public NginxLogAnalyzer(String[] args) {
        inputArguments = ParserUtils.parseInput(args);
    }

    public void analyse() {
        LogFilter dateFilter = new NginxLogsDateFilter(inputArguments.from(), inputArguments.to());
        var statisticsCollector = new NginxLogStatisticsCollector();

        for (String path : inputArguments.paths()) {
            ParserUtils.getLogParserByPath(path)
                .parseLogs()
                .stream()
                .filter(dateFilter::accept)
                .forEach(statisticsCollector::takeStock);
        }

        NginxLogReport report = statisticsCollector.getReport(inputArguments.from(), inputArguments.to());
        getWriter(report).write();
    }

    private LogWriter getWriter(NginxLogReport report) {
        return switch (inputArguments.outputType()) {
            case adoc -> new AdocLogWriter(report, ApplicationProperties.ADOC_WRITE_PATH);
            case markdown -> new MarkdownLogWriter(report, ApplicationProperties.MARKDOWN_WRITE_PATH);
        };
    }
}
