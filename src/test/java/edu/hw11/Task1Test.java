package edu.hw11;

import edu.hw11.task1.Task1;
import java.lang.reflect.InvocationTargetException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task1Test {
    @Test
    @DisplayName("Создание класса с переопределенным методом toString()")
    void runtimeToStringImplementation()
        throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        String customToStringReturnValue = "Hello, ByteBuddy!";

        assertEquals(
            customToStringReturnValue,
            Task1.createClassWithCustomToStringImplementation(customToStringReturnValue).toString()
        );
    }
}
