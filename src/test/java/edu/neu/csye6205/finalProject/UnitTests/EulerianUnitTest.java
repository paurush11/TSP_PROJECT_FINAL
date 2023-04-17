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

public class EulerianUnitTest {
	
	@Test
	public void testGetEulerianTour() {
		
	    // Create a sample multigraph with an Eulerian tour
		
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
		
	    assertEquals(6, eulerianTour.size());
	    
	    assertEquals(Nodes.getNode(1), eulerianTour.get(0));
	    assertEquals(Nodes.getNode(3), eulerianTour.get(1));
	    assertEquals(Nodes.getNode(2), eulerianTour.get(2));
	    assertEquals(Nodes.getNode(4), eulerianTour.get(3));
	    assertEquals(Nodes.getNode(0), eulerianTour.get(4));
	    assertEquals(Nodes.getNode(1), eulerianTour.get(5));
	}
}
