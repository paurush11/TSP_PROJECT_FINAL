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

public class MultigraphUnitTests {
	
	@Test
	public void testGenerateMultigraph() {
	    // Create a sample MST
	    CustomGraph mst = new CustomGraph();
		Node nodeA = new Node("A", -0.016542, 51.515192);
		Node nodeB = new Node("B", -0.236815, 51.406763);
		Node nodeC = new Node("C", -0.184411, 51.495871);
	    mst.addNode(nodeA);
	    mst.addNode(nodeB);
	    mst.addNode(nodeC);
	    mst.addEdge(new Edge(nodeA, nodeB, 2));
	    mst.addEdge(new Edge(nodeA, nodeC, 5));

	    // Create some sample perfect matching pairs
	    List<Pair<Node, Node>> perfectMatchingPairs = new ArrayList<>();
	    perfectMatchingPairs.add(new Pair<>(nodeB, nodeC));
	    perfectMatchingPairs.add(new Pair<>(nodeA, nodeB));

	    // Generate the multigraph
	    CustomGraph multigraph = Driver.generateMultigraph(mst, perfectMatchingPairs);

	    // Check that the multigraph contains all the nodes from the MST
	    assertEquals(3, multigraph.getNodes().size());
	    assertTrue(multigraph.getNodes().contains(nodeA));
	    assertTrue(multigraph.getNodes().contains(nodeB));
	    assertTrue(multigraph.getNodes().contains(nodeC));
	    
	    multigraph.getEdges().forEach(a -> {
	    	System.out.println(a.getId()+ "-" + a.getEdgeWeight());
	    });

	    assertEquals("2.0", String.valueOf(multigraph.getEdge(nodeA, nodeB).getEdgeWeight()));
    	assertEquals("5.0", String.valueOf(multigraph.getEdge(nodeA, nodeC).getEdgeWeight()));
    	assertEquals("11507.607354809319", String.valueOf(multigraph.getEdge(nodeB, nodeC).getEdgeWeight()));
    	assertEquals("27330.458645378687", String.valueOf(multigraph.getEdges().get(3).getEdgeWeight()));
    	
    	
	}
}
