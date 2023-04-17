package edu.neu.csye6205.finalProject.UnitTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.jgrapht.alg.util.Pair;
import org.junit.Test;

import edu.neu.csye6205.finalProject.Paurush.CustomGraph;
import edu.neu.csye6205.finalProject.Paurush.Driver;
import edu.neu.csye6205.finalProject.Paurush.Edge;
import edu.neu.csye6205.finalProject.Paurush.Node;

public class PerfectMatchingUnitTests {
	@Test
	public void testGeneratePerfectMatching() {
		CustomGraph graph = new CustomGraph();

		// Create nodes for the graph
		Node nodeA = new Node("A", -0.016542, 51.515192);
		Node nodeB = new Node("B", -0.236815, 51.406763);
		Node nodeC = new Node("C", -0.184411, 51.495871);
		Node nodeD = new Node("D", -0.268832, 51.464685);
		Node nodeE = new Node("E", -0.098618, 51.415897);

		// Add edges to the graph

		graph.addEdge(new Edge(nodeA, nodeB, 2));
		graph.addEdge(new Edge(nodeB, nodeC, 3));
		graph.addEdge(new Edge(nodeC, nodeD, 1));
		graph.addEdge(new Edge(nodeD, nodeE, 1));
		graph.addEdge(new Edge(nodeE, nodeA, 4));
		graph.addEdge(new Edge(nodeA, nodeC, 5));
		graph.addEdge(new Edge(nodeB, nodeD, 6));

		// Generate perfect matching
		List<Pair<Node, Node>> matchingPairs = Driver.generatePerfectMatching(graph);

		// Verify that the matching pairs cover all nodes in the graph
		List<Node> nodesInPairs = new ArrayList<>();
		for (Pair<Node, Node> pair : matchingPairs) {
			nodesInPairs.add(pair.getFirst());
			nodesInPairs.add(pair.getSecond());
		}
		assertEquals(nodesInPairs.size(), graph.getNodes().size());

		// Verify that all nodes in the matching pairs have odd degree
		for (Pair<Node, Node> pair : matchingPairs) {
			assertTrue(graph.degree(pair.getFirst()) % 2 != 0);
			assertTrue(graph.degree(pair.getSecond()) % 2 != 0);
		}
	}

	@Test
	public void testGeneratePerfectMatchingNoOddVertices() {
		CustomGraph graph = new CustomGraph();

		// Create nodes for the graph
		Node nodeA = new Node("A", -0.016542, 51.515192);
		Node nodeB = new Node("B", -0.236815, 51.406763);
		Node nodeC = new Node("C", -0.184411, 51.495871);
		Node nodeD = new Node("D", -0.268832, 51.464685);

		// Add edges to the graph

		graph.addEdge(new Edge(nodeA, nodeB, 2));
		graph.addEdge(new Edge(nodeB, nodeC, 3));
		graph.addEdge(new Edge(nodeC, nodeD, 1));
		graph.addEdge(new Edge(nodeD, nodeA, 6));

		// Generate perfect matching
		List<Pair<Node, Node>> matchingPairs = Driver.generatePerfectMatching(graph);

		// Verify that there are no matching pairs
		assertEquals(matchingPairs.size(), 0);
	}
	
    
    @Test
    public void testGeneratePerfectMatchingAllOddVertices() {
        CustomGraph graph = new CustomGraph();
        
        // Create nodes for the graph
		// Create nodes for the graph
		Node nodeA = new Node("A", -0.016542, 51.515192);
		Node nodeB = new Node("B", -0.236815, 51.406763);
		Node nodeC = new Node("C", -0.184411, 51.495871);
		Node nodeD = new Node("D", -0.268832, 51.464685);
        
        // Add edges to the graph
		graph.addEdge(new Edge(nodeA, nodeB, 2));
		graph.addEdge(new Edge(nodeB, nodeC, 3));
		graph.addEdge(new Edge(nodeC, nodeD, 1));
		graph.addEdge(new Edge(nodeD, nodeA, 6));
		graph.addEdge(new Edge(nodeA, nodeC, 5));
		graph.addEdge(new Edge(nodeB, nodeD, 6));
        
        // Generate perfect matching
        List<Pair<Node, Node>> matchingPairs = Driver.generatePerfectMatching(graph);
        
        // Verify that all nodes in the matching pairs have odd degree
        for (Pair<Node, Node> pair : matchingPairs) {
            assertTrue(graph.degree(pair.getFirst()) % 2 != 0);
            assertTrue(graph.degree(pair.getSecond()) % 2 != 0);
        }
    }
}
