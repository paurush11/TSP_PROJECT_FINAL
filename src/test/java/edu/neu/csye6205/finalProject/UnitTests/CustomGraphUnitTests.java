package edu.neu.csye6205.finalProject.UnitTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import edu.neu.csye6205.finalProject.Paurush.CustomGraph;
import edu.neu.csye6205.finalProject.Paurush.Edge;
import edu.neu.csye6205.finalProject.Paurush.Node;

public class CustomGraphUnitTests {

    private CustomGraph graph;
    private Node node1, node2, node3;
    private Edge edge1, edge2;
    
//    public Node(String ID, double parseDouble, double parseDouble2) {

    @Before
    public void setUp() {
        graph = new CustomGraph();
        node1 = new Node("CityA", -0.016542, 51.515192);
        node2 = new Node("CityB", -0.189609,51.353616);
        node3 = new Node("CityC", -0.189609,51.353616);
        edge1 = new Edge(node1, node2, 2);
        edge2 = new Edge(node2, node3, 4);
        graph.addNode(node1);
        graph.addNode(node2);
        graph.addNode(node3);
        graph.addEdge(edge1);
        graph.addEdge(edge2);
    }

    @Test
    public void testGetNodes() {
        assertEquals(graph.getNodes().size(), 3);
        assertTrue(graph.getNodes().contains(node1));
        assertTrue(graph.getNodes().contains(node2));
        assertTrue(graph.getNodes().contains(node3));
    }

    @Test
    public void testGetEdges() {
        assertEquals(graph.getEdges().size(), 2);
        assertTrue(graph.getEdges().contains(edge1));
        assertTrue(graph.getEdges().contains(edge2));
    }

    @Test
    public void testDegree() {
        assertEquals(graph.degree(node1), 1);
        assertEquals(graph.degree(node2), 2);
        assertEquals(graph.degree(node3), 1);
    }

    @Test
    public void testGetEdge() {
        assertEquals(graph.getEdge(node1, node2), edge1);
        assertEquals(graph.getEdge(node2, node3), edge2);
        assertNull(graph.getEdge(node1, node3));
    }
}
