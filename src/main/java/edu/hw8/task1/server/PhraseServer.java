package edu.hw8.task1.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PhraseServer implements AutoCloseable {
    private static final int PORT = 8088;
    private static final int THREAD_COUNT = 4;
    private static final int BYTE_BUFFER_CAPACITY = 1024;
    private Selector selector;
    private ServerSocketChannel serverSocketChannel;
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
    private final static Logger LOGGER = LogManager.getLogger();

    public PhraseServer() {
    }

    public void start() {
        try {
            selector = Selector.open();
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress(InetAddress.getLocalHost(), PORT));
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            LOGGER.info("SERVER -> started");

            while (selector.isOpen()) {
                selector.select();

                if (!selector.isOpen()) {
                    return;
                }

                Set<SelectionKey> selectedKeys = selector.selectedKeys();
                Iterator<SelectionKey> iter = selectedKeys.iterator();

                while (iter.hasNext()) {
                    SelectionKey key = iter.next();

                    if (!key.isValid()) {
                        iter.remove();
                        continue;
                    }
                    if (key.isAcceptable()) {
                        register(selector, serverSocketChannel);
                    }
                    if (key.isReadable()) {
                        this.executorService.submit(
                            () -> {
                                try {
                                    answer(ByteBuffer.allocate(BYTE_BUFFER_CAPACITY), key);
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        );
                    }
                }
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void answer(ByteBuffer buffer, SelectionKey key) throws IOException {
        SocketChannel socketChannel = (SocketChannel) key.channel();

        int numBytesRead = socketChannel.read(buffer);
        while (numBytesRead != -1) {
            buffer.flip();
            while (buffer.hasRemaining()) {
                ByteBuffer result = getResponseByHandler(buffer);

                socketChannel.write(result);

                LOGGER.info("SERVER -> answered to the client");
            }
            buffer.clear();

            numBytesRead = socketChannel.read(buffer);
        }

        socketChannel.close();
    }

    private ByteBuffer getResponseByHandler(ByteBuffer buffer) {
        String message = StandardCharsets.UTF_8.decode(buffer).toString();
        String answer = WORD_TO_PHRASE.getOrDefault(message, "") + "\n";

        return ByteBuffer.wrap(answer.getBytes(StandardCharsets.UTF_8));
    }

    private void register(Selector selector, ServerSocketChannel serverSocket)
        throws IOException, InterruptedException {
        SocketChannel socketChannel = serverSocket.accept();

        if (socketChannel != null) {

            socketChannel.configureBlocking(false);
            socketChannel.register(selector, SelectionKey.OP_READ);

            LOGGER.info("SERVER -> registered a new client");
        }
    }

    @Override
    public void close() throws Exception {
        serverSocketChannel.close();
        selector.close();
    }
}
