package edu.hw5;

public class Task8 {
    public static final String NULL_POINTER_EXCEPTION_MESSAGE = "String \"s\" cant be null";
    private static final String ODD_LENGTH_PATTERN = "([0-1]{2})*[0-1]";
    private static final String STARTS_AT_0_AND_HAS_ODD_LENGTH_OR_STARTS_AT_1_AND_HAS_EVEN_LENGTH_PATTERN =
        "(0([0-1]{2})*|1([0-1]{2})*[0-1])";
    private static final String QUANTITY_0_IS_A_MULTIPLE_OF_3_PATTERN = "(1*01*01*01*)+";
    private static final String ANY_STRING_OTHER_THAN_11_OR_111_PATTERN = "(?!11$|111$)[0-1]*";
    private static final String EVERY_ODD_SYMBOL_IS_EQUAL_TO_1_PATTERN = "(10)*1";
    private static final String CONTAINS_AT_LEAST_TWO_0S_AND_AT_MOST_ONE_1S_PATTERN = "(1?0{2}0*|0+1?0+|0{2}0*1?)";
    private static final String NO_LEADING_1S_PATTERN = "(0+[0-1]*|0*)";

    private Task8() {
    }

    public static boolean isOddLength(String s) {
        if (s == null) {
            throw new NullPointerException(NULL_POINTER_EXCEPTION_MESSAGE);
        }

        return s.matches(ODD_LENGTH_PATTERN);
    }

    public static boolean isStartsAt0AndHasOddLengthOrStartsAt1AndHasEvenLength(String s) {
        if (s == null) {
            throw new NullPointerException(NULL_POINTER_EXCEPTION_MESSAGE);
        }

        return s.matches(STARTS_AT_0_AND_HAS_ODD_LENGTH_OR_STARTS_AT_1_AND_HAS_EVEN_LENGTH_PATTERN);
    }

    public static boolean isQuantity0IsAMultipleOf3(String s) {
        if (s == null) {
            throw new NullPointerException(NULL_POINTER_EXCEPTION_MESSAGE);
        }

        return s.matches(QUANTITY_0_IS_A_MULTIPLE_OF_3_PATTERN);
    }

    public static boolean isAnyStringOtherThan11Or111(String s) {
        if (s == null) {
            throw new NullPointerException(NULL_POINTER_EXCEPTION_MESSAGE);
        }

        return s.matches(ANY_STRING_OTHER_THAN_11_OR_111_PATTERN);
    }

    public static boolean isEveryOddSymbolIsEqualTo1(String s) {
        if (s == null) {
            throw new NullPointerException(NULL_POINTER_EXCEPTION_MESSAGE);
        }

        return s.matches(EVERY_ODD_SYMBOL_IS_EQUAL_TO_1_PATTERN);
    }

    public static boolean isContainsAtLeastTwo0sAndAtMostOne1s(String s) {
        if (s == null) {
            throw new NullPointerException(NULL_POINTER_EXCEPTION_MESSAGE);
        }

        return s.matches(CONTAINS_AT_LEAST_TWO_0S_AND_AT_MOST_ONE_1S_PATTERN);
    }

    public static boolean isContainsNoLeading1s(String s) {
        if (s == null) {
            throw new NullPointerException(NULL_POINTER_EXCEPTION_MESSAGE);
        }

        return s.matches(NO_LEADING_1S_PATTERN);
    }
}
