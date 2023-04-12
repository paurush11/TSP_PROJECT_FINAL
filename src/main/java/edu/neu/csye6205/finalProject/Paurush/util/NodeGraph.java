package edu.neu.csye6205.finalProject.Paurush.util;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.annotations.XYLineAnnotation;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYShapeRenderer;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;

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
                    new BasicStroke(2f), Color.red
            ));
        }

        // Add the edges to the plot
        for (XYLineAnnotation edge : edges) {
            plot.addAnnotation(edge);
        }

        // Create and show the frame
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(500, 500));
        JFrame frame = new JFrame("Graph Plot");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(chartPanel);
        frame.pack();
        frame.setVisible(true);
    }


//    public NodeGraph(String title, List<Node> nodeList) {
//    	  super(title);
//    	JFreeChart chart = createChart(nodeList);
//        ChartPanel chartPanel = new ChartPanel(chart);
//        chartPanel.setPreferredSize(new Dimension(500, 500));
//        setContentPane(chartPanel);
//		// TODO Auto-generated constructor stub
//	}
    
//    private JFreeChart createChart(List<Node> nodeList) {
//        XYSeriesCollection dataset = new XYSeriesCollection();
//        XYSeries series = new XYSeries("Nodes");
//
//        for (Node node : nodeList) {
//            double lat = node.getLatitude();
//            double lon = node.getLongitude();
//            series.add(lon, lat);
//        }
//
//        dataset.addSeries(series);
//
//        JFreeChart chart = ChartFactory.createXYLineChart(
//                "Graph", "Longitude", "Latitude",
//                dataset, PlotOrientation.VERTICAL, true, true, false);
//
//        XYPlot plot = chart.getXYPlot();
//        NumberAxis domainAxis = (NumberAxis) plot.getDomainAxis();
//        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
//        domainAxis.setRange(-0.505587, 0.297941);
//        rangeAxis.setRange(51.261334, 51.684761);
//
//        // Add lines between nodes
//        for (int i = 0; i < nodeList.size() - 1; i++) {
//            Node currNode = nodeList.get(i);
//            Node nextNode = nodeList.get(i + 1);
//            double x1 = currNode.getLongitude();
//            double y1 = currNode.getLatitude();
//            double x2 = nextNode.getLongitude();
//            double y2 = nextNode.getLatitude();
//            plot.getRenderer().setSeriesPaint(i, getRandomColor());
//            plot.getRenderer().setSeriesStroke(i, new BasicStroke(2.0f));
//            plot.getRenderer().drawLine(
//                    plot.getDomainAxis().valueToJava2D(x1, plot.getScreenDataArea(),
//                            plot.getDomainAxisEdge()),
//                    plot.getRangeAxis().valueToJava2D(y1, plot.getScreenDataArea(),
//                            plot.getRangeAxisEdge()),
//                    plot.getDomainAxis().valueToJava2D(x2, plot.getScreenDataArea(),
//                            plot.getDomainAxisEdge()),
//                    plot.getRangeAxis().valueToJava2D(y2, ((ChartPanel) plot).getScreenDataArea(),
//                            plot.getRangeAxisEdge()));
//        }
//
//        return chart;
//    }
	private static final double MIN_LATITUDE = 51.261334;
    private static final double MAX_LATITUDE = 51.684761;
    private static final double MIN_LONGITUDE = -0.505587;
    private static final double MAX_LONGITUDE = 0.297941;

//    public static void createChart(List<Node> nodes) {
//        // Create dataset with node locations
//        DefaultXYDataset dataset = new DefaultXYDataset();
//        double[][] data = new double[2][nodes.size()];
//        for (int i = 0; i < nodes.size(); i++) {
//            data[0][i] = nodes.get(i).getLongitude();
//            data[1][i] = nodes.get(i).getLatitude();
//        }
//        dataset.addSeries("Nodes", data);
//
//        // Create chart with plot and renderer
//        JFreeChart chart = ChartFactory.createScatterPlot(
//                "Node Locations", "Longitude", "Latitude", dataset,
//                PlotOrientation.VERTICAL, true, true, false);
//
//        XYPlot plot = (XYPlot) chart.getPlot();
//        plot.setBackgroundPaint(Color.WHITE);
//        plot.setDomainPannable(true);
//        plot.setRangePannable(true);
//        plot.setDomainGridlinePaint(Color.LIGHT_GRAY);
//        plot.setRangeGridlinePaint(Color.LIGHT_GRAY);
//
//        XYItemRenderer renderer = plot.getRenderer();
//        renderer.setSeriesShape(0, new Ellipse2D.Double(-2.5, -2.5, 5, 5));
//        renderer.setSeriesPaint(0, Color.BLUE);
//
//        // Set x-axis and y-axis ranges
//        NumberAxis domainAxis = (NumberAxis) plot.getDomainAxis();
//        domainAxis.setRange(MIN_LONGITUDE, MAX_LONGITUDE);
//        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
//        rangeAxis.setRange(MIN_LATITUDE, MAX_LATITUDE);
//
//        // Show chart in a JFrame
//        ChartPanel chartPanel = new ChartPanel(chart);
//        chartPanel.setPreferredSize(new Dimension(800, 600));
//        JFrame frame = new JFrame("Node Locations");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setContentPane(chartPanel);
//        frame.pack();
//        frame.setVisible(true);
//    }
    private static Color getRandomColor() {
        Random rand = new Random();
        float r = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();
        return new Color(r, g, b);
    }
}
