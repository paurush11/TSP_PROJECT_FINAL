package edu.neu.csye6205.finalProject.Paurush;

import java.util.List;

import edu.neu.csye6205.finalProject.Paurush.util.*;


public class Driver {
	public static void main(String[] args) {
		Graph graph = new Graph();
		Fetch.main(args);
		graph.setNodes(Nodes.getNodes());

		for (int i = 0; i < Nodes.getNodes().size(); i++) {
			for (int j = i + 1; j < Nodes.getNodes().size(); j++) {
				graph.addEdge(new Edge(Nodes.getNode(i), Nodes.getNode(j),
						Distance.measureDistance(Nodes.getNode(i), Nodes.getNode(j))));
			}
		}

		// Run Prim's algorithm on the graph
//		GraphPlotter.plotGraph(graph);
		Graph mst = PrimAlgorithm.findMinimumSpanningTree(graph);

		// Print out the nodes and edges in the MST
		List<Node> nodes = mst.getNodes();
		List<Edge> edges = mst.getEdges();
		for (Node node : nodes) {
			System.out.println("Node " + node.getId());
		}
		for (Edge edge : edges) {
			System.out.println("Edge " + edge.getA().getId() + " - " + edge.getB().getId());
		}

	}

}
