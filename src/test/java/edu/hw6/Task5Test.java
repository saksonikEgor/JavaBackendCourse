package edu.hw6;

import edu.hw6.Task5.HackerNews;
import java.io.IOException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task5Test {
    private static final String WRONG_URI = "https://hacker-news.firebaseio.com/v0/topstoriesWRONG.json";

    @DisplayName("Получение самых обсуждаемых статей")
    @Test
    void gettingTopNews() {
        try {
            assertTrue(HackerNews.hackerNewsTopStories().length > 0);
            assertEquals(0, HackerNews.hackerNewsTopStories(WRONG_URI).length);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @DisplayName("Получение статьи по id")
    @Test
    void gettingNewById() {
        try {
            assertEquals("JDK 21 Release Notes", HackerNews.news(37570037));
        } catch (InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }

        assertThatThrownBy(() -> HackerNews.news(-1001))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining(HackerNews.ILLEGAL_ARGUMENT_EXCEPTION);
    }
}
