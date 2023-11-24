package edu.hw8;

import edu.hw8.task1.client.PhraseClient;
import edu.hw8.task1.server.PhraseServer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Task1Test {
    @Test
    @DisplayName("")
    void gettingPhrase() throws InterruptedException {
        PhraseServer server = new PhraseServer();
        Thread serverThread = new Thread(server);

        PhraseClient client1 = new PhraseClient();
        PhraseClient client2 = new PhraseClient();

        Thread client1Thread = new Thread(() -> {
            System.out.println("личности -> " + client1.getPhrase("личности"));
            System.out.println("интеллект -> " + client1.getPhrase("интеллект"));
            System.out.println("личности -> " + client1.getPhrase("личности"));
            System.out.println("интеллект -> " + client1.getPhrase("интеллект"));
        });
        Thread client2Thread = new Thread(() -> {
            System.out.println("интеллект -> " + client2.getPhrase("интеллект"));
        });




        serverThread.start();
        Thread.sleep(1000);
        client1Thread.start();
        client2Thread.start();


        client1Thread.join();
        client2Thread.join();

        serverThread.join();
    }
}
