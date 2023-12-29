package edu.hw8.task3.solving;

import java.util.Map;

public interface PasswordBruteForcer {
    Map<String, String> bruteForce(Map<String, String> hashToLogin);
}
