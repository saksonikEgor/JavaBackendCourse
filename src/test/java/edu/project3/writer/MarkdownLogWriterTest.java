package edu.project3.writer;

import edu.project3.model.NginxLogReport;
import edu.project3.writer.markdown.MarkdownLogWriter;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.project3.properties.ApplicationProperties.LOG_DATE_PATTERN;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MarkdownLogWriterTest {
    private static final String PATHNAME = "src/test/resources/project3/output.md";

    private static String readOutput() {
        StringBuilder sb = new StringBuilder();

        try (
            BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(new FileInputStream(PATHNAME))
            )) {
            while (bufferedReader.ready()) {
                sb.append(bufferedReader.readLine()).append("\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return sb.toString();
    }

    @Test
    @DisplayName("Сохранение статистики в md формате")
    void adocWrite() {
        var mostFreqRes = new LinkedHashMap<String, Long>();
        var mostFreqStatusCode = new LinkedHashMap<Integer, Long>();

        mostFreqRes.put("fist/another/logs.log", 13L);
        mostFreqRes.put("tee/nginxLong.log", 10L);

        mostFreqStatusCode.put(200, 13L);
        mostFreqStatusCode.put(404, 10L);

        new MarkdownLogWriter(new NginxLogReport(
            Optional.empty(),
            Optional.of(
                OffsetDateTime.parse(
                    "17/May/2015:08:05:24 +0000",
                    DateTimeFormatter.ofPattern(LOG_DATE_PATTERN, Locale.ENGLISH)
                )
            ),
            23,
            mostFreqRes.entrySet().stream().toList(),
            mostFreqStatusCode.entrySet().stream().toList(),
            240
        ), PATHNAME).write();

        assertEquals("""
            #### Общая информация

            |        Метрика        |     Значение |
            |:---------------------:|-------------:|
            | Начальная дата | -
            | Конечная дата | 2015-05-17T08:05:24Z
            | Количество запросов | 23
            | Средний размер ответа | 240

            #### Запрашиваемые ресурсы

            |     Ресурс      | Количество |
            |:---------------:|-----------:|
            | fist/another/logs.log | 13
            | tee/nginxLong.log | 10


            #### Коды ответа

            | Код |          Имя          | Количество |
            |:---:|:---------------------:|-----------:|
            | 200 | OK | 13
            | 404 | Not Found | 10
            """, readOutput());
    }
}
