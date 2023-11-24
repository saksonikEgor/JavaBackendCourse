package edu.hw8.task1.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class PhraseClient {
    private static final int PORT = 8088;
    private final Socket socket;

    public PhraseClient() {
        try {
            socket = new Socket(InetAddress.getLocalHost(), PORT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getPhrase(String word) {
        try  {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            out.println(word);
            out.flush();

            return in.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
