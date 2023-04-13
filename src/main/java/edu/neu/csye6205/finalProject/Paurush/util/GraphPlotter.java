package edu.neu.csye6205.finalProject.Paurush.util;

import java.awt.Color;
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
import edu.neu.csye6205.finalProject.Paurush.CustomGraph;
import edu.neu.csye6205.finalProject.Paurush.Node;



public class GraphPlotter{
	

	 public static void plotGraph(CustomGraph graph) {
	        XYSeriesCollection dataset = new XYSeriesCollection();
	        XYSeries series = new XYSeries("Edges");

	        List<Node> nodes = graph.getNodes();
	        List<Edge> edges = graph.getEdges();

	        // Add all the nodes to the series
	        for (Node node : nodes) {
	            series.add(node.getLatitude(), node.getLongitude());
	        }

	        // Add all the edges to the series
	        for (Edge edge : edges) {
	            series.add(edge.getA().getLatitude(), edge.getA().getLongitude());
	            series.add(edge.getB().getLatitude(), edge.getB().getLongitude());
	            
	        }

	        dataset.addSeries(series);

	        // Create the chart and plot
	        JFreeChart chart = ChartFactory.createXYLineChart("Graph", "Latitude", "Longitude", dataset, PlotOrientation.VERTICAL, true, true, false);
	        XYPlot plot = chart.getXYPlot();

	        // Customize the plot
	        plot.setBackgroundPaint(Color.white);
	        plot.setDomainGridlinePaint(Color.black);
	        plot.setRangeGridlinePaint(Color.black);

	        // Create and show the panel
	        ChartPanel panel = new ChartPanel(chart);
	        JFrame frame = new JFrame("Graph Plot");
	        frame.setContentPane(panel);
	        frame.setSize(1000, 1000);
	        frame.setVisible(true);
	    }

}
