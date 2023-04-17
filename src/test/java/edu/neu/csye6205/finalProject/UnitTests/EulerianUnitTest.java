package edu.neu.csye6205.finalProject.UnitTests;

import static org.junit.Assert.assertEquals;

import java.util.List;

import edu.neu.csye6205.finalProject.Paurush.CustomGraph;
import edu.neu.csye6205.finalProject.Paurush.Driver;
import edu.neu.csye6205.finalProject.Paurush.Edge;
import edu.neu.csye6205.finalProject.Paurush.Node;

public class EulerianUnitTest {
	
	//@Test
	public void testGetEulerianTour() {
		
	    // Create a sample multigraph with an Eulerian tour
		
	    CustomGraph multigraph = new CustomGraph();
	    
		Node nodeA = new Node("A", -0.016542, 51.515192);
		Node nodeB = new Node("B", -0.236815, 51.406763);
		Node nodeC = new Node("C", -0.184411, 51.495871);
		Node nodeD = new Node("D", -0.268832, 51.464685);
		Node nodeE = new Node("E", -0.098618, 51.415897);
		
		multigraph.addNode(nodeA);
		multigraph.addNode(nodeB);
		multigraph.addNode(nodeC);
		multigraph.addNode(nodeD);
		multigraph.addNode(nodeE);
		
		multigraph.addEdge(new Edge(nodeA, nodeB, 2));
		multigraph.addEdge(new Edge(nodeB, nodeC, 3));
		multigraph.addEdge(new Edge(nodeC, nodeD, 1));
		multigraph.addEdge(new Edge(nodeD, nodeE, 1));
		multigraph.addEdge(new Edge(nodeE, nodeA, 4));
		multigraph.addEdge(new Edge(nodeA, nodeB, 5));
		multigraph.addEdge(new Edge(nodeB, nodeD, 6));

	    // Get the Eulerian tour
	    List<Node> tour = Driver.getEulerianTour(multigraph);

	    // Check that the tour has the correct size and order
	    
	    assertEquals(8, tour.size());
	    assertEquals(nodeA, tour.get(0));
	    assertEquals(nodeB, tour.get(1));
	    assertEquals(nodeC, tour.get(2));
	    assertEquals(nodeD, tour.get(3));
	    assertEquals(nodeE, tour.get(4));
	    assertEquals(nodeC, tour.get(2));
	    assertEquals(nodeB, tour.get(6));
	}
}
