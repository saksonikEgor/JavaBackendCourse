package edu.hw5;

import java.time.Duration;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task1Test {
    private static Stream<Arguments> provideCalculateAvg() {
        return Stream.of(
            Arguments.of("03:40", """
                2022-03-12, 20:20 - 2022-03-12, 23:50
                2022-04-01, 21:30 - 2022-04-02, 01:20"""),
            Arguments.of("07:25", """
                2022-03-12, 20:20 - 2022-03-12, 23:50
                2022-04-01, 21:30 - 2022-04-02, 01:20
                2022-04-11, 22:20 - 2022-04-11, 23:50
                2022-10-01, 11:30 - 2022-10-02, 18:20
                2022-03-22, 20:20 - 2022-03-22, 20:25
                2022-06-01, 21:30 - 2022-06-02, 02:20"""),
            Arguments.of("00:20", "2022-03-12, 20:20 - 2022-03-12, 20:40"),
            Arguments.of("00:00", "1111-11-11, 11:11 - 1111-11-11, 11:11")
        );

    }

    @ParameterizedTest
    @DisplayName("Подсчет среднего времени")
    @MethodSource("provideCalculateAvg")
    void calculateAvg(String expected, String input) {
        Duration avg = Task1.calculateAvg(input);
        assertEquals(expected, String.format("%02d:%02d", avg.toHours(), avg.toMinutesPart()));

        assertThatThrownBy(() -> Task1.calculateAvg(null))
            .isInstanceOf(NullPointerException.class)
            .hasMessageContaining(Task1.NULL_POINTER_EXCEPTION_MESSAGE);
    }
}
