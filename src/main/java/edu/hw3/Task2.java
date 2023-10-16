package edu.hw3;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Task2 {
    public static final String NULL_POINTER_EXCEPTION_MESSAGE = "String \"str\" cant be null";
    public static final String ILLEGAL_ARGUMENT_EXCEPTION_MESSAGE = "String \"str\" is invalid";

    private Task2() {
    }

    public static String[] clusterize(String str) {
        if (str == null) {
            throw new NullPointerException(NULL_POINTER_EXCEPTION_MESSAGE);
        }
        if (str.isEmpty()) {
            return new String[] {""};
        }
        if (!isValidParentheses(str)) {
            throw new IllegalArgumentException(ILLEGAL_ARGUMENT_EXCEPTION_MESSAGE);
        }

        Deque<Character> stack = new ArrayDeque<>();
        List<String> result = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        for (char c : str.toCharArray()) {
            switch (c) {
                case ')', ']', '}' -> {
                    stack.poll();
                    sb.append(c);
                    if (stack.isEmpty()) {
                        result.add(sb.toString());
                        sb = new StringBuilder();
                    }
                }
                default -> {
                    sb.append(c);
                    stack.push(c);
                }
            }
        }
        return result.toArray(new String[0]);
    }

    private static boolean isValidParentheses(String str) {
        Deque<Character> stack = new ArrayDeque<>();

        for (char c : str.toCharArray()) {
            switch (c) {
                case ')' -> {
                    Character polled = stack.poll();
                    if (polled == null || polled != '(') {
                        return false;
                    }
                }
                case '}' -> {
                    Character polled = stack.poll();
                    if (polled == null || polled != '{') {
                        return false;
                    }
                }
                case ']' -> {
                    Character polled = stack.poll();
                    if (polled == null || polled != '[') {
                        return false;
                    }
                }
                default -> stack.push(c);
            }
        }
        return stack.isEmpty();
    }

}
