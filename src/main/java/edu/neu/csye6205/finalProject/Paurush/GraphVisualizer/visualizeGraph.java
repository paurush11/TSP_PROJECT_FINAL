package edu.neu.csye6205.finalProject.Paurush.GraphVisualizer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import javax.swing.JFrame;
import javax.swing.JPanel;

import edu.neu.csye6205.finalProject.Paurush.CustomGraph;
import edu.neu.csye6205.finalProject.Paurush.Edge;
import edu.neu.csye6205.finalProject.Paurush.Node;

public class visualizeGraph {
	public static void visualizeGraphDrawer(CustomGraph graph, List<Node> nodes) throws InterruptedException {
	    // create a new panel to draw the nodes and edges
	    JPanel panel = new JPanel() {
	        @Override
	        protected void paintComponent(Graphics g) {
	            super.paintComponent(g);
	            // plot all the nodes
	            for (Node node : nodes) {
	               
	                g.setColor(Color.BLACK);
	                g.fillOval((int)node.getLatitude() - 5, (int)node.getLongitude() - 5, 10, 10);
	            }
	            // connect the edges
	            for (Edge edge : graph.getEdges()) {
	                Node a = edge.getA();
	                Node b = edge.getB();
	                g.setColor(Color.RED);
	                g.drawLine((int)a.getLatitude(), (int)a.getLongitude(), (int)b.getLatitude(), (int)b.getLongitude());
	                // add a short delay to see the edges being drawn one by one
	                try {
						TimeUnit.MILLISECONDS.sleep(2);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	            }
	        }
	    };
	    // create a new frame to display the panel
	    JFrame frame = new JFrame();
	    frame.add(panel);
	    frame.setSize(1000, 1000);
	    frame.setVisible(true);
	}

}
