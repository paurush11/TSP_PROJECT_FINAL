package edu.neu.csye6205.finalProject.UnitTests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import edu.neu.csye6205.finalProject.Paurush.Edge;
import edu.neu.csye6205.finalProject.Paurush.Node;

public class EdgeUnitTest {
    @Test
    public void testGetters() {
        Node a = new Node("CityA", -0.016542, 51.515192);
        Node b = new Node("CityB", -0.189609,51.353616);
        double edgeWeight = 10.0;

        Edge edge = new Edge(a, b, edgeWeight);

        assertEquals(a, edge.getA());
        assertEquals(b, edge.getB());
        assertEquals(edgeWeight, edge.getEdgeWeight(), 10.0);
    }

    @Test
    public void testGetId() {
        Node a = new Node("CityA", -0.016542, 51.515192);
        Node b = new Node("CityB", -0.189609,51.353616);
        double edgeWeight = 10.0;

        Edge edge = new Edge(a, b, edgeWeight);

        String expectedId = "CityA-CityB";
        String actualId = edge.getId();

        assertEquals(expectedId, actualId);
    }
}
