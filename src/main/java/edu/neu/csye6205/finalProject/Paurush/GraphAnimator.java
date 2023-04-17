package edu.neu.csye6205.finalProject.Paurush;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public class GraphAnimator {
    private final CustomGraph graph;
    private static final int width = 1200;
    private static final int height = 1200;
    private static final int delay = 200;
    private static final int NODE_RADIUS = 5;
    public final String name;
    private static final Color BGColor= Color.WHITE; 
    private final Color NodeColor; 
    private final Color EdgeColor;
    

    public GraphAnimator(CustomGraph graph, String name, Color nodeColor, Color edgeColor) {
        this.graph = graph;
        this.name = name;
		this.NodeColor = nodeColor;
		this.EdgeColor = edgeColor;
    }

    public void animate() {
        List<Node> nodes = graph.getNodes();
        List<Edge> edges = graph.getEdges();
        int nFrames = edges.size();
        List<BufferedImage> frames = new ArrayList<>();

        double xMin = -0.010;
        double xMax = 0.005;
        double yMin = 0.8944;
        double yMax = 0.9036;

        for (int i = 0; i < nFrames; i++) {
            BufferedImage frame = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = frame.createGraphics();

            // Set background color
            g2d.setColor(BGColor);
            g2d.fillRect(0, 0, width, height);

            // Draw nodes and edges as before
            // ...
         // Draw nodes
            g2d.setColor(NodeColor);
            for (Node node : nodes) {
                int xPixel = (int) ((node.getLongitude() - xMin) / (xMax - xMin) * width);
                int yPixel = (int) ((node.getLatitude() - yMin) / (yMax - yMin) * height);
                g2d.fillOval(xPixel - NODE_RADIUS, yPixel - NODE_RADIUS, NODE_RADIUS * 2, NODE_RADIUS * 2);
            }

            // Draw edges up to and including the i-th edge
            g2d.setColor(EdgeColor);
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
            
            try {
                File outputfile = new File("animation_frames/frame_ "+ name + i + ".png");
                ImageIO.write(frame, "png", outputfile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        JFrame frame = new JFrame();
        frame.setTitle(name);
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
