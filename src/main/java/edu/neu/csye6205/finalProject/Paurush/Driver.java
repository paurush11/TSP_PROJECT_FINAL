package edu.neu.csye6205.finalProject.Paurush;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import javax.swing.JFrame;

import org.jgrapht.alg.util.Pair;


import edu.neu.csye6205.finalProject.Paurush.GeneticAlgorithm.GeneticAlgorithm;
import edu.neu.csye6205.finalProject.Paurush.GraphVisualizer.GraphVisualizer;
import edu.neu.csye6205.finalProject.Paurush.SimulatedAnnealing.SA2opt;
import edu.neu.csye6205.finalProject.Paurush.SimulatedAnnealing.SA3opt;
import edu.neu.csye6205.finalProject.Paurush.SimulatedAnnealing.SimulatedAnnealingOptimization;
import edu.neu.csye6205.finalProject.Paurush.util.*;


public class Driver {
	public static CustomGraph graph;
	public static void main(String[] args) throws IOException, InterruptedException {
		
		Driver.graph = new CustomGraph();
		Fetch.main(args);
		graph.setNodes(Nodes.getNodes());

		for (int i = 0; i < Nodes.getNodes().size(); i++) {
			for (int j = i + 1; j < Nodes.getNodes().size(); j++) {
				graph.addEdge(new Edge(Nodes.getNode(i), Nodes.getNode(j),
						Distance.measureDistance(Nodes.getNode(i), Nodes.getNode(j))));
			}
		}
	
//		GraphPlotter.plotGraph(graph);
		CustomGraph mst = PrimAlgorithm.findMinimumSpanningTree(graph);
		
		
//		GraphPlotter.plotGraph(mst);
		//Now we generate perfect matching pairs
		List<Pair<Node, Node>> perfectMatchingPairs = generatePerfectMatching(mst);
		
		//Now generate MultiGraph for the above 
		
		CustomGraph Multi = generateMultigraph(mst, perfectMatchingPairs);

	
		List<Node> eulerianTour = getEulerianTour(Multi);
		List<Node> hamiltonianTour = getHamiltonianTour(eulerianTour);

		
		double hamiltonianTourDistance = calculatePathDistance(hamiltonianTour);
		double mstTourDistance = calculatePathDistance(mst);
		List<Node> hamiltonianTourCopy = new ArrayList<Node>();
		hamiltonianTour.forEach(z ->{
			 hamiltonianTourCopy.add(z);
		});
//		List<Node> opt3 = optimization3opt.threeOpt(hamiltonianTourCopy);
		List<Node>opt3TourNodes = SA3opt.simulatedAnnealingOptimization(hamiltonianTourCopy);
		List<Node> sATourNodes = SimulatedAnnealingOptimization.simulatedAnnealingOptimization(hamiltonianTourCopy);
		List<Node> geneticAlgoTourNodes = GeneticAlgorithm.optimize(hamiltonianTour);
		List<Node> opt2TourNodes =SA2opt.simulatedAnnealingOptimization(hamiltonianTourCopy);
		double threeOptTourDistance = calculatePathDistance(opt3TourNodes);
		double twoOptTourDistance= calculatePathDistance(opt2TourNodes);
		double sATourDistance = calculatePathDistance(sATourNodes);
		double geneticAlgorithmTourDistance = calculatePathDistance(geneticAlgoTourNodes);
		
		print(mst.getNodes(), "MST", mstTourDistance, mstTourDistance);
		print(hamiltonianTour, "TSP- using Christofidies", hamiltonianTourDistance, mstTourDistance);
		print(opt3TourNodes, "TSP- using threeOpt", threeOptTourDistance,mstTourDistance);
		print(sATourNodes, "TSP- using Simulated Annealing Optimization", sATourDistance,mstTourDistance);
		print(opt2TourNodes, "TSP- using twoOpt", twoOptTourDistance,mstTourDistance);
		print(geneticAlgoTourNodes, "TSP- using Genetic Algorithm", geneticAlgorithmTourDistance,mstTourDistance);
	
		NodeGraph.plot(hamiltonianTourCopy);
		CustomGraph graphFinal = createGraph(hamiltonianTour);

		GraphAnimator christofidiesGraph = new GraphAnimator(graphFinal,"TSP- using Christofidies",Color.RED,Color.BLUE);
		GraphAnimator threeOptGraph = new GraphAnimator(graphFinal,"TSP- using threeOpt",Color.RED,Color.BLUE);
		GraphAnimator twoOptGraph = new GraphAnimator(graphFinal,"TSP- using twoOpt",Color.RED,Color.BLUE);
		GraphAnimator sAOptGraph = new GraphAnimator(graphFinal,"TSP- using SAOpt",Color.RED,Color.BLUE);
		GraphAnimator geneticGraph = new GraphAnimator(graphFinal,"TSP- using Genetic",Color.RED,Color.BLUE);
		
		christofidiesGraph.animate();
		threeOptGraph.animate();
		twoOptGraph.animate();
		sAOptGraph.animate();
		geneticGraph.animate();
		
//		visualizeGraph(graphFinal, driver.graph.getNodes());
//		visualizeGraph(graphFinal);
		}
	public static void print(List<Node>nodes, String name, double distance, double mst) {
		System.out.println("---------------------------------------");
		System.out.println(name);
		System.out.print("[");
		nodes.forEach(e->{
			System.out.print(e.getId() + ",");
		});
		System.out.print("]\n");
		System.out.println("\n--- Tour Distance" + distance + "   meters");
		System.out.println("Ratio");
		System.out.println("\n--- Ratio" + distance/mst + "   meters");
		System.out.println("---------------------------------------");
	}
	public static void visualizeGraph(CustomGraph graph, List<Node> nodes) {
	    // create a new visualizer
	    GraphVisualizer visualizer = new GraphVisualizer(graph);

	    // plot all nodes
	    for (Node node : nodes) {
	        visualizer.plotNode(node);
	    }

	    // connect edges with a delay of 2ms
	    for (Edge edge : graph.getEdges()) {
	        visualizer.plotEdge(edge);
	        try {
	            Thread.sleep(2);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	    }

	    // show the final graph in the visualizer
	    JFrame frame = new JFrame();
	    frame.add(visualizer);
	    frame.setSize(1000, 1000);
	    frame.setVisible(true);
	}

	public static CustomGraph createGraph(List<Node> nodes) {
	    // create a new graph
	    CustomGraph graph = new CustomGraph();

	    // add all the nodes to the graph
	    for (Node node : nodes) {
	        graph.addNode(node);
	    }

	    // connect adjacent nodes with edges
	    for (int i = 0; i < nodes.size() - 1; i++) {
	        Node a = nodes.get(i);
	        Node b = nodes.get(i + 1);
	        double edgeWeight = Distance.measureDistance(a, b);
	        Edge edge = new Edge(a, b, edgeWeight);
	        graph.addEdge(edge);
	    }

	    // if the graph is circular, connect the last node to the first node with an edge
	    Node first = nodes.get(0);
	    Node last = nodes.get(nodes.size() - 1);
	    double edgeWeight = Distance.measureDistance(last, first);
	    Edge edge = new Edge(last, first, edgeWeight);
	    graph.addEdge(edge);

	    return graph;
	}

	
	public static List<Pair<Node, Node>> generatePerfectMatching(CustomGraph graph) {
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
	public static CustomGraph generateMultigraph(CustomGraph mst, List<Pair<Node, Node>> perfectMatchingPairs) {
	    CustomGraph multigraph = new CustomGraph();

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
	
	 public static List<Node> getEulerianTour(CustomGraph multigraph) {
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
	 public static double calculatePathDistance(CustomGraph graph) {
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
