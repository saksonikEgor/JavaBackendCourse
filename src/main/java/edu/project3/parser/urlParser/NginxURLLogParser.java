package edu.project3.parser.urlParser;

import edu.project3.model.NginxLogRecord;
import edu.project3.parser.LogParser;
import edu.project3.util.ParserUtils;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class NginxURLLogParser implements LogParser {
    private final URI uri;

    public NginxURLLogParser(URI uri) {
        this.uri = uri;
    }

    @Override
    public List<NginxLogRecord> parseLogs() {
        HttpRequest request = HttpRequest
            .newBuilder()
            .uri(uri)
            .GET()
            .build();

        try (HttpClient client = HttpClient.newBuilder().build()) {
            return client.send(request, HttpResponse.BodyHandlers.ofLines())
                .body()
                .map(ParserUtils::parseLog)
                .toList();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Error while reading a file by uri: " + uri.toString(), e);
        }
    }
}
