package edu.neu.csye6205.finalProject.Paurush.util;

import java.awt.Color;
import java.awt.Dimension;
import java.util.List;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import edu.neu.csye6205.finalProject.Paurush.Edge;
import edu.neu.csye6205.finalProject.Paurush.Graph;
import edu.neu.csye6205.finalProject.Paurush.Node;



public class GraphPlotter {
    public static void plotGraph(Graph graph) {
        // Create a new XY series collection
        XYSeriesCollection dataset = new XYSeriesCollection();

        // Add a new series for each node in the graph
        List<Node> nodes = graph.getNodes();
        for (Node node : nodes) {
            XYSeries series = new XYSeries(node.getId());
            series.add(node.getLatitude(), node.getLongitude());
            dataset.addSeries(series);
        }

        // Add lines for each edge in the graph
        List<Edge> edges = graph.getEdges();
        for (Edge edge : edges) {
            Node a = edge.getA();
            Node b = edge.getB();
            double[] x = {a.getLatitude(), b.getLatitude()};
            double[] y = {a.getLongitude(), b.getLongitude()};
            dataset.addSeries(createLineSeries(x, y));
        }

        // Create a new chart
        JFreeChart chart = ChartFactory.createXYLineChart("Graph Plot", "Latitude", "Longitude", dataset,
                PlotOrientation.VERTICAL, true, true, false);

        // Set the background color of the chart
        chart.setBackgroundPaint(Color.white);

        // Get the plot object and set the axis colors
        XYPlot plot = chart.getXYPlot();
        plot.setBackgroundPaint(Color.white);
        plot.setDomainGridlinePaint(Color.lightGray);
        plot.setRangeGridlinePaint(Color.lightGray);

        // Create a new chart panel and set the size
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(500, 500));

        // Show the chart in a new window
        JFrame frame = new JFrame("Graph Plot");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(chartPanel);
        frame.pack();
        frame.setVisible(true);
    }

    private static XYSeries createLineSeries(double[] x, double[] y) {
        XYSeries series = new XYSeries("");
        for (int i = 0; i < x.length; i++) {
            series.add(x[i], y[i]);
        }
        return series;
    }
}
