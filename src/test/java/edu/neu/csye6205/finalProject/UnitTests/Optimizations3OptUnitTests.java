package edu.neu.csye6205.finalProject.UnitTests;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.jgrapht.alg.util.Pair;
import org.junit.Test;

import edu.neu.csye6205.finalProject.Paurush.CustomGraph;
import edu.neu.csye6205.finalProject.Paurush.Driver;
import edu.neu.csye6205.finalProject.Paurush.Edge;
import edu.neu.csye6205.finalProject.Paurush.Node;
import edu.neu.csye6205.finalProject.Paurush.PrimAlgorithm;
import edu.neu.csye6205.finalProject.Paurush.tactical.optimization3opt;
import edu.neu.csye6205.finalProject.Paurush.util.Distance;
import edu.neu.csye6205.finalProject.Paurush.util.Nodes;

public class Optimizations3OptUnitTests {

	@Test
	public void testThreeOpt() {
	    CustomGraph graph = new CustomGraph();
	    
		Node nodeA = new Node("A", -0.016542, 51.515192);
		Node nodeB = new Node("B", -0.236815, 51.406763);
		Node nodeC = new Node("C", -0.184411, 51.495871);
		Node nodeD = new Node("D", -0.268832, 51.464685);
		Node nodeE = new Node("E", -0.098618, 51.415897);
		
		
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
		
		List<Pair<Node, Node>> perfectMatchingPairs = Driver.generatePerfectMatching(mst);
		
		//Now generate MultiGraph for the above 
		System.out.println("----------------------------------");
		CustomGraph Multi = Driver.generateMultigraph(mst, perfectMatchingPairs);
		
		List<Node> eulerianTour = Driver.getEulerianTour(Multi);
		
		List<Node> hamiltonianTour = Driver.getHamiltonianTour(eulerianTour);
		
		double d = Driver.calculatePathDistance(hamiltonianTour);

		List<Node> hamiltonianTourCopy = new ArrayList<Node>();
		hamiltonianTour.forEach(z ->{
			 hamiltonianTourCopy.add(z);
		});
		
		List<Node> opt3 = optimization3opt.threeOpt(hamiltonianTourCopy);
		
		double three = Driver.calculatePathDistance(opt3);
		
		assertTrue(three < d); 
	}
}
