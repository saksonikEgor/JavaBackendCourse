package edu.hw11;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

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
