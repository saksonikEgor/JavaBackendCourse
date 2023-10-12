package edu.project1;

import edu.project1.util.ConsoleHangman;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;
import static com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOut;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ConsoleHangmanTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @Test
    void printHelloWord() throws Exception {
        ConsoleHangman consoleHangman = new ConsoleHangman();
        assertEquals(tapSystemOut(consoleHangman::run), "");
    }

//    void function() {
//        Scanner scanner = new Scanner(System.in);
//        String input = scanner.nextLine();
//        System.out.println(input + "!!!");
//    }
//
//    @BeforeEach
//    public void setUp() {
//        System.setOut(new PrintStream(outputStreamCaptor));
//    }
//
//    @Test
//    void functionTest() {
//        provideInput("Baeldung");
//
//        function();
//
//        assertEquals("Baeldung!!!\n", outputStreamCaptor.toString());
//    }
//
//    @AfterEach
//    public void tearDown() {
//        System.setOut(standardOut);
//    }

    void provideInput(String data) {
        ByteArrayInputStream testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

    @Test
    void someTest() {
        provideInput("a\nb\nc");

        ConsoleHangman consoleHangman = new ConsoleHangman();
        consoleHangman.run();

        assertEquals("Baeldung!!!\n", outputStreamCaptor.toString());
    }
}
