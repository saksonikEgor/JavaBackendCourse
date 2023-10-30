package edu.hw3;

import java.util.Arrays;

public class Task5 {
    public static final String KEY_WORD_IS_NULL_EXCEPTION_MESSAGE = "String \"keyWord\" cant be null";
    public static final String FULLNAME_IS_INVALID_EXCEPTION_MESSAGE = "String \"name\" is array \"names\" is invalid";
    public static final int MAXIMUM_FULLNAME_LENGTH = 2;

    private Task5() {
    }

    public static Contact[] parseContacts(String[] fullNames, String keyWord) {
        if (fullNames == null) {
            return new Contact[] {};
        }
        if (keyWord == null) {
            throw new NullPointerException(KEY_WORD_IS_NULL_EXCEPTION_MESSAGE);
        }

        Contact[] contacts = new Contact[fullNames.length];
        int idx = 0;
        for (String fullName : fullNames) {
            if (!isFullNameValid(fullName)) {
                throw new IllegalArgumentException(FULLNAME_IS_INVALID_EXCEPTION_MESSAGE);
            }
            String[] words = fullName.split(" ");
            contacts[idx++] = new Contact(words[0], words[1]);
        }

        return switch (Key.valueOf(keyWord)) {
            case ASC -> ascendingSort(contacts);
            case DESC -> descendingSort(contacts);
        };
    }

    private static boolean isFullNameValid(String fullName) {
        if (fullName == null) {
            return false;
        }
        int wordCount = fullName.split(" ").length;
        return wordCount > 0 && wordCount <= MAXIMUM_FULLNAME_LENGTH;
    }

    private static Contact[] ascendingSort(Contact[] contacts) {
        return Arrays.stream(contacts)
            .sorted((o1, o2) -> String.CASE_INSENSITIVE_ORDER.compare(o1.surname, o2.surname))
            .toList().toArray(new Contact[] {});
    }

    private static Contact[] descendingSort(Contact[] contacts) {
        return Arrays.stream(contacts)
            .sorted((o1, o2) -> String.CASE_INSENSITIVE_ORDER.compare(o2.surname, o1.surname))
            .toList().toArray(new Contact[] {});
    }

    public enum Key {
        ASC,
        DESC
    }

    public record Contact(String name, String surname) {
    }
}
