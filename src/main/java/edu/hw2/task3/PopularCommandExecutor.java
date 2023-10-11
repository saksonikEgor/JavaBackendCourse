package edu.hw2.task3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PopularCommandExecutor {
    private final static String FAILED_EXECUTE_MASSAGE = "All executions failed";
    private final static String FAILED_UPDATE_PACKAGES = "Failed update packages";
    private final ConnectionManager manager;
    private final int maxAttempts;
    private final static Logger LOGGER = LogManager.getLogger();

    public PopularCommandExecutor(ConnectionManager manager, int maxAttempts) {
        this.manager = manager;
        this.maxAttempts = maxAttempts;
    }

    public void updatePackages() {
        try {
            tryExecute("apt update && apt upgrade -y");
        } catch (ConnectionException e) {
            LOGGER.error(FAILED_UPDATE_PACKAGES, e);
        }
    }

    private void tryExecute(String command) {
        Connection connection = manager.getConnection();
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
        throw (ConnectionException) new ConnectionException(FAILED_EXECUTE_MASSAGE).initCause(exception);
    }
}
