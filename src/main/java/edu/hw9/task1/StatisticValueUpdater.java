package edu.hw9.task1;

import java.util.List;

@FunctionalInterface
public interface StatisticValueUpdater {
    double update(double curStatValue, int valueCount, List<Double> data);
}
