package edu.hw9.task1;

import java.util.Comparator;

public enum Metric {
    SUM, AVG, MAX, MIN;

    public StatisticValueUpdater getFunction() {
        return switch (this) {
            case SUM -> (curStatValue, valueCount, nextNumbers) ->
                curStatValue + nextNumbers.stream().mapToDouble(Double::doubleValue).sum();
            case AVG -> (curStatValue, valueCount, nextNumbers) ->
                (curStatValue * (valueCount - nextNumbers.size())
                    + nextNumbers.stream().mapToDouble(Double::doubleValue).sum())
                    / valueCount;
            case MAX -> (curStatValue, valueCount, nextNumbers) ->
                Math.max(curStatValue, nextNumbers.stream().max(Comparator.comparingDouble(Double::doubleValue)).get());
            case MIN -> (curStatValue, valueCount, nextNumbers) ->
                Math.min(curStatValue, nextNumbers.stream().min(Comparator.comparingDouble(Double::doubleValue)).get());
        };
    }
}
