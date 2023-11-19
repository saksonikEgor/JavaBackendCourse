package edu.project3.writer.adoc;

import edu.project3.model.NginxLogReport;
import edu.project3.util.WriterUtils;
import edu.project3.writer.LogWriter;

public class AdocLogWriter extends LogWriter {
    private static final String SEPARATOR = "|===\n";
    private static final String HEADER = "[options=\"header\"]\n";

    public AdocLogWriter(NginxLogReport report, String path) {
        super(report, path);
    }

    public String getGeneralInfo() {
        StringBuilder sb = new StringBuilder();

        sb.append("==== Общая информация\n\n")
            .append(HEADER)
            .append(SEPARATOR)
            .append("| Метрика | Значение\n")

            .append("| Начальная дата | ")
            .append(report.from().isPresent() ? report.from().get().toString() : "-").append("\n")
            .append("| Конечная дата | ")
            .append(report.to().isPresent() ? report.to().get().toString() : "-").append("\n")

            .append("| Количество запросов | ")
            .append(report.totalCount()).append("\n")

            .append("| Средний размер ответа | ")
            .append(report.avgBodyBytesSent()).append("\n")
            .append(SEPARATOR);
        return sb.toString();
    }

    public String getResources() {
        StringBuilder sb = new StringBuilder();

        sb.append("==== Запрашиваемые ресурсы\n\n")
            .append(HEADER)
            .append(SEPARATOR)
            .append("| Ресурс | Количество\n");

        for (var resource : report.mostFrequentlyResources()) {
            sb.append("| ").append(resource.getKey()).append(" | ").append(resource.getValue()).append("\n");
        }

        sb.append(SEPARATOR);
        return sb.toString();
    }

    public String getCodes() {
        StringBuilder sb = new StringBuilder();

        sb.append("==== Коды ответа\n\n")
            .append(HEADER)
            .append(SEPARATOR)
            .append("| Код | Имя | Количество\n");

        for (var statusCode : report.mostFrequentlyStatusCode()) {
            sb.append("| ")
                .append(statusCode.getKey())
                .append(" | ")
                .append(WriterUtils.getNameByCode(statusCode.getKey()))
                .append(" | ")
                .append(statusCode.getValue())
                .append("\n");

        }

        sb.append(SEPARATOR);
        return sb.toString();
    }
}
