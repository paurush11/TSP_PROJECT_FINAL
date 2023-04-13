package edu.neu.csye6205.finalProject.Paurush;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import java.io.File;
import java.io.IOException;
import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageOutputStream;

public class GraphAnimatorGIF {
    private static final int NODE_RADIUS = 5;

    private CustomGraph graph;
    private int width;
    private int height;
    private int delay;

    private static final double LATITUDE_MIN = 51.261334;
    private static final double LATITUDE_MAX = 51.684761;
    private static final double LONGITUDE_MIN = -0.505587;
    private static final double LONGITUDE_MAX = 0.297941;

    public GraphAnimatorGIF(CustomGraph graph, int width, int height, int delay) {
        this.graph = graph;
        this.width = width;
        this.height = height;
        this.delay = delay;
    }

    public void animate() {
        List<Edge> edges = graph.getEdges();
        int nFrames = edges.size();
        List<BufferedImage> frames = new ArrayList<>();

        for (int i = 0; i < nFrames; i++) {
            BufferedImage frame = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = frame.createGraphics();

            // Set background color
            g2d.setColor(Color.WHITE);
            g2d.fillRect(0, 0, width, height);

            // Draw nodes
            g2d.setColor(Color.RED);
            for (Node node : graph.getNodes()) {
                int xPixel = (int) ((node.getLongitude() - LONGITUDE_MIN) / (LONGITUDE_MAX - LONGITUDE_MIN) * width);
                int yPixel = (int) ((node.getLatitude() - LATITUDE_MIN) / (LATITUDE_MAX - LATITUDE_MIN) * height);
                g2d.fillOval(xPixel - NODE_RADIUS, yPixel - NODE_RADIUS, NODE_RADIUS * 2, NODE_RADIUS * 2);
            }

            // Draw edges up to and including the i-th edge
            g2d.setColor(Color.BLUE);
            for (int j = 0; j <= i; j++) {
                Edge edge = edges.get(j);
                Node a = edge.getA();
                Node b = edge.getB();
                int x1 = (int) ((a.getLongitude() - LONGITUDE_MIN) / (LONGITUDE_MAX - LONGITUDE_MIN) * width);
                int y1 = (int) ((a.getLatitude() - LATITUDE_MIN) / (LATITUDE_MAX - LATITUDE_MIN) * height);
                int x2 = (int) ((b.getLongitude() - LONGITUDE_MIN) / (LONGITUDE_MAX - LONGITUDE_MIN) * width);
                int y2 = (int) ((b.getLatitude() - LATITUDE_MIN) / (LATITUDE_MAX - LATITUDE_MIN) * height);
                g2d.drawLine(x1, y1, x2, y2);
            }

            frames.add(frame);
            g2d.dispose();
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        createGIF(frames, "graph_animation.gif", delay);
    }

    private void createGIF(List<BufferedImage> frames, String fileName, int delay) {
        try {
            ImageOutputStream output = new FileImageOutputStream(new File(fileName));
            GifSequenceWriter writer = new GifSequenceWriter(output, frames.get(0).getType(), delay, true);

            for (BufferedImage frame : frames) {
                writer.writeToSequence(frame);
            }

            writer.close();
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
	