package edu.neu.csye6205.finalProject.UnitTests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import edu.neu.csye6205.finalProject.Paurush.CustomGraph;
import edu.neu.csye6205.finalProject.Paurush.Edge;
import edu.neu.csye6205.finalProject.Paurush.Node;
import edu.neu.csye6205.finalProject.Paurush.PrimAlgorithm;

public class MinimumSpanningTreeTest {

	@Test
    public void testFindMinimumSpanningTree() {
        // Create a sample graph
        CustomGraph graph = new CustomGraph();
        Node nodeA = new Node("A", -0.016542,51.515192);
        Node nodeB = new Node("B", -0.236815,51.406763);
        Node nodeC = new Node("C", -0.184411,51.495871);
        Node nodeD = new Node("D", -0.268832,51.464685);
        Node nodeE = new Node("E", -0.098618,51.415897);
        
        graph.addNode(nodeA);
        graph.addNode(nodeB);
        graph.addNode(nodeC);
        graph.addNode(nodeD);
        graph.addNode(nodeE);
        
        graph.addEdge(new Edge(nodeA, nodeB, 2));
        graph.addEdge(new Edge(nodeA, nodeC, 3));
        graph.addEdge(new Edge(nodeB, nodeC, 1));
        graph.addEdge(new Edge(nodeB, nodeD, 1));
        graph.addEdge(new Edge(nodeC, nodeD, 4));
        graph.addEdge(new Edge(nodeC, nodeE, 5));
        graph.addEdge(new Edge(nodeD, nodeE, 6));

        // Get the minimum spanning tree
        CustomGraph minimumSpanningTree = PrimAlgorithm.findMinimumSpanningTree(graph);

        // Check that the minimum spanning tree has the correct number of nodes and edges
        assertEquals(5, minimumSpanningTree.getNodes().size());
        assertEquals(4, minimumSpanningTree.getEdges().size());

        // Check that the minimum spanning tree has the correct edges
    	assertEquals("A-B", minimumSpanningTree.getEdge(nodeA, nodeB).getId());
    	assertEquals("B-C", minimumSpanningTree.getEdge(nodeB, nodeC).getId());
    	assertEquals("B-D", minimumSpanningTree.getEdge(nodeB, nodeD).getId());
    	assertEquals("C-E", minimumSpanningTree.getEdge(nodeC, nodeE).getId());
    	
        // Check that the minimum spanning tree has the correct edges
    	assertEquals("2.0", String.valueOf(minimumSpanningTree.getEdge(nodeA, nodeB).getEdgeWeight()));
    	assertEquals("1.0", String.valueOf(minimumSpanningTree.getEdge(nodeB, nodeC).getEdgeWeight()));
    	assertEquals("1.0", String.valueOf(minimumSpanningTree.getEdge(nodeB, nodeD).getEdgeWeight()));
    	assertEquals("5.0", String.valueOf(minimumSpanningTree.getEdge(nodeC, nodeE).getEdgeWeight()));
    }
}
