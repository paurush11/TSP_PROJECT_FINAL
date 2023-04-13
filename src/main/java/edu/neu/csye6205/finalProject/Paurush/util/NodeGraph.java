package edu.neu.csye6205.finalProject.Paurush.util;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.annotations.XYLineAnnotation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import edu.neu.csye6205.finalProject.Paurush.Node;

public class NodeGraph {
	public static void plot(List<Node> nodes) {
        // Create the dataset with the nodes
        XYSeriesCollection dataset = new XYSeriesCollection();
        XYSeries series = new XYSeries("Nodes");
        for (Node node : nodes) {
            series.add(node.getLongitude(), node.getLatitude());
        }
        dataset.addSeries(series);

        // Create the chart
        JFreeChart chart = ChartFactory.createScatterPlot(
                "Graph Plot",
                "Longitude",
                "Latitude",
                dataset
        );
        XYPlot plot = (XYPlot) chart.getPlot();

        // Customize the chart
        plot.setBackgroundPaint(Color.white);
        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.black);
        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.black);

        // Create the edges
        List<XYLineAnnotation> edges = new ArrayList<>();
        for (int i = 0; i < nodes.size() - 1; i++) {
            Node node1 = nodes.get(i);
            Node node2 = nodes.get(i + 1);
            edges.add(new XYLineAnnotation(
                    node1.getLongitude(), node1.getLatitude(),
                    node2.getLongitude(), node2.getLatitude(),
                    new BasicStroke(2f), getRandomColor()
            ));
        }

        // Add the edges to the plot
        for (XYLineAnnotation edge : edges) {
            plot.addAnnotation(edge);
        }

        // Create and show the frame
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(1000, 1000));
        JFrame frame = new JFrame("Graph Plot");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(chartPanel);
        frame.pack();
        frame.setVisible(true);
    }


    private static Color getRandomColor() {
        Random rand = new Random();
        float r = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();
        return new Color(r, g, b);
    }
}
