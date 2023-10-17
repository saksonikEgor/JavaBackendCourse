package edu.hw3;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Task8Test {
    @Test
    @DisplayName("Получение обратного итератора")
    void getBackwardIterator() {
        Task8.BackwardIterator<String> stringIterator =
            new Task8.BackwardIterator<>(List.of("soup", "road", "zoo", "spring"));
        List<String> reversedStringList = new ArrayList<>();
        while (stringIterator.hasNext()) {
            reversedStringList.add(stringIterator.next());
        }
        assertThat(reversedStringList).containsExactly("spring", "zoo", "road", "soup");

        Task8.BackwardIterator<String> integersIterator =
            new Task8.BackwardIterator<>(List.of("1", "2", "3", "4"));
        List<String> reversedIntegerList = new ArrayList<>();
        while (integersIterator.hasNext()) {
            reversedIntegerList.add(integersIterator.next());
        }
        assertThat(reversedIntegerList).containsExactly("4", "3", "2", "1");

        Task8.BackwardIterator<String> floatsIterator =
            new Task8.BackwardIterator<>(List.of("1.8", "32.2", "3.1", "0.112"));
        List<String> reversedFloatList = new ArrayList<>();
        while (floatsIterator.hasNext()) {
            reversedFloatList.add(floatsIterator.next());
        }
        assertThat(reversedFloatList).containsExactly("0.112", "3.1", "32.2", "1.8");
    }
}
