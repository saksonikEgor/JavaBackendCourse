package edu.hw10.task1;

import edu.hw10.task1.annotations.Max;
import edu.hw10.task1.annotations.Min;
import edu.hw10.task1.annotations.NotNull;
import edu.hw10.task1.generation.RandomObjectGenerator;
import java.lang.reflect.InvocationTargetException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task1Test {
    private static final int LOWER_BOUND = 10;
    private static final int UPPER_BOUND = 100;

    @Test
    @DisplayName("Генерация рандомного POJO")
    void generatingRandomPOJO()
        throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        RandomObjectGenerator generator = new RandomObjectGenerator();

        POJOSample pojo = generator.nextObject(POJOSample.class);

        assertNotNull(pojo);
        assertTrue(pojo.intNumber >= LOWER_BOUND && pojo.intNumber <= UPPER_BOUND);
        assertTrue(pojo.doubleNumber <= LOWER_BOUND);
        assertNotNull(pojo.name);
    }

    @Test
    @DisplayName("Генерация рандомного Record")
    void generatingRandomRecord()
        throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        RandomObjectGenerator generator = new RandomObjectGenerator();

        RecordSample record = generator.nextObject(RecordSample.class);

        assertNotNull(record);
        assertTrue(record.intNumber >= LOWER_BOUND && record.intNumber <= UPPER_BOUND);
        assertTrue(record.doubleNumber <= LOWER_BOUND);
        assertNotNull(record.name);
    }

    @Test
    @DisplayName("Генерация рандомного инстанса через фабричный метод")
    void randomFabricGenerating()
        throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException {
        RandomObjectGenerator generator = new RandomObjectGenerator();

        FabricSample singleton = generator.nextObject(FabricSample.class, "create");

        assertNotNull(singleton);
        assertTrue(singleton.intNumber >= LOWER_BOUND && singleton.intNumber <= UPPER_BOUND);
        assertTrue(singleton.doubleNumber <= LOWER_BOUND);
        assertNotNull(singleton.name);
    }

    private static class POJOSample {
        @Min(value = LOWER_BOUND)
        @Max(value = UPPER_BOUND)
        private final int intNumber;
        private final boolean flag;
        private final char symbol;
        @Max(value = LOWER_BOUND)
        private final double doubleNumber;
        @NotNull
        private final String name;

        public POJOSample(int intNumber, boolean flag, char symbol, double doubleNumber, String name) {
            this.intNumber = intNumber;
            this.flag = flag;
            this.symbol = symbol;
            this.doubleNumber = doubleNumber;
            this.name = name;
        }
    }

    private record RecordSample(
        @Min(value = LOWER_BOUND) @Max(value = UPPER_BOUND) int intNumber,
        boolean flag,
        char symbol,
        @Max(value = LOWER_BOUND) double doubleNumber,
        @NotNull String name
    ) {
    }

    private static class FabricSample {
        @Min(value = LOWER_BOUND)
        @Max(value = UPPER_BOUND)
        private int intNumber;
        private boolean flag;
        private char symbol;
        @Max(value = LOWER_BOUND)
        private double doubleNumber;
        @NotNull
        private String name;

        private FabricSample() {
        }

        public static FabricSample create() {
            return new FabricSample();
        }
    }
}
