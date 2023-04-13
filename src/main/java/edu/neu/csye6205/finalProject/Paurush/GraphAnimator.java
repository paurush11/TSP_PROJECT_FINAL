package edu.neu.csye6205.finalProject.Paurush;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class GraphAnimator {
    private final CustomGraph graph;
    private final int width;
    private final int height;
    private final int delay;
    private static final int NODE_RADIUS = 5;

    public GraphAnimator(CustomGraph graph, int width, int height, int delay) {
        this.graph = graph;
        this.width = width;
        this.height = height;
        this.delay = delay;
    }

    public void animate() {
        List<Node> nodes = graph.getNodes();
        List<Edge> edges = graph.getEdges();
        int nFrames = edges.size();
        List<BufferedImage> frames = new ArrayList<>();

        double xMin = -0.007;
        double xMax = 0.003;
        double yMin = 0.8966;
        double yMax = 0.9016;

        for (int i = 0; i < nFrames; i++) {
            BufferedImage frame = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = frame.createGraphics();

            // Set background color
            g2d.setColor(Color.WHITE);
            g2d.fillRect(0, 0, width, height);

            // Draw nodes and edges as before
            // ...
         // Draw nodes
            g2d.setColor(Color.RED);
            for (Node node : nodes) {
                int xPixel = (int) ((node.getLongitude() - xMin) / (xMax - xMin) * width);
                int yPixel = (int) ((node.getLatitude() - yMin) / (yMax - yMin) * height);
                g2d.fillOval(xPixel - NODE_RADIUS, yPixel - NODE_RADIUS, NODE_RADIUS * 2, NODE_RADIUS * 2);
            }

            // Draw edges up to and including the i-th edge
            g2d.setColor(Color.BLUE);
            for (int j = 0; j <= i; j++) {
                Edge edge = edges.get(j);
                Node a = edge.getA();
                Node b = edge.getB();
                int x1 = (int) ((a.getLongitude() - xMin) / (xMax - xMin) * width);
                int y1 = (int) ((a.getLatitude() - yMin) / (yMax - yMin) * height);
                int x2 = (int) ((b.getLongitude() - xMin) / (xMax - xMin) * width);
                int y2 = (int) ((b.getLatitude() - yMin) / (yMax - yMin) * height);
                g2d.drawLine(x1, y1, x2, y2);
            }


            frames.add(frame);
            g2d.dispose();
        }

        JFrame frame = new JFrame();
        frame.setTitle("Paurush Batish");
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);

        JLabel animationLabel = new JLabel(new ImageIcon(frames.get(0)));
        frame.add(animationLabel);

        for (int i = 1; i < nFrames; i++) {
            animationLabel.setIcon(new ImageIcon(frames.get(i)));
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        frame.dispose();
    }
}
