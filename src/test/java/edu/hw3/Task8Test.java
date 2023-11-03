package edu.hw3;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

public class Task8Test {
    private static Stream<Arguments> provideBackwardIterating() {
        return Stream.of(
            Arguments.of(
                new Task8.BackwardIterator<>(List.of("soup", "road", "zoo", "spring")),
                List.of("spring", "zoo", "road", "soup"),
                new ArrayList<String>()
            ),
            Arguments.of(
                new Task8.BackwardIterator<>(List.of(1, 2, 3, 4)),
                List.of(4, 3, 2, 1),
                new ArrayList<Integer>()
            ),
            Arguments.of(
                new Task8.BackwardIterator<>(List.of(1.8, 32.2, 3.1, 0.112)),
                List.of(0.112, 3.1, 32.2, 1.8),
                new ArrayList<Double>()
            ),
            Arguments.of(
                new Task8.BackwardIterator<>(new LinkedHashSet<>(List.of(1, 2, 3, 4))),
                List.of(4, 3, 2, 1),
                new ArrayList<Integer>()
            ),
            Arguments.of(
                new Task8.BackwardIterator<>(new TreeSet<>(List.of(1, -2, 3, 4, -5))),
                List.of(4, 3, 1, -2, -5),
                new ArrayList<Integer>()
            )
        );
    }

    @ParameterizedTest
    @MethodSource("provideBackwardIterating")
    @DisplayName("Получение обратного итератора")
    void getBackwardIterator(Task8.BackwardIterator iterator, List expect, ArrayList actual) {
        while (iterator.hasNext()) {
            actual.add(iterator.next());
        }
        assertThat(actual).containsExactly(expect.toArray());
    }
}
