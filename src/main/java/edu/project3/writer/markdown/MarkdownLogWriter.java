package edu.project3.writer.markdown;

import edu.project3.model.NginxLogReport;
import edu.project3.util.WriterUtils;
import edu.project3.writer.LogWriter;

public class MarkdownLogWriter extends LogWriter {
    public MarkdownLogWriter(NginxLogReport report, String path) {
        super(report, path);
    }

    public String getGeneralInfo() {
        StringBuilder sb = new StringBuilder();

        sb.append("#### Общая информация\n\n")
            .append("|        Метрика        |     Значение |\n")
            .append("|:---------------------:|-------------:|\n")

            .append("| Начальная дата | ")
            .append(report.from().isPresent() ? report.from().get().toString() : "-").append("\n")
            .append("| Конечная дата | ")
            .append(report.to().isPresent() ? report.to().get().toString() : "-").append("\n")

            .append("| Количество запросов | ")
            .append(report.totalCount()).append("\n")

            .append("| Средний размер ответа | ")
            .append(report.avgBodyBytesSent()).append("\n");
        return sb.toString();
    }

    public String getResources() {
        StringBuilder sb = new StringBuilder();

        sb.append("#### Запрашиваемые ресурсы\n\n")
            .append("|     Ресурс      | Количество |\n")
            .append("|:---------------:|-----------:|\n");

        for (var resource : report.mostFrequentlyResources()) {
            sb.append("| ").append(resource.getKey()).append(" | ").append(resource.getValue());
        }

        return sb.toString();
    }

    public String getCodes() {
        StringBuilder sb = new StringBuilder();

        sb.append("#### Коды ответа\n\n")
            .append("| Код |          Имя          | Количество |\n")
            .append("|:---:|:---------------------:|-----------:|\n");

        for (var statusCode : report.mostFrequentlyStatusCode()) {
            sb.append(String.format(
                "| %d | %s | %d\n",
                statusCode.getKey(),
                WriterUtils.getNameByCode(statusCode.getKey()),
                statusCode.getValue()
            ));
        }

        return sb.toString();
    }
}
