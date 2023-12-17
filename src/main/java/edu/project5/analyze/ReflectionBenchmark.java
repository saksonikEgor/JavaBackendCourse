package edu.project5.analyze;

import edu.project5.model.Student;
import java.lang.invoke.CallSite;
import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

@State(Scope.Thread)
@SuppressWarnings("all")
public class ReflectionBenchmark {
    private static final String METHOD_NAME = "name";
    private static final String INTERFACE_METHOD_NAME = "get";
    private Student student;
    private Method method;
    private MethodHandle methodHandle;
    private Supplier<String> supplier;

    public static void main(String[] args) throws RunnerException {
        startRunner();
    }

    private static void startRunner() throws RunnerException {
        new Runner(
            new OptionsBuilder()
                .include(ReflectionBenchmark.class.getSimpleName())
                .shouldFailOnError(true)
                .shouldDoGC(true)
                .mode(Mode.AverageTime)
                .timeUnit(TimeUnit.NANOSECONDS)
                .forks(1)
                .warmupForks(1)
                .warmupIterations(1)
                .warmupTime(TimeValue.seconds(5))
                .measurementIterations(1)
                .measurementTime(TimeValue.seconds(10))
                .build()
        ).run();
    }

    @Setup
    public void setup() throws Throwable {
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        methodHandle = lookup.findVirtual(Student.class, METHOD_NAME, MethodType.methodType(String.class));

        CallSite callSite = LambdaMetafactory.metafactory(
            lookup,
            INTERFACE_METHOD_NAME,
            MethodType.methodType(Supplier.class, Student.class),
            MethodType.methodType(Object.class),
            methodHandle,
            MethodType.methodType(String.class)
        );

        student = new Student("Sample name", "Sample surname");
        method = Student.class.getDeclaredMethod(METHOD_NAME);
        supplier = (Supplier<String>) callSite.getTarget().invokeExact(student);
    }

    @Benchmark
    public void metricDirectAccess(Blackhole bh) {
        bh.consume(student.name());
    }

    @Benchmark
    public void metricReflection(Blackhole bh) throws InvocationTargetException, IllegalAccessException {
        bh.consume((String) method.invoke(student));
    }

    @Benchmark
    public void metricMethodHandles(Blackhole bh) throws Throwable {
        bh.consume((String) methodHandle.invoke(student));
    }

    @Benchmark
    public void metricLambdaMetafactory(Blackhole bh) {
        bh.consume(supplier.get());
    }
}
