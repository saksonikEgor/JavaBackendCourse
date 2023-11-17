package edu.hw6.Task5;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HackerNews {
    private static final String TOP_STORIES_URI = "https://hacker-news.firebaseio.com/v0/topstories.json";
    private static final String NEW_BY_ID_URI_PREFIX = "https://hacker-news.firebaseio.com/v0/item/";
    private static final String JSON_SUFFIX = ".json";
    private static final String NEW_NAME_PATTERN = "\"title\":\"(.*?)\"";
    private static final String ARRAY_SEPARATORS = ",";
    public static final String ILLEGAL_ARGUMENT_EXCEPTION = "Wrong input. No match for current id";

    private HackerNews() {
    }

    public static long[] hackerNewsTopStories(String uri) throws InterruptedException {
        return hackerNewsTopStoriesFromURI(uri);
    }

    public static long[] hackerNewsTopStories() throws InterruptedException {
        return hackerNewsTopStoriesFromURI(TOP_STORIES_URI);
    }

    private static long[] hackerNewsTopStoriesFromURI(String uri) throws InterruptedException {
        try (HttpClient client = HttpClient.newHttpClient()) {

            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .build();

            String response = client.send(request, HttpResponse.BodyHandlers.ofString()).body();

            return Arrays.stream(response.substring(1, response.length() - 1).split(ARRAY_SEPARATORS))
                .mapToLong(Long::parseLong)
                .toArray();
        } catch (IOException | NumberFormatException e) {
            return new long[] {};
        }
    }

    public static String news(long id) throws InterruptedException, IOException {
        try (HttpClient client = HttpClient.newHttpClient()) {

            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(NEW_BY_ID_URI_PREFIX + id + JSON_SUFFIX))
                .build();

            String response = client.send(request, HttpResponse.BodyHandlers.ofString()).body();

            Matcher matcher = Pattern.compile(NEW_NAME_PATTERN).matcher(response);
            if (!matcher.find()) {
                throw new IllegalArgumentException(ILLEGAL_ARGUMENT_EXCEPTION);
            }
            return matcher.group(1);
        }
    }
}
