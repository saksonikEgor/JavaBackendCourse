package edu.hw6;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.util.Map;
import java.util.stream.IntStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Task6 {
    private static final int PORT_UPPER_BOUND = 1000;
    private static final String ILLEGAL_ARGUMENT_EXCEPTION_MESSAGE =
        "Port number must be in range [0, " + PORT_UPPER_BOUND + "]";
    private static final String PORT_IS_FREE_MESSAGE = "Free";
    private static final String PORT_IS_BUSY_MESSAGE = "Busy";
    private static final Logger LOGGER = LogManager.getLogger();

    public static void scanPorts() {
        LOGGER.info("Protocol\tPort\tStatus\tService");
        Map<Integer, String> populatPortsMap = initPopularPortsMap();

        IntStream.range(0, PORT_UPPER_BOUND + 1).forEach(port -> {
            LOGGER.info(
                port + "\t" + getPortStatus(port) + "\t" + populatPortsMap.getOrDefault(port, "")
            );
        });

//            LOGGER.info(new ObjectArrayMessage("first", "second", "value3"));
//        IntStream.range(0, PORT_UPPER_BOUND + 1).forEach(port -> {
//            LOGGER.info(
//                "UDP\t" + port + "\t" + getPortUDPStatus(port) + "\t" + populatPortsMap.getOrDefault(port, "")
//            );
//            LOGGER.info(
//                "TCP\t" + port + "\t" + getPortTCPStatus(port) + "\t" + populatPortsMap.getOrDefault(port, "")
//            );
//        });
    }

//    private static String getPortTCPStatus(int port) {
//        return isTCPListening(port) ? PORT_IS_BUSY_MESSAGE : PORT_IS_FREE_MESSAGE;
//    }
//
//    private static String getPortUDPStatus(int port) {
//        return isUDPListening(port) ? PORT_IS_BUSY_MESSAGE : PORT_IS_FREE_MESSAGE;
//    }
//
//    private static boolean isTCPListening(int port) {
//        try (ServerSocket serverSocket = new ServerSocket(port)) {
//            return true;
//        } catch (IOException e) {
//            return false;
//        }
//    }
//
//    private static boolean isUDPListening(int port) {
//        try (DatagramSocket datagramSocket = new DatagramSocket(port)) {
//            return true;
//        } catch (IOException e) {
//            return false;
//        }
//    }

    public static boolean isPortBusy(int port) {
        if (port < 0 || port > PORT_UPPER_BOUND) {
            throw new IllegalArgumentException(ILLEGAL_ARGUMENT_EXCEPTION_MESSAGE);
        }

        try (ServerSocket serverSocket = new ServerSocket(port);
             DatagramSocket datagramSocket = new DatagramSocket(port)) {
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    private static String getPortStatus(int port) {
        return isPortBusy(port) ? PORT_IS_BUSY_MESSAGE : PORT_IS_FREE_MESSAGE;
    }

    private static Map<Integer, String> initPopularPortsMap() {
        return Map.of(
            21, "FTP - File Transfer Protocol",
            22, "SSH - Secure Shell",
            25, "SMTP - Simple Mail Transfer Protocol",
            53, "DNS - Domain Name System",
            80, "HTTP - Hypertext Transfer Protocol",
            143, "IMAP - Internet Message Access Protocol",
            443, "HTTPS - HTTP Secure",
            993, "IMAP over SSL",
            995, "POP3 over SSL",
            27017, "MongoDB"
        );
    }
}
