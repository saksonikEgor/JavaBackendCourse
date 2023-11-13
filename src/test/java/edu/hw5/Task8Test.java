package edu.hw5;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task8Test {
    private static Stream<Arguments> provideIsOddLength() {
        return Stream.of(
            Arguments.of(true, "010"),
            Arguments.of(true, "000"),
            Arguments.of(true, "110"),
            Arguments.of(false, "1310"),
            Arguments.of(false, "13e10"),
            Arguments.of(false, "1w0"),
            Arguments.of(false, "10"),
            Arguments.of(false, ""),
            Arguments.of(true, "0"),
            Arguments.of(true, "1")
        );
    }

    private static Stream<Arguments> provideIsStartsAt0AndHasOddLengthOrStartsAt1AndHasEvenLength() {
        return Stream.of(
            Arguments.of(true, "010"),
            Arguments.of(true, "000"),
            Arguments.of(false, "00"),
            Arguments.of(false, "0010"),
            Arguments.of(false, "13e10"),
            Arguments.of(false, "1w0"),
            Arguments.of(true, "10"),
            Arguments.of(false, ""),
            Arguments.of(true, "0"),
            Arguments.of(true, "1110")
        );
    }

    private static Stream<Arguments> provideIsQuantity0IsAMultipleOf3() {
        return Stream.of(
            Arguments.of(false, "010"),
            Arguments.of(true, "000"),
            Arguments.of(false, "110"),
            Arguments.of(false, "1310"),
            Arguments.of(false, "13e10"),
            Arguments.of(false, "1w0"),
            Arguments.of(false, "10"),
            Arguments.of(false, ""),
            Arguments.of(false, "0"),
            Arguments.of(true, "000000000"),
            Arguments.of(true, "000000"),
            Arguments.of(false, "1")
        );
    }

    private static Stream<Arguments> provideIsAnyStringOtherThan11Or111() {
        return Stream.of(
            Arguments.of(true, "010"),
            Arguments.of(true, "000"),
            Arguments.of(true, "110"),
            Arguments.of(false, "1310"),
            Arguments.of(false, "13e10"),
            Arguments.of(false, "1w0"),
            Arguments.of(true, "10"),
            Arguments.of(false, "11"),
            Arguments.of(false, "111"),
            Arguments.of(true, "1111"),
            Arguments.of(true, ""),
            Arguments.of(true, "0"),
            Arguments.of(true, "1")
        );
    }

    private static Stream<Arguments> provideIsEveryOddSymbolIsEqualTo1() {
        return Stream.of(
            Arguments.of(false, "010"),
            Arguments.of(false, "000"),
            Arguments.of(false, "110"),
            Arguments.of(false, "1310"),
            Arguments.of(false, "13e10"),
            Arguments.of(false, "1w0"),
            Arguments.of(false, "10"),
            Arguments.of(false, ""),
            Arguments.of(true, "10101"),
            Arguments.of(true, "1010101010101010101"),
            Arguments.of(true, "101"),
            Arguments.of(false, "0"),
            Arguments.of(true, "1")
        );
    }

    private static Stream<Arguments> provideIsContainsAtLeastTwo0sAndAtMostOne1s() {
        return Stream.of(
            Arguments.of(true, "010"),
            Arguments.of(true, "000"),
            Arguments.of(false, "110"),
            Arguments.of(false, "1310"),
            Arguments.of(false, "13e10"),
            Arguments.of(false, "1w0"),
            Arguments.of(false, "10"),
            Arguments.of(false, ""),
            Arguments.of(true, "0001"),
            Arguments.of(true, "0001000"),
            Arguments.of(true, "1000"),
            Arguments.of(true, "010"),
            Arguments.of(false, "0"),
            Arguments.of(false, "1")
        );
    }

    private static Stream<Arguments> provideIsContainsNoLeading1s() {
        return Stream.of(
            Arguments.of(true, "010"),
            Arguments.of(true, "000"),
            Arguments.of(false, "110"),
            Arguments.of(false, "1310"),
            Arguments.of(false, "13e10"),
            Arguments.of(false, "1w0"),
            Arguments.of(false, "10"),
            Arguments.of(true, ""),
            Arguments.of(true, "0101011"),
            Arguments.of(true, "01110111"),
            Arguments.of(false, "10"),
            Arguments.of(false, "11110010"),
            Arguments.of(true, "0"),
            Arguments.of(false, "1")
        );
    }

    @ParameterizedTest
    @DisplayName("Проверка что строка нечетной длины")
    @MethodSource("provideIsOddLength")
    void isOddLength(boolean expected, String input) {
        assertEquals(expected, Task8.isOddLength(input));

        assertThatThrownBy(() -> Task8.isOddLength(null))
            .isInstanceOf(NullPointerException.class)
            .hasMessageContaining(Task8.NULL_POINTER_EXCEPTION_MESSAGE);
    }

    @ParameterizedTest
    @DisplayName("Проверка что строка начинается с 0 и имеет нечетную длину, или начинается с 1 и имеет четную длину")
    @MethodSource("provideIsStartsAt0AndHasOddLengthOrStartsAt1AndHasEvenLength")
    void isStartsAt0AndHasOddLengthOrStartsAt1AndHasEvenLength(boolean expected, String input) {
        assertEquals(expected, Task8.isStartsAt0AndHasOddLengthOrStartsAt1AndHasEvenLength(input));

        assertThatThrownBy(() -> Task8.isStartsAt0AndHasOddLengthOrStartsAt1AndHasEvenLength(null))
            .isInstanceOf(NullPointerException.class)
            .hasMessageContaining(Task8.NULL_POINTER_EXCEPTION_MESSAGE);
    }

    @ParameterizedTest
    @DisplayName("Проверка строки что количество 0 кратно 3")
    @MethodSource("provideIsQuantity0IsAMultipleOf3")
    void isQuantity0IsAMultipleOf3(boolean expected, String input) {
        assertEquals(expected, Task8.isQuantity0IsAMultipleOf3(input));

        assertThatThrownBy(() -> Task8.isQuantity0IsAMultipleOf3(null))
            .isInstanceOf(NullPointerException.class)
            .hasMessageContaining(Task8.NULL_POINTER_EXCEPTION_MESSAGE);
    }

    @ParameterizedTest
    @DisplayName("Проверка строки на несовпадение с 11 и 111")
    @MethodSource("provideIsAnyStringOtherThan11Or111")
    void isAnyStringOtherThan11Or111(boolean expected, String input) {
        assertEquals(expected, Task8.isAnyStringOtherThan11Or111(input));

        assertThatThrownBy(() -> Task8.isAnyStringOtherThan11Or111(null))
            .isInstanceOf(NullPointerException.class)
            .hasMessageContaining(Task8.NULL_POINTER_EXCEPTION_MESSAGE);
    }

    @ParameterizedTest
    @DisplayName("Проверка строки что каждый нечетный символ в ней равен 1")
    @MethodSource("provideIsEveryOddSymbolIsEqualTo1")
    void isEveryOddSymbolIsEqualTo1(boolean expected, String input) {
        assertEquals(expected, Task8.isEveryOddSymbolIsEqualTo1(input));

        assertThatThrownBy(() -> Task8.isEveryOddSymbolIsEqualTo1(null))
            .isInstanceOf(NullPointerException.class)
            .hasMessageContaining(Task8.NULL_POINTER_EXCEPTION_MESSAGE);
    }

    @ParameterizedTest
    @DisplayName("Проверка строки что она содержит не менее двух 0 и не более одной 1")
    @MethodSource("provideIsContainsAtLeastTwo0sAndAtMostOne1s")
    void isContainsAtLeastTwo0sAndAtMostOne1s(boolean expected, String input) {
        assertEquals(expected, Task8.isContainsAtLeastTwo0sAndAtMostOne1s(input));

        assertThatThrownBy(() -> Task8.isContainsAtLeastTwo0sAndAtMostOne1s(null))
            .isInstanceOf(NullPointerException.class)
            .hasMessageContaining(Task8.NULL_POINTER_EXCEPTION_MESSAGE);
    }

    @ParameterizedTest
    @DisplayName("Проверка строки на отсутствие последовательных 1")
    @MethodSource("provideIsContainsNoLeading1s")
    void isContainsNoLeading1s(boolean expected, String input) {
        assertEquals(expected, Task8.isContainsNoLeading1s(input));

        assertThatThrownBy(() -> Task8.isContainsNoLeading1s(null))
            .isInstanceOf(NullPointerException.class)
            .hasMessageContaining(Task8.NULL_POINTER_EXCEPTION_MESSAGE);
    }
}
