package edu.hw8.task1.server;

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
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import edu.hw8.task1.model.PhraseDictionary;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PhraseServer implements AutoCloseable {
    private static final int PORT = 8088;
    private static final int THREAD_COUNT = 4;
    private static final int BYTE_BUFFER_CAPACITY = 1024;
    private Selector selector;
    private ServerSocketChannel serverSocketChannel;
    private final ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);
    private final PhraseDictionary phraseDictionary = new PhraseDictionary();
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
                    continue;
                }

                Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();

                while (keyIterator.hasNext()) {
                    SelectionKey key = keyIterator.next();

                    if (!key.isValid()) {
                        keyIterator.remove();
                        continue;
                    }
                    if (key.isAcceptable()) {
                        register(selector, serverSocketChannel);
                    }
                    if (key.isReadable()) {
                        executorService.submit(answer(ByteBuffer.allocate(BYTE_BUFFER_CAPACITY), key));
                    }
                }
            }
            LOGGER.info("SERVER -> closing");
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private Runnable answer(ByteBuffer buffer, SelectionKey key) throws IOException {
        return () -> {
            try {
                SocketChannel socketChannel = (SocketChannel) key.channel();
                int numBytesRead = socketChannel.read(buffer);

                while (numBytesRead != -1) {
                    buffer.flip();

                    while (buffer.hasRemaining()) {
                        socketChannel.write(getPhraseByWord(buffer));

                        LOGGER.info("SERVER -> answered to the client");
                    }

                    buffer.clear();
                    numBytesRead = socketChannel.read(buffer);
                }
                socketChannel.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };
    }

    private ByteBuffer getPhraseByWord(ByteBuffer buffer) {
        String answer = phraseDictionary.getOrDefault(
            StandardCharsets.UTF_8.decode(buffer).toString(),
            ""
        ) + "\n";

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
        selector.close();
        serverSocketChannel.close();
        executorService.shutdown();
    }
}
