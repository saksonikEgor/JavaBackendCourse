package edu.hw11;

import edu.hw11.task3.Task3;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.lang.reflect.InvocationTargetException;
import java.util.function.Function;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task3Test {
    private static final String CUSTOM_CLASS_NAME = "FubCalculator";
    private static final String CUSTOM_METHOD_NAME = "fib";

    private static Stream<Arguments> provideRuntimeCreatingClassWithMethodFib() {
        return Stream.of(
            Arguments.of(1),
            Arguments.of(0),
            Arguments.of(25),
            Arguments.of(100)
        );
    }

    @ParameterizedTest
    @MethodSource("provideRuntimeCreatingClassWithMethodFib")
    @DisplayName("Создание класса с методом long fib(int n)")
    void runtimeCreatingClassWithMethodFib(int input)
        throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Class<?> customType = Task3.createCustomClassWithImplementedMethod(CUSTOM_CLASS_NAME, CUSTOM_METHOD_NAME);
        Object customInstance = customType.getDeclaredConstructor().newInstance();

        assertEquals(
            getFunction().apply(input),
            customType.getMethod(CUSTOM_METHOD_NAME, int.class).invoke(customInstance, input)
        );
    }

    private static Function<Integer, Long> getFunction() {
        return (n) -> {
            long prev = 0;
            long next = 1;

            for (int i = 0; i < n; i++) {
                long tmp = next;
                next = prev + next;
                prev = tmp;
            }

            return prev;
        };
    }
}
