package com.s2;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class AnnotationUtils {
    public static Object invokeWithTimeLog(Object target, Method method, Object... args) throws InvocationTargetException, IllegalAccessException {
        long start = System.currentTimeMillis();
        Object result = method.invoke(target, args);
        long cost = System.currentTimeMillis()-start;

        if (method.isAnnotationPresent(LogTime.class)) {
            System.out.printf("[INFO]%s executed in %dms%n", method.getName(), cost);
        }
        return result;
    }
}
