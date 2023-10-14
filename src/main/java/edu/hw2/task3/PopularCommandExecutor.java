package edu.hw2.task3;

import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PopularCommandExecutor {
    private static final String ALL_EXECUTIONS_FAILED_MESSAGE = "All executions failed";
    private static final String FAULTY_EXECUTE_MESSAGE = "Faulty execute";
    private static final String FAILED_UPDATE_PACKAGES = "Failed update packages";
    private static final String EXECUTE_COMMAND = "apt update && apt upgrade -y";
    private static final int CONNECTION_UPPER_BOUND = 2;
    private static final Logger LOGGER = LogManager.getLogger();
    private final ConnectionManager manager;
    private final int maxAttempts;
    private final Random connectionRandom;

    public PopularCommandExecutor(ConnectionManager manager, int maxAttempts, Random connectionRandom) {
        this.manager = manager;
        this.maxAttempts = maxAttempts;
        this.connectionRandom = connectionRandom;
    }

    public void updatePackages() {
        try {
            tryExecute(EXECUTE_COMMAND);
        } catch (ConnectionException e) {
            LOGGER.error(FAILED_UPDATE_PACKAGES, e);
        }
    }

    public void tryExecute(String command) {
        Connection connection = manager.getConnection(CONNECTION_UPPER_BOUND, FAULTY_EXECUTE_MESSAGE, connectionRandom);
        ConnectionException exception = null;

        int attempt = 0;
        while (attempt < maxAttempts) {
            try (connection) {
                connection.execute(command);
                return;
            } catch (ConnectionException e) {
                exception = e;
                attempt++;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        throw (ConnectionException) new ConnectionException(ALL_EXECUTIONS_FAILED_MESSAGE).initCause(exception);
    }
}
