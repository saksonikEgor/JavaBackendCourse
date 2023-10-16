package edu.hw3;

import java.util.Arrays;

public class Task5 {
    public static final String KEY_WORD_IS_NULL_EXCEPTION_MESSAGE = "String \"keyWord\" cant be null";
    public static final String NAME_IS_INVALID_EXCEPTION_MESSAGE = "String \"name\" is array \"names\" is invalid";
    public static final int MAXIMUM_NAME_LENGTH = 3;

    private Task5() {
    }

    public static Contact[] parseContacts(String[] names, String keyWord) {
        if (names == null) {
            return new Contact[] {};
        }
        if (keyWord == null) {
            throw new NullPointerException(KEY_WORD_IS_NULL_EXCEPTION_MESSAGE);
        }

        Contact[] contacts = new Contact[names.length];
        int idx = 0;
        for (String name : names) {
            if (!isNameValid(name)) {
                throw new IllegalArgumentException(NAME_IS_INVALID_EXCEPTION_MESSAGE);
            }
            contacts[idx++] = new Contact(name);
        }

        return switch (Key.valueOf(keyWord)) {
            case ASC -> ascendingSort(contacts);
            case DESC -> descendingSort(contacts);
        };
    }

    private static boolean isNameValid(String name) {
        if (name == null) {
            return false;
        }
        int wordCount = name.split(" ").length;
        return wordCount > 0 && wordCount < MAXIMUM_NAME_LENGTH;
    }

    private static Contact[] ascendingSort(Contact[] contacts) {
        Arrays.sort(contacts, (c1, c2) -> {
            String[] fullName1 = c1.name.split(" ");
            String[] fullName2 = c2.name.split(" ");

            return String.CASE_INSENSITIVE_ORDER.compare(
                fullName1[fullName1.length - 1],
                fullName2[fullName2.length - 1]
            );
        });
        return contacts;
    }

    private static Contact[] descendingSort(Contact[] contacts) {
        Arrays.sort(contacts, (c1, c2) -> {
            String[] fullName1 = c1.name.split(" ");
            String[] fullName2 = c2.name.split(" ");

            return String.CASE_INSENSITIVE_ORDER.compare(
                fullName2[fullName2.length - 1],
                fullName1[fullName1.length - 1]
            );
        });
        return contacts;
    }

    public record Contact(String name) {
    }

    public enum Key {
        ASC,
        DESC
    }
}
