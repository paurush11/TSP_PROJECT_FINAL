package edu.neu.csye6205.finalProject.UnitTests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.jgrapht.alg.util.Pair;
import org.junit.Test;

import edu.neu.csye6205.finalProject.Paurush.CustomGraph;
import edu.neu.csye6205.finalProject.Paurush.Driver;
import edu.neu.csye6205.finalProject.Paurush.Edge;
import edu.neu.csye6205.finalProject.Paurush.Node;
import edu.neu.csye6205.finalProject.Paurush.PrimAlgorithm;
import edu.neu.csye6205.finalProject.Paurush.util.Distance;
import edu.neu.csye6205.finalProject.Paurush.util.Nodes;

public class HamaltonianUnitTests {
	
	@Test
	public void testGetHamiltonianTour() {
	    // Create a sample Eulerian tour
	    CustomGraph graph = new CustomGraph();
	    
		Node nodeA = new Node("A", -0.016542, 51.515192);
		Node nodeB = new Node("B", -0.236815, 51.406763);
		Node nodeC = new Node("C", -0.184411, 51.495871);
		Node nodeD = new Node("D", -0.268832, 51.464685);
		Node nodeE = new Node("E", -0.098618, 51.415897);
		
		Nodes.nodes = new ArrayList<Node>();
		
		Nodes.addNode(nodeA);
		Nodes.addNode(nodeB);
		Nodes.addNode(nodeC);
		Nodes.addNode(nodeD);
		Nodes.addNode(nodeE);
		
		graph.setNodes(Nodes.getNodes());

		
		for (int i = 0; i < Nodes.getNodes().size(); i++) {
			for (int j = i + 1; j < Nodes.getNodes().size(); j++) {
				graph.addEdge(new Edge(Nodes.getNode(i), Nodes.getNode(j),
						Distance.measureDistance(Nodes.getNode(i), Nodes.getNode(j))));
			}
		}

		CustomGraph mst = PrimAlgorithm.findMinimumSpanningTree(graph);
		
		//Now we generate perfect matching pairs
		List<Pair<Node, Node>> perfectMatchingPairs = Driver.generatePerfectMatching(mst);
		
		//Now generate MultiGraph for the above 
		System.out.println("----------------------------------");
		CustomGraph Multi = Driver.generateMultigraph(mst, perfectMatchingPairs);
		
		List<Node> eulerianTour = Driver.getEulerianTour(Multi);
		
		List<Node> hamiltonianTour = Driver.getHamiltonianTour(eulerianTour);

		List<Node> hamiltonianTourCopy = new ArrayList<Node>();
		hamiltonianTour.forEach(z ->{
			 hamiltonianTourCopy.add(z);
		});
		
	    for (Node node : hamiltonianTour) {
	    	System.out.println(node.getName());
	    }
	    
	    System.out.println();
	    
	    for (Node node : Nodes.getNodes()) {
	    	System.out.println(node.getName());
	    }
		
	    assertEquals(6, hamiltonianTourCopy.size());
	    
	    assertEquals(Nodes.getNode(0), hamiltonianTourCopy.get(0));
	    assertEquals(Nodes.getNode(4), hamiltonianTourCopy.get(1));
	    assertEquals(Nodes.getNode(2), hamiltonianTourCopy.get(2));
	    assertEquals(Nodes.getNode(3), hamiltonianTourCopy.get(3));
	    assertEquals(Nodes.getNode(1), hamiltonianTourCopy.get(4));
	    assertEquals(Nodes.getNode(0), hamiltonianTourCopy.get(5));
	}
}
