package edu.hw8.task1.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PhraseServer extends Thread{
    private static final int THREAD_COUNT = 1;
    private static final int PORT = 8088;
    final ServerSocket serverSocket;
    private final ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);
    private static final ConcurrentHashMap<String, String> WORD_TO_PHRASE = new ConcurrentHashMap<>(
        Map.of(
            "личности",
            "Не переходи на личности там, где их нет",
            "оскорбления",
            "Если твои противники перешли на личные оскорбления, будь уверена — твоя победа не за горами",
            "глупый",
            "А я тебе говорил, что ты глупый? Так вот, я забираю свои слова обратно... Ты просто бог идиотизма.",
            "интеллект",
            "Чем ниже интеллект, тем громче оскорбления"
        )
    );

    public PhraseServer() {
        try {
            serverSocket = new ServerSocket(PORT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static class Worker extends Thread {
        final Socket socket;

        Worker(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String inputLine = in.readLine();

                System.out.println("обработка клиента с сообщением = " + inputLine + ", в потоке -> " +
                    Thread.currentThread().getName());

                out.println(WORD_TO_PHRASE.get(inputLine));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                executorService.submit(new Worker(serverSocket.accept()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void shutdown() {
        executorService.shutdown();
        try {
            serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException("Error while closing the Server Socket", e);
        }
    }
}
