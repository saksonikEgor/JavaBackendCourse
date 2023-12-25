package edu.hw7.task4.model;

public record CounterResponse(
    double pi,
    long duration,
    double fault,
    int threadCount,
    int iterCount
) {
}
