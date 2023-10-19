package edu.hw2.task4;

public class Task4 {
    public static final String NO_ONE_CALLED_THIS_METHOD_MESSAGE = "no one called this method";

    private Task4() {
    }

    public static CallingInfo callingInfo(String methodName) {
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        for (StackTraceElement element : stackTraceElements) {
            if (element.getMethodName().equals(methodName)) {
                return new CallingInfo(element.getClassName(), element.getMethodName());
            }
        }
        return new CallingInfo(NO_ONE_CALLED_THIS_METHOD_MESSAGE, methodName);
    }

    public record CallingInfo(String className, String methodName) {
    }
}
