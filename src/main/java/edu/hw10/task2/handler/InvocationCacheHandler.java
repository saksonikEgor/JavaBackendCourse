package edu.hw10.task2.handler;

import edu.hw10.task2.annotations.Cache;
import edu.hw10.task2.model.CachedRequest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class InvocationCacheHandler<T> implements InvocationHandler {
    private final T instance;
    private final Map<CachedRequest, Object> cached = new HashMap<>();

    public InvocationCacheHandler(T instance) {
        this.instance = instance;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        method.setAccessible(true);

        if (method.isAnnotationPresent(Cache.class) && method.getAnnotation(Cache.class).persist()) {
            return cached.computeIfAbsent(
                new CachedRequest(
                    method.getName(),
                    Arrays.toString(args)
                ), key -> {
                    try {
                        return method.invoke(instance, args);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        throw new RuntimeException(e);
                    }
                });
        }

        return method.invoke(instance, args);
    }
}
