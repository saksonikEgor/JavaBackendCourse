package edu.hw8.task1.client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PhraseClient implements AutoCloseable {
    private static final int PORT = 8088;
    private final Socket socket;
    private final BufferedWriter writer;
    private final BufferedReader reader;
    private final static Logger LOGGER = LogManager.getLogger();

    public PhraseClient() {
        try {
            socket = new Socket(InetAddress.getLocalHost(), PORT);

            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendToServer(String line) {
        try {
            writer.write(line);
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String readFromServer() {
        String message;
        try {
            message = reader.readLine();
            LOGGER.info("CLIENT | Received message from server " + message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return message;
    }

    @Override
    public void close() {
        try {
            socket.close();
            reader.close();
            writer.close();
        } catch (IOException e) {
            LOGGER.warn(e.getMessage());
        }
    }
}
