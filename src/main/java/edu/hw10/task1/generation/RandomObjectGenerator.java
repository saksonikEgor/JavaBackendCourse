package edu.hw10.task1.generation;

import edu.hw10.task1.annotations.Max;
import edu.hw10.task1.annotations.Min;
import edu.hw10.task1.annotations.NotNull;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.RecordComponent;
import java.util.Arrays;
import java.util.Random;

public class RandomObjectGenerator {
    private static final Random RANDOM = new Random();

    public <T> T nextObject(Class<T> classType, String gettingInstanceMethodName)
        throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException {
        Method gettingInstanceMethod = classType.getDeclaredMethod(gettingInstanceMethodName);
        gettingInstanceMethod.setAccessible(true);

        if (!gettingInstanceMethod.getReturnType().equals(classType)) {
            throw new IllegalArgumentException("The type of the instance getter method "
                + "does not match the type of the class");
        }

        T instance = (T) gettingInstanceMethod.invoke(null);
        for (Field field : classType.getDeclaredFields()) {
            field.setAccessible(true);

            if (field.getType().isPrimitive()) {
                field.set(
                    instance,
                    getRandomPrimitiveValue(field.getType(), getMin(field), getMax(field))
                );
            } else {
                field.set(
                    instance,
                    field.isAnnotationPresent(NotNull.class)
                        ? field.getType().getConstructor().newInstance()
                        : null
                );
            }
        }

        return instance;
    }

    public <T> T nextObject(Class<T> classType)
        throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Constructor<T> constructor = classType.getDeclaredConstructor(
            classType.isRecord()
                ? Arrays.stream(classType.getRecordComponents())
                .map(RecordComponent::getType)
                .toArray(Class[]::new)
                : Arrays.stream(classType.getDeclaredFields())
                .map(Field::getType)
                .toArray(Class[]::new)
        );
        constructor.setAccessible(true);

        Object[] parameters;
        if (classType.isRecord()) {
            parameters = Arrays.stream(classType.getRecordComponents())
                .map(field -> {
                    if (field.getType().isPrimitive()) {
                        return getRandomPrimitiveValue(
                            field.getType(),
                            getMin(field),
                            getMax(field)
                        );
                    }
                    try {
                        return field.isAnnotationPresent(NotNull.class)
                            ? field.getType().getConstructor().newInstance()
                            : null;
                    } catch (InstantiationException | IllegalAccessException
                             | InvocationTargetException | NoSuchMethodException e) {
                        throw new RuntimeException(e);
                    }
                })
                .toArray();
        } else {
            parameters = Arrays.stream(classType.getDeclaredFields())
                .map(field -> {
                    if (field.getType().isPrimitive()) {
                        return getRandomPrimitiveValue(
                            field.getType(),
                            getMin(field),
                            getMax(field)
                        );
                    }
                    try {
                        return field.isAnnotationPresent(NotNull.class)
                            ? field.getType().getConstructor().newInstance()
                            : null;
                    } catch (InstantiationException | IllegalAccessException
                             | InvocationTargetException | NoSuchMethodException e) {
                        throw new RuntimeException(e);
                    }
                })
                .toArray();
        }

        return constructor.newInstance(parameters);
    }

    private <T> T getRandomPrimitiveValue(Class<T> parameterType, int min, int max) {
        return (T) switch (parameterType.getName()) {
            case "byte" -> (byte) RANDOM.nextInt(min, max);
            case "boolean" -> RANDOM.nextBoolean();
            case "char" -> (char) RANDOM.nextInt(min, max);
            case "short" -> (short) RANDOM.nextInt(min, max);
            case "int" -> RANDOM.nextInt(min, max);
            case "long" -> RANDOM.nextLong(min, max);
            case "float" -> RANDOM.nextFloat(min, max);
            case "double" -> RANDOM.nextDouble(min, max);
            default -> throw new IllegalStateException("Type in not primitive: " + parameterType.getName());
        };
    }

    private int getMin(RecordComponent component) {
        return component.isAnnotationPresent(Min.class) ? component.getAnnotation(Min.class).value()
            : Integer.MIN_VALUE;
    }

    private int getMin(Field field) {
        return field.isAnnotationPresent(Min.class) ? field.getAnnotation(Min.class).value() : Integer.MIN_VALUE;
    }

    private int getMax(RecordComponent component) {
        return component.isAnnotationPresent(Max.class) ? component.getAnnotation(Max.class).value()
            : Integer.MAX_VALUE;
    }

    private int getMax(Field field) {
        return field.isAnnotationPresent(Max.class) ? field.getAnnotation(Max.class).value() : Integer.MAX_VALUE;
    }
}
