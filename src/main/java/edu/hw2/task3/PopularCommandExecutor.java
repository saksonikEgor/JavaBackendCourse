package edu.hw2.task3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PopularCommandExecutor {
    private static final String ALL_EXECUTIONS_FAILED_MESSAGE = "All executions failed";
    private static final String FAILED_UPDATE_PACKAGES = "Failed update packages";
    private static final String EXECUTE_COMMAND = "apt update && apt upgrade -y";
    private static final Logger LOGGER = LogManager.getLogger();
    private final ConnectionManager manager;
    private final int maxAttempts;

    public PopularCommandExecutor(ConnectionManager manager, int maxAttempts) {
        this.manager = manager;
        this.maxAttempts = maxAttempts;
    }

    public void updatePackages() {
        try {
            tryExecute(EXECUTE_COMMAND);
        } catch (ConnectionException e) {
            LOGGER.error(FAILED_UPDATE_PACKAGES, e);
        }
    }

    public void tryExecute(String command) {
        Connection connection = manager.getConnection();
        Exception cause = null;

        int attempt = 0;
        while (attempt < maxAttempts) {
            try (connection) {
                connection.execute(command);
                return;
            } catch (Exception e) {
                cause = e;
                attempt++;
            }
        }
        throw new ConnectionException(ALL_EXECUTIONS_FAILED_MESSAGE, cause);
    }
}
