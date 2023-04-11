package edu.neu.csye6205.finalProject.Paurush;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class PrimAlgorithm {

    public static Graph findMinimumSpanningTree(Graph graph) {
        // Create a new graph to hold the minimum spanning tree
        Graph minimumSpanningTree = new Graph();

        // Create a set to keep track of the visited nodes
        Set<Node> visitedNodes = new HashSet<>();

        // Add the first node to the visited set
        visitedNodes.add(graph.getNodes().get(0));

        // Create a priority queue to store the edges in ascending order by weight
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(graph.getEdges().size(),
                (a, b) -> Double.compare(a.getEdgeWeight(), b.getEdgeWeight()));

        // Add all the edges connected to the first node to the priority queue
        for (Edge edge : graph.getEdges()) {
            if (edge.getA().equals(visitedNodes.iterator().next()) || edge.getB().equals(visitedNodes.iterator().next())) {
                priorityQueue.offer(edge);
            }
        }

        // Iterate until all nodes have been visited
        while (visitedNodes.size() < graph.getNodes().size()) {
            // Get the edge with the smallest weight from the priority queue
            Edge smallestEdge = priorityQueue.poll();

            // Check if either endpoint of the edge has already been visited
            Node endpointA = smallestEdge.getA();
            Node endpointB = smallestEdge.getB();

            if (visitedNodes.contains(endpointA) && visitedNodes.contains(endpointB)) {
                continue;
            }

            // Add the edge to the minimum spanning tree
            minimumSpanningTree.addEdge(smallestEdge);

            // Add the unvisited endpoint to the visited set
            Node unvisitedEndpoint = visitedNodes.contains(endpointA) ? endpointB : endpointA;
            visitedNodes.add(unvisitedEndpoint);

            // Add all the edges connected to the new visited node to the priority queue
            for (Edge edge : graph.getEdges()) {
                if (edge.getA().equals(unvisitedEndpoint) || edge.getB().equals(unvisitedEndpoint)) {
                    priorityQueue.offer(edge);
                }
            }
        }

        return minimumSpanningTree;
    }
}
