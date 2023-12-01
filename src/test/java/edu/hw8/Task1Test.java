package edu.hw8;

import edu.hw8.task1.client.PhraseClient;
import edu.hw8.task1.model.PhraseDictionary;
import edu.hw8.task1.server.PhraseServer;
import java.net.InetAddress;
import java.net.Socket;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task1Test {
    private static final int SERVER_PORT = 8088;

    @Test
    @DisplayName("Активация сервера")
    void serverGettingStarted() throws Exception {
        var server = new PhraseServer(SERVER_PORT);
        Thread serverThread = new Thread(server::start);
        serverThread.start();

        Thread.sleep(1000);

        Socket socket = new Socket(InetAddress.getLocalHost(), SERVER_PORT);
        assertTrue(socket.isConnected());

        server.close();
        serverThread.join();
    }

    private static Stream<Arguments> provideGettingPhraseByWord() {
        return Stream.of(
            Arguments.of(
                List.of("интеллект", "wrong"),
                List.of("интеллект", "интеллект", "интеллект", "интеллект", "интеллект", "интеллект"),
                List.of("глупый", "оскорбления", "интеллект", "личности"),
                List.of("личности", "оскорбления", "0-0-0-0-0-=0--00-")
            )
        );
    }

    @ParameterizedTest
    @MethodSource("provideGettingPhraseByWord")
    @DisplayName("Поулчение фразы по слову")
    void gettingPhraseByWord(List<String> inputList) throws Exception {
        var server = new PhraseServer(SERVER_PORT);
        Thread serverThread = new Thread(server::start);
        serverThread.start();

        Thread.sleep(1000);

        AtomicInteger differenceCount = new AtomicInteger();
        PhraseDictionary dict = new PhraseDictionary();
        List<Thread> clients = inputList.stream()
            .map(str -> new Thread(() -> {
                PhraseClient client = new PhraseClient();
                client.sendToServer(str);

                if (!Objects.equals(dict.getOrDefault(str, ""), client.readFromServer())) {
                    differenceCount.getAndIncrement();
                }
            }))
            .toList();

        clients.forEach(Thread::start);
        clients.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        server.close();
        serverThread.join();

        assertEquals(0, differenceCount.get());
    }

    @Test
    @DisplayName("Имитация обращения множества клиентов к серверу")
    void doesNotThrowing() {
        assertDoesNotThrow(
            () -> {
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
        );
    }
}
