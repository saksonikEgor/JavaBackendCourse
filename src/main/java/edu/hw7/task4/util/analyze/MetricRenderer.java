package edu.hw7.task4.util.analyze;

import edu.hw7.task4.model.CounterResponse;
import edu.hw7.task4.util.PiCounter;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYSplineRenderer;
import org.jfree.data.Range;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class MetricRenderer {
    private static final String ITER_COUNT_LABEL = "Count of iterations";
    private static final String DURATION_LABEL = "Duration";
    private static final String FAULT_LABEL = "Fault";
    private static final String DURATION_CHART_LABEL = "Duration(iterCount)";
    private static final String FAULT_CHART_LABEL = "Fault(iterCount)";
    private static final int ITERATION_COUNT_UPPER_BOUND = 1_000_000;

    private MetricRenderer() {
    }

    public static void visualAllDependencies() {
        List<CounterResponse> single = new ArrayList<>();
        List<CounterResponse> two = new ArrayList<>();
        List<CounterResponse> four = new ArrayList<>();
        List<CounterResponse> six = new ArrayList<>();

        for (int iterCount = 1; iterCount < ITERATION_COUNT_UPPER_BOUND; iterCount += 1000) {
            single.add(PiCounter.countPiSingleThreadingMetric(iterCount));
            two.add(PiCounter.countPiMultiThreadingMetric(2, iterCount));
            four.add(PiCounter.countPiMultiThreadingMetric(4, iterCount));
            six.add(PiCounter.countPiMultiThreadingMetric(6, iterCount));
        }

        plotDurationChart(single, two, four, six);
        plotFaultChart(single, two, four, six);
    }

    private static void plotDurationChart(
        List<CounterResponse> single, List<CounterResponse> two,
        List<CounterResponse> four, List<CounterResponse> six
    ) {

        XYSeries singleSeries = new XYSeries("1 thread");
        XYSeries twoSeries = new XYSeries("2 threads");
        XYSeries fourSeries = new XYSeries("4 threads");
        XYSeries sixSeries = new XYSeries("6 threads");

        single.forEach(r -> singleSeries.add(r.iterCount(), (double) r.duration()));
        two.forEach(r -> twoSeries.add(r.iterCount(), (double) r.duration()));
        four.forEach(r -> fourSeries.add(r.iterCount(), (double) r.duration()));
        six.forEach(r -> sixSeries.add(r.iterCount(), (double) r.duration()));

        XYDataset xyDatasetModel = new XYSeriesCollection(singleSeries);
        JFreeChart chart = ChartFactory
            .createXYLineChart("", ITER_COUNT_LABEL, DURATION_LABEL,
                xyDatasetModel,
                PlotOrientation.VERTICAL,
                true, true, true
            );

        XYPlot plot = chart.getXYPlot();
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();

        rangeAxis.setRange(new Range(
            0,
            Stream.concat(
                    Stream.concat(single.stream(), two.stream()),
                    Stream.concat(single.stream(), two.stream())
                )
                .map(CounterResponse::duration)
                .max(Comparator.comparingLong(Long::longValue))
                .get()
        ));

        plot.setDataset(1, new XYSeriesCollection(twoSeries));
        plot.setDataset(2, new XYSeriesCollection(fourSeries));
        plot.setDataset(3, new XYSeriesCollection(sixSeries));

        XYSplineRenderer r0 = new XYSplineRenderer(1000);
        XYSplineRenderer r1 = new XYSplineRenderer(1000);
        XYSplineRenderer r2 = new XYSplineRenderer(1000);
        XYSplineRenderer r3 = new XYSplineRenderer(1000);

        r0.setSeriesPaint(0, Color.RED);
        r1.setSeriesPaint(0, Color.BLACK);
        r2.setSeriesPaint(0, Color.BLUE);
        r3.setSeriesPaint(0, Color.MAGENTA);
        r0.setSeriesShapesVisible(0, false);
        r1.setSeriesShapesVisible(0, false);
        r2.setSeriesShapesVisible(0, false);
        r3.setSeriesShapesVisible(0, false);

        plot.setRenderer(0, r0);
        plot.setRenderer(1, r1);
        plot.setRenderer(2, r2);
        plot.setRenderer(3, r3);

        JFrame frame = new JFrame(DURATION_CHART_LABEL);

        frame.getContentPane().add(new ChartPanel(chart));
        frame.setSize(400, 300);
        frame.setVisible(true);
    }

    private static void plotFaultChart(
        List<CounterResponse> single, List<CounterResponse> two,
        List<CounterResponse> four, List<CounterResponse> six
    ) {

        XYSeries singleSeries = new XYSeries("1 thread");
        XYSeries twoSeries = new XYSeries("2 threads");
        XYSeries fourSeries = new XYSeries("4 threads");
        XYSeries sixSeries = new XYSeries("6 threads");

        single.forEach(r -> singleSeries.add(r.iterCount(), r.fault()));
        two.forEach(r -> twoSeries.add(r.iterCount(), r.fault()));
        four.forEach(r -> fourSeries.add(r.iterCount(), r.fault()));
        six.forEach(r -> sixSeries.add(r.iterCount(), r.fault()));

        XYDataset xyDatasetModel = new XYSeriesCollection(singleSeries);
        JFreeChart chart = ChartFactory
            .createXYLineChart("", ITER_COUNT_LABEL, FAULT_LABEL,
                xyDatasetModel,
                PlotOrientation.VERTICAL,
                true, true, true
            );

        XYPlot plot = chart.getXYPlot();
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();

//        rangeAxis.setRange(new Range(
//            0,
//            Stream.concat(
//                    Stream.concat(single.stream(), two.stream()),
//                    Stream.concat(single.stream(), two.stream())
//                )
//                .map(CounterResponse::fault)
//                .max(Comparator.comparingDouble(Double::doubleValue))
//                .get()
//        ));
        rangeAxis.setRange(new Range(
            0,
            0.1
        ));

        plot.setDataset(1, new XYSeriesCollection(twoSeries));
        plot.setDataset(2, new XYSeriesCollection(fourSeries));
        plot.setDataset(3, new XYSeriesCollection(sixSeries));

        XYSplineRenderer r0 = new XYSplineRenderer(1000);
        XYSplineRenderer r1 = new XYSplineRenderer(1000);
        XYSplineRenderer r2 = new XYSplineRenderer(1000);
        XYSplineRenderer r3 = new XYSplineRenderer(1000);

        r0.setSeriesPaint(0, Color.RED);
        r1.setSeriesPaint(0, Color.BLACK);
        r2.setSeriesPaint(0, Color.BLUE);
        r3.setSeriesPaint(0, Color.MAGENTA);
        r0.setSeriesShapesVisible(0, false);
        r1.setSeriesShapesVisible(0, false);
        r2.setSeriesShapesVisible(0, false);
        r3.setSeriesShapesVisible(0, false);

        plot.setRenderer(0, r0);
        plot.setRenderer(1, r1);
        plot.setRenderer(2, r2);
        plot.setRenderer(3, r3);

        JFrame frame = new JFrame(FAULT_CHART_LABEL);

        frame.getContentPane().add(new ChartPanel(chart));
        frame.setSize(400, 300);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        visualAllDependencies();
    }
}
