package edu.hw8;

import edu.hw8.task1.client.PhraseClient;
import edu.hw8.task1.server.PhraseServer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Task1Test {
    @Test
    @DisplayName("")
    void gettingPhrase() throws Exception {
        PhraseServer server = new PhraseServer();
        Thread serverThread = new Thread(server::start);


        Thread client1Thread = new Thread(() -> {
            PhraseClient client1 = new PhraseClient();

            client1.sendToServer("интеллект");
            System.out.println(client1.readFromServer());

            client1.sendToServer("личности");
            System.out.println(client1.readFromServer());

            client1.sendToServer("интеллект");
            System.out.println(client1.readFromServer());
        });
//        Thread client2Thread = new Thread(() -> {
//            PhraseClient client2 = new PhraseClient();
//
//            client2.sendToServer("личности");
//            System.out.println(client2.readFromServer());
//        });
        Thread client2Thread = new Thread(() -> {
            PhraseClient client1 = new PhraseClient();

            client1.sendToServer("интеллект");
            System.out.println(client1.readFromServer());

            client1.sendToServer("личности");
            System.out.println(client1.readFromServer());

            client1.sendToServer("интеллект");
            System.out.println(client1.readFromServer());
        });
        Thread client3Thread = new Thread(() -> {
            PhraseClient client1 = new PhraseClient();

            client1.sendToServer("интеллект");
            System.out.println(client1.readFromServer());

            client1.sendToServer("личности");
            System.out.println(client1.readFromServer());

            client1.sendToServer("интеллект");
            System.out.println(client1.readFromServer());
        });
        Thread client4Thread = new Thread(() -> {
            PhraseClient client1 = new PhraseClient();

            client1.sendToServer("интеллект");
            System.out.println(client1.readFromServer());

            client1.sendToServer("личности");
            System.out.println(client1.readFromServer());

            client1.sendToServer("интеллект");
            System.out.println(client1.readFromServer());
        });


        serverThread.start();
        Thread.sleep(1000);
        client1Thread.start();
        client2Thread.start();
        client3Thread.start();
        client4Thread.start();


        client1Thread.join();
        client2Thread.join();
        client3Thread.join();
        client4Thread.join();

        server.close();
        serverThread.join();
    }
}
