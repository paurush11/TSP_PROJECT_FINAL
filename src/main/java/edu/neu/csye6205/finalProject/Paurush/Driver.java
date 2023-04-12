package edu.neu.csye6205.finalProject.Paurush;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.stream.IntStream;

import org.jgrapht.alg.util.Pair;


import edu.neu.csye6205.finalProject.Paurush.ACO.runACO;
import edu.neu.csye6205.finalProject.Paurush.SimulatedAnnealing.SimulatedAnnealingOptimization;
import edu.neu.csye6205.finalProject.Paurush.tactical.optimization2opt;
import edu.neu.csye6205.finalProject.Paurush.tactical.optimization3opt;
import edu.neu.csye6205.finalProject.Paurush.util.*;


public class Driver {
	public static Graph graph;
	public static void main(String[] args) throws IOException {
		
		Driver driver = new Driver();
		Driver.graph = new Graph();
		Fetch.main(args);
		graph.setNodes(Nodes.getNodes());

		for (int i = 0; i < Nodes.getNodes().size(); i++) {
			for (int j = i + 1; j < Nodes.getNodes().size(); j++) {
				graph.addEdge(new Edge(Nodes.getNode(i), Nodes.getNode(j),
						Distance.measureDistance(Nodes.getNode(i), Nodes.getNode(j))));
			}
		}

		// Run Prim's algorithm on the graph
//		List<Node> nodes = graph.getNodes();
//		List<Edge> edges = graph.getEdges();
//		for (Node node : nodes) {
//			System.out.println("Node " + node.getId());
//		}
//		for (Edge edge : edges) {
//			System.out.println("Edge " + edge.getA().getId() + " - " + edge.getB().getId());
//		}
//		GraphPlotter.plotGraph(graph);
		Graph mst = PrimAlgorithm.findMinimumSpanningTree(graph);
		GraphPlotter.plotGraph(mst);
System.out.println("----------------------------------");
		// Print out the nodes and edges in the MST
		List<Node> nodes2 = mst.getNodes();
		List<Edge> edges2 = mst.getEdges();
		
		
		//Now we generate perfect matching pairs
		List<Pair<Node, Node>> perfectMatchingPairs = generatePerfectMatching(mst);
		
		//Now generate MultiGraph for the above 
		System.out.println("----------------------------------");
		Graph Multi = generateMultigraph(mst, perfectMatchingPairs);
		List<Node> nodes3 = Multi.getNodes();
		List<Edge> edges3 = Multi.getEdges();
		System.out.println(edges2.size());
		System.out.println(edges3.size());
		
		List<Node> eulerianTour = getEulerianTour(Multi);
		for(Node n : eulerianTour) {
			System.out.print(n + ",");
		}
		System.out.println("\n---");
		List<Node> hamiltonianTour = getHamiltonianTour(eulerianTour);
		for(Node n : hamiltonianTour) {
			System.out.print(n + ",");
		}
		
		double d = calculatePathDistance(hamiltonianTour);
		double x = calculatePathDistance(mst);
		List<Node> opt3 = optimization3opt.threeOpt(hamiltonianTour);
		List<Node> opt2 = optimization2opt.twoOpt(hamiltonianTour);
		List<Node> SA = SimulatedAnnealingOptimization.simulatedAnnealingOptimization(hamiltonianTour);
		double three = calculatePathDistance(opt3);
		double two = calculatePathDistance(opt2);
		double sa_val = calculatePathDistance(SA);
		System.out.println("\n--- Hamiltonian Tour   " + d + "   Miles");
		System.out.println("\n--- MST   " + x + "   Miles");
		System.out.println("\n--- Three Opt   " + three + "   Miles");
		System.out.println("\n--- Two Opt   " + two + "   Miles");
		System.out.println("\n--- SA   " + sa_val + "   Miles");
		System.out.println();
		System.out.println("\n--- Hamiltonian Tour/MST   " + d/x);
		System.out.println("\n--- Three Opt/MST   " + three/x);
		System.out.println("\n--- Two Opt/MST   " + two/x);
		System.out.println("\n--- SA/MST   " + sa_val/x);
		NodeGraph.plot(hamiltonianTour);
		
		///ACO
//		runACO r = new runACO();
//		System.out.println("> "+r.getNumberOfAnts() + " Artificial Ants ...");
//		runACO.printHeading();
//		edu.neu.csye6205.finalProject.Paurush.ACO.AntColonyOptimization aco = new edu.neu.csye6205.finalProject.Paurush.ACO.AntColonyOptimization();
//		runACO.initialRoute = (ArrayList<Node>) hamiltonianTour;
//		IntStream.range(1, r.getNumberOfAnts()).forEach(y -> {
//			r.getExecutorCompletionService().submit(new edu.neu.csye6205.finalProject.Paurush.ACO.Ant(aco, y));
//			r.setActiveAnts(r.getActiveAnts() + 1);
//			if (Math.random() > runACO.getProcessingCycleProbability()) r.processAnts();
//		});
//		r.processAnts();
//		runACO.getExecutorService().shutdownNow();
//		System.out.println("\nOptimal Route : "+Arrays.toString(r.getShortestRoute().getCities().toArray()));
//		System.out.println("w/ Distance   : " + r.getShortestRoute().getDistance());
	}
	
	public static List<Pair<Node, Node>> generatePerfectMatching(Graph graph) {
	    List<Pair<Node, Node>> matchingPairs = new ArrayList<>();
	    
	    // Find all the odd-degree vertices in the graph
	    List<Node> oddVertices = new ArrayList<>();
	    for (Node node : graph.getNodes()) {
	    	
	    	
	        if (graph.degree(node) % 2 != 0) {
	            oddVertices.add(node);
	        }
	    }
	   
	    
	    // Pair up the odd-degree vertices
	    while (oddVertices.size() > 1) {
	        Node u = oddVertices.remove(0);
	        double minDistance = Double.MAX_VALUE;
	        Node closestVertex = null;
	        for (Node v : oddVertices) {
	            double distance = Distance.measureDistance(u, v);
	            if (distance < minDistance) {
	                minDistance = distance;
	                closestVertex = v;
	            }
	        }
	        oddVertices.remove(closestVertex);
	        matchingPairs.add(new Pair<Node, Node>(u, closestVertex));
	    }
	    
	    return matchingPairs;
	}
	public static Graph generateMultigraph(Graph mst, List<Pair<Node, Node>> perfectMatchingPairs) {
	    Graph multigraph = new Graph();

	    // Add all the nodes from the MST
	    List<Node> nodes = mst.getNodes();
	    for (Node node : nodes) {
	        multigraph.addNode(node);
	    }

	    // Add all the edges from the MST
	    List<Edge> mstEdges = mst.getEdges();
	    for (Edge edge : mstEdges) {
	        multigraph.addEdge(edge);
	    }

	    // Add edges from the perfect matching pairs
	    for (Pair<Node, Node> pair : perfectMatchingPairs) {
	        Node node1 = pair.getFirst();
	        Node node2 = pair.getSecond();
	        double weight = Distance.measureDistance(node1, node2); // Get the weight as the Euclidean distance between the nodes
	        Edge edge = new Edge(node1, node2, weight);
	        multigraph.addEdge(edge);
	    }

	    return multigraph;
	}
	
	 public static List<Node> getEulerianTour(Graph multigraph) {
	        List<Node> tour = new ArrayList<>();
	        Map<Node, List<Node>> adjList = new HashMap<>();

	        // Create adjacency list from multigraph
	        for (Node node : multigraph.getNodes()) {
	            adjList.put(node, new ArrayList<>());
	            for (Edge edge : multigraph.getEdges()) {
	                if (edge.getA().equals(node)) {
	                    adjList.get(node).add(edge.getB());
	                } else if (edge.getB().equals(node)) {
	                    adjList.get(node).add(edge.getA());
	                }
	            }
	        }

	        // Find starting node for tour (any node with odd degree)
	        Node startNode = null;
	        for (Node node : adjList.keySet()) {
	            if (adjList.get(node).size() % 2 != 0) {
	                startNode = node;
	                break;
	            }
	        }

	        // If no node has odd degree, start at any node
	        if (startNode == null) {
	            startNode = adjList.keySet().iterator().next();
	        }

	        // Perform Hierholzer's algorithm
	        Deque<Node> stack = new ArrayDeque<>();
	        stack.push(startNode);
	        while (!stack.isEmpty()) {
	            Node currNode = stack.peek();
	            if (adjList.get(currNode).isEmpty()) {
	                tour.add(stack.pop());
	            } else {
	                Node nextNode = adjList.get(currNode).remove(0);
	                adjList.get(nextNode).remove(currNode);
	                stack.push(nextNode);
	            }
	        }

	        // Reverse the tour to get it in the correct order
	        Collections.reverse(tour);

	        return tour;
	    }
	 public static List<Node> getHamiltonianTour(List<Node> eulerianTour) {
		    List<Node> hamiltonianTour = new ArrayList<>();
		    Set<Node> visitedNodes = new HashSet<>();

		    for (Node node : eulerianTour) {
		        if (!visitedNodes.contains(node)) {
		            hamiltonianTour.add(node);
		            visitedNodes.add(node);
		        }
		    }

		    // Connect the last node to the first node to create a cycle
		    hamiltonianTour.add(eulerianTour.get(0));

		    return hamiltonianTour;
		}
	 public static double calculatePathDistance(List<Node> path) {
		    double distance = 0.0;
		   
		    for (int i = 0; i < path.size() - 1; i++) {
		        Node node1 = path.get(i);
		        Node node2 = path.get(i + 1);
		      
		        
		        distance += Distance.measureDistance(node1, node2);
		    }
		    return distance;
		}
	 public static double calculatePathDistance(Graph graph) {
		    double distance = 0.0;
		    Set<Node> visitedNodes = new HashSet<>();
		    visitedNodes.add(graph.getNodes().get(0));

		    PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(graph.getEdges().size(),
		            (a, b) -> Double.compare(a.getEdgeWeight(), b.getEdgeWeight()));

		    for (Edge edge : graph.getEdges()) {
		        if (edge.getA().equals(visitedNodes.iterator().next()) || edge.getB().equals(visitedNodes.iterator().next())) {
		            priorityQueue.offer(edge);
		        }
		    }

		    while (visitedNodes.size() < graph.getNodes().size()) {
		        Edge smallestEdge = priorityQueue.poll();
		        Node endpointA = smallestEdge.getA();
		        Node endpointB = smallestEdge.getB();

		        if (visitedNodes.contains(endpointA) && visitedNodes.contains(endpointB)) {
		            continue;
		        }

		        distance += smallestEdge.getEdgeWeight();

		        Node unvisitedEndpoint = visitedNodes.contains(endpointA) ? endpointB : endpointA;
		        visitedNodes.add(unvisitedEndpoint);

		        for (Edge edge : graph.getEdges()) {
		            if (edge.getA().equals(unvisitedEndpoint) || edge.getB().equals(unvisitedEndpoint)) {
		                priorityQueue.offer(edge);
		            }
		        }
		    }

		    return distance;
		}


}
