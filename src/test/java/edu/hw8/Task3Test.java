package edu.hw8;

import edu.hw8.task3.solving.MultiThreadingPasswordBruteForcer;
import edu.hw8.task3.solving.PasswordBruteForcer;
import edu.hw8.task3.solving.SingleThreadingPasswordBruteForcer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task3Test {
    private static Stream<Arguments> provideBruteForcing() {
        return Stream.of(
            Arguments.of(
                List.of(
                    new UserInfo("Tom", "h84"), new UserInfo("Jane", "hcr1"),
                    new UserInfo("Will", "1"), new UserInfo("Kate", "873")
                )
            )
        );
    }

    @ParameterizedTest
    @MethodSource("provideBruteForcing")
    @DisplayName("Однопоточный перебор паролей")
    void singleThreadingBruteForcing(List<UserInfo> users) {
        PasswordBruteForcer bruteForcer = new SingleThreadingPasswordBruteForcer();

        assertEquals(
            Map.ofEntries(
                users.stream()
                    .map(user -> Map.entry(user.login(), user.password()))
                    .toArray(Map.Entry[]::new)
            ),
            bruteForcer.bruteForce(new HashMap<>(Map.ofEntries(
                users.stream()
                    .map(user -> Map.entry(DigestUtils.md5Hex(user.password()), user.login()))
                    .toArray(Map.Entry[]::new)
            )))
        );
    }

    @ParameterizedTest
    @MethodSource("provideBruteForcing")
    @DisplayName("Многопоточный перебор паролей")
    void multiThreadingBruteForcing(List<UserInfo> users) {
        PasswordBruteForcer bruteForcer = new MultiThreadingPasswordBruteForcer(3);

        assertEquals(
            Map.ofEntries(
                users.stream()
                    .map(user -> Map.entry(user.login(), user.password()))
                    .toArray(Map.Entry[]::new)
            ),
            bruteForcer.bruteForce(new HashMap<>(Map.ofEntries(
                users.stream()
                    .map(user -> Map.entry(DigestUtils.md5Hex(user.password()), user.login()))
                    .toArray(Map.Entry[]::new)
            )))
        );
    }

    private record UserInfo(String login, String password) {
    }
}
