package edu.neu.csye6205.finalProject.Paurush.GraphVisualizer;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

import edu.neu.csye6205.finalProject.Paurush.CustomGraph;
import edu.neu.csye6205.finalProject.Paurush.Edge;
import edu.neu.csye6205.finalProject.Paurush.Node;

public class GraphVisualizer extends JPanel {
    private CustomGraph graph;
    private List<Node> nodes;
    private List<Edge> edges;
    private int pointRadius = 10;

    public GraphVisualizer(CustomGraph graph) {
        this.graph = graph;
        nodes = new ArrayList<Node>(graph.getNodes());
        edges = new ArrayList<Edge>(graph.getEdges());
    }

    public void setGraph(CustomGraph graph) {
        this.graph = graph;
        nodes = new ArrayList<Node>(graph.getNodes());
        edges = new ArrayList<Edge>(graph.getEdges());
        repaint();
    }

    public void setPointRadius(int radius) {
        this.pointRadius = radius;
        repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);

        // Draw nodes as circles
        for (Node node : nodes) {
            Point p = node.getPosition();
            g.fillOval(p.x - pointRadius, p.y - pointRadius, pointRadius * 2, pointRadius * 2);
        }

        // Draw edges as lines
        for (Edge edge : edges) {
            Point a = edge.getA().getPosition();
            Point b = edge.getB().getPosition();
            g.drawLine(a.x, a.y, b.x, b.y);
        }
    }

    public Dimension getPreferredSize() {
        int maxX = 0, maxY = 0;
        for (Node node : nodes) {
            Point p = node.getPosition();
            if (p.x > maxX) maxX = p.x;
            if (p.y > maxY) maxY = p.y;
        }
        return new Dimension(maxX + pointRadius, maxY + pointRadius);
    }
    
    public void plotNode(Node node) {
    	int x = (int)node.getLatitude();
    	int y = (int)node.getLongitude();
        Graphics2D g2 = (Graphics2D) this.getGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setStroke(new BasicStroke(2));
        g2.setColor(Color.BLUE);
        Ellipse2D.Double circle = new Ellipse2D.Double(x - 5, y - 5, 10, 10);
        g2.draw(circle);
        g2.fill(circle);
        g2.setColor(Color.BLACK);
        g2.drawString(node.getId(), x + 5, y + 15);
    }

    public void plotEdge(Edge edge ) {
    	int startX = (int)edge.getA().getLatitude();
    	int startY =(int)edge.getA().getLongitude();
    	int endX = (int)edge.getB().getLatitude();
    	int endY = (int)edge.getA().getLongitude();
        Graphics2D g2 = (Graphics2D) this.getGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setStroke(new BasicStroke(2));
        g2.setColor(Color.RED);
        g2.drawLine(startX, startY, endX, endY);
        g2.setColor(Color.BLACK);
        g2.drawString(String.format("%.2f", edge.getEdgeWeight()), (startX + endX) / 2, (startY + endY) / 2);
    }
}
