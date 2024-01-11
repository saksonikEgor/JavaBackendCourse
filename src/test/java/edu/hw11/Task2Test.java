package edu.hw11;

import edu.hw11.task2.Task2;
import edu.hw11.task2.modelSample.Adder;
import edu.hw11.task2.modelSample.Multiplier;
import java.lang.reflect.InvocationTargetException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task2Test {
    @Test
    @DisplayName("Изменение поведения метода класса")
    void runtimeMethodDelegating()
        throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        final int A = 3;
        final int B = 10;

        assertEquals(
            Multiplier.multiply(A, B),
            ((Adder) Task2.changeMethodImplementation(
                Adder.class,
                Adder.class.getDeclaredMethod("sum", int.class, int.class),
                Multiplier.class
            )).sum(A, B)
        );
    }

}
