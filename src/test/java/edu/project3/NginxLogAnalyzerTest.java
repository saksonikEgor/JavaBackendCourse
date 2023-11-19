package edu.project3;

import edu.project3.exception.WrongInputLineException;
import edu.project3.properties.ApplicationProperties;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class NginxLogAnalyzerTest {
    @Test
    @DisplayName("Анализ логов")
    void analyze() {
        new NginxLogAnalyzer(
            new String[] {
                "--path", "src/test/resources/project3/manyGoodLogs.txt",
                "--path", "src/test/resources/project3/goodLogs.txt",
                "--from", "17/May/2015:08:05:32 +0000",
                "--to", "17/May/2015:08:12:22 +0000",
                "--format", "adoc"
            }
        ).analyse();

        assertEquals("""
            ==== Общая информация

            [options="header"]
            |===
            | Метрика | Значение
            | Начальная дата | 2015-05-17T08:05:32Z
            | Конечная дата | 2015-05-17T08:12:22Z
            | Количество запросов | 50
            | Средний размер ответа | 192
            |===
            ==== Запрашиваемые ресурсы

            [options="header"]
            |===
            | Ресурс | Количество
            | /downloads/product_1 | 28
            | /downloads/product_2 | 22
            |===
            ==== Коды ответа

            [options="header"]
            |===
            | Код | Имя | Количество
            | 304 | Not Modified | 31
            | 404 | Not Found | 16
            | 200 | OK | 3
            |===
            """, readOutput());
    }

    @Test
    @DisplayName("Передача некорректных параметров")
    void gettingWrongParams() {
        assertThatThrownBy(
            () -> new NginxLogAnalyzer(
                new String[] {
                    "--path", "src/test/resources/project3/manyGoodLogs.txt",
                    "--path", "src/test/resources/project3/goodLogs.txt",
                    "--wrong", "17/May/2015:09:05:18",
                    "--to", "17/May/2015:13:05:15",
                    "--format", "adoc"
                }
            ).analyse())
            .isInstanceOf(WrongInputLineException.class);
    }

    private static String readOutput() {
        StringBuilder sb = new StringBuilder();

        try (
            BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(new FileInputStream(ApplicationProperties.ADOC_WRITE_PATH))
            )) {
            while (bufferedReader.ready()) {
                sb.append(bufferedReader.readLine()).append("\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return sb.toString();
    }
}
