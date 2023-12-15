package edu.hw10.task2.utils;

import edu.hw10.task2.handler.InvocationCacheHandler;
import java.lang.reflect.Proxy;

public class CacheProxy {
    private CacheProxy() {
    }

    public static <T> T create(T instance, Class<T> classType) {
        return (T) Proxy.newProxyInstance(
            classType.getClassLoader(),
            new Class[] {classType},
            new InvocationCacheHandler<>(instance)
        );
    }
}
