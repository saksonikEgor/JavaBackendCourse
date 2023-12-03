package edu.hw8.task3.solving;

import edu.hw8.task3.configuration.Params;
import edu.hw8.task3.utils.BruteForceUtils;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import org.apache.commons.codec.digest.DigestUtils;


public class SingleThreadingPasswordBruteForcer implements PasswordBruteForcer {
    private final Function<String, String> hashFunction = DigestUtils::md5Hex;

    @Override
    public Map<String, String> bruteForce(Map<String, String> hashToLogin) {
        Map<String, String> loginToPassword = new HashMap<>();

        for (int length = 1; length <= Params.PASSWORD_MAX_LENGTH; length++) {
            long passwordCount = (long) Math.pow(Params.ALPHABET.length, length);

            for (int idx = 0; idx < passwordCount; idx++) {
                String password = BruteForceUtils.nextPassword(idx, length);
                String hash = hashFunction.apply(password).toLowerCase();
                String login = hashToLogin.remove(hash);

                if (login != null) {
                    loginToPassword.put(login, password);

                    if (hashToLogin.isEmpty()) {
                        return loginToPassword;
                    }
                }
            }
        }

        return loginToPassword;
    }
}
