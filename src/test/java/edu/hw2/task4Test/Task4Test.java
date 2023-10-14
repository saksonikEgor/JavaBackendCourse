package edu.hw2.task4Test;

import edu.hw2.task4.Task4;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task4Test {
    @Test
    @DisplayName("Получение названия класса, вызвавшего метод")
    void getClassName() {
        assertEquals("edu.hw2.task4.Task4", Task4.callingInfo("callingInfo").className());
    }

    @Test
    @DisplayName("Получение сообщения о том, что данный метод никто не вызывал")
    void getNoOneCalledThisMethodMessage() {
        assertEquals(Task4.NO_ONE_CALLED_THIS_METHOD_MESSAGE, Task4.callingInfo("nonExistentMethod").className());
    }
}
