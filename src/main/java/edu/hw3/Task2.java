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

        Deque<Character> stack = new ArrayDeque<>();
        Deque<Character> validationStack = new ArrayDeque<>();
        List<String> result = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        for (char c : str.toCharArray()) {
            if (c == ')') {
                Character polled = validationStack.poll();
                if (polled == null || polled != '(') {
                    throw new IllegalArgumentException(ILLEGAL_ARGUMENT_EXCEPTION_MESSAGE);
                }
                stack.poll();
                sb.append(c);

                if (stack.isEmpty()) {
                    result.add(sb.toString());
                    sb.setLength(0);
                }
            } else {
                validationStack.push(c);
                sb.append(c);
                stack.push(c);
            }
        }

        if (!validationStack.isEmpty()) {
            throw new IllegalArgumentException(ILLEGAL_ARGUMENT_EXCEPTION_MESSAGE);
        }

        return result.toArray(new String[0]);
    }
}
