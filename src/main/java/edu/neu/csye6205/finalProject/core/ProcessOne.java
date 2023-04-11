package edu.neu.csye6205.finalProject.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.alg.cycle.HierholzerEulerianCycle;
import org.jgrapht.alg.interfaces.MatchingAlgorithm.Matching;
import org.jgrapht.alg.interfaces.SpanningTreeAlgorithm.SpanningTree;
import org.jgrapht.alg.matching.blossom.v5.KolmogorovWeightedPerfectMatching;
import org.jgrapht.alg.spanning.PrimMinimumSpanningTree;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.Multigraph;
import org.jgrapht.graph.SimpleWeightedGraph;
import org.jgrapht.graph.WeightedMultigraph;

public class ProcessOne {

	static HashMap<String, Double> mapOriginalGraph = new HashMap<>();

	public static void main(String args[]) {

		// Create a dummy weight graph

		Graph<String, DefaultWeightedEdge> graph = new SimpleWeightedGraph<String, DefaultWeightedEdge>(
				DefaultWeightedEdge.class);

		Graphs.addAllVertices(graph, List.of("0", "1", "2", "3", "4"));

//		int graph[][] = new int[][{ { 0, 2, 0, 6, 0 }
		// { 2, 0, 3, 8, 5 }
		// { 0, 3, 0, 0, 7 }
		// { 6, 8, 0, 0, 9 },
//			                        { 0, 5, 7, 9, 0 } };

		graph.setEdgeWeight(graph.addEdge("0", "1"), 2.0);
		graph.setEdgeWeight(graph.addEdge("0", "3"), 6.0);
		graph.setEdgeWeight(graph.addEdge("1", "2"), 3.0);
		graph.setEdgeWeight(graph.addEdge("1", "3"), 8.0);
		graph.setEdgeWeight(graph.addEdge("1", "4"), 5.0);
		graph.setEdgeWeight(graph.addEdge("2", "4"), 7.0);
		graph.setEdgeWeight(graph.addEdge("3", "4"), 9.0);

		graph.edgeSet().forEach(edge -> {
			mapOriginalGraph.put(graph.getEdgeSource(edge) + "-" + graph.getEdgeTarget(edge),
					graph.getEdgeWeight(edge));
		});

		// we need create an MST from this graph that is get the edges and minimum
		// weight

		PrimMinimumSpanningTree<String, DefaultWeightedEdge> mst = new PrimMinimumSpanningTree<String, DefaultWeightedEdge>(
				graph);

		SpanningTree<DefaultWeightedEdge> tree = mst.getSpanningTree(); // we have the tree

		tree.getEdges();

		Multigraph<String, DefaultWeightedEdge> mstMultiGraph = new WeightedMultigraph<>(DefaultWeightedEdge.class);

		// Graphs.addAllVertices(mstGraph, List.of("0", "1", "2", "3", "4"));

		Set<String> mstGraphVertices = new HashSet<String>();

		tree.getEdges().forEach(edge -> {
			mstGraphVertices.add(graph.getEdgeSource(edge));
			mstGraphVertices.add(graph.getEdgeTarget(edge));
		});

		Graphs.addAllVertices(mstMultiGraph, mstGraphVertices);

		for (DefaultWeightedEdge edge : tree.getEdges()) {
			String source = graph.getEdgeSource(edge);
			String target = graph.getEdgeTarget(edge);
			double weight = graph.getEdgeWeight(edge);

			mstMultiGraph.addEdge(source, target);
			mstMultiGraph.setEdgeWeight(mstMultiGraph.getEdge(source, target), weight);
		}

		// we have created the mst graph.

		// we need to do perfect matching minimum weight

		// we need to create graphs for that also

		// we need to find the odd vertices

		Graph<String, DefaultWeightedEdge> graphPerfectMatching = new SimpleWeightedGraph<String, DefaultWeightedEdge>(
				DefaultWeightedEdge.class);

		List<String> oddDegreeVertices = new ArrayList<>();

		for (String vertex : mstMultiGraph.vertexSet()) {
			int degree = mstMultiGraph.degreeOf(vertex);
			if (degree % 2 != 0) {
				oddDegreeVertices.add(String.valueOf(vertex));
			}
		}

		Graphs.addAllVertices(graphPerfectMatching, oddDegreeVertices);

		for (int i = 0; i < oddDegreeVertices.size(); i++) {
			for (int j = i + 1; j < oddDegreeVertices.size(); j++) {
				String sourceVertex = oddDegreeVertices.get(i);
				String targetVertex = oddDegreeVertices.get(j);
				double weight = mapOriginalGraph.getOrDefault(sourceVertex + "-" + targetVertex, Double.MIN_VALUE);
				if (weight == Double.MIN_VALUE) {
					graphPerfectMatching.setEdgeWeight(graphPerfectMatching.addEdge(sourceVertex, targetVertex), 2.0);

				} else {
					// we need to get the weighted edges from the original graph
					graphPerfectMatching.setEdgeWeight(graphPerfectMatching.addEdge(sourceVertex, targetVertex),
							weight);
				}
			}
		}

		// We have odd Vertices edge combination
		// We need to get perfect matching minimum edges

		KolmogorovWeightedPerfectMatching<String, DefaultWeightedEdge> weightedPerfectMatching = new KolmogorovWeightedPerfectMatching<>(
				graphPerfectMatching);

		Matching<String, DefaultWeightedEdge> matcher = weightedPerfectMatching.getMatching();

		Set<DefaultWeightedEdge> perfectMatchingMinWeighEdges = matcher.getEdges();

		perfectMatchingMinWeighEdges.forEach(perfectMatchedge -> {
			String source = graph.getEdgeSource(perfectMatchedge);
			String target = graph.getEdgeTarget(perfectMatchedge);
			double weight = graph.getEdgeWeight(perfectMatchedge);

			mstMultiGraph.setEdgeWeight(mstMultiGraph.addEdge(source, target), weight);
		});

		// we need find the eulerian tour of multigraph

		HierholzerEulerianCycle<String, DefaultWeightedEdge> eulerianCycle = new HierholzerEulerianCycle<String, DefaultWeightedEdge>();

		System.out.println(eulerianCycle.getEulerianCycle(mstMultiGraph));
		
		// we need get the hamaltonian cycle for the above graph
		
		
        List<DefaultWeightedEdge> eulerianTour = (List<DefaultWeightedEdge>) eulerianCycle.getEulerianCycle(mstMultiGraph).getEdgeList();

        Set<String> visited = new HashSet<>();
        List<String> hamiltonianTour = new ArrayList<>();

        for (DefaultWeightedEdge edge : eulerianTour) {
            String source = mstMultiGraph.getEdgeSource(edge);
            String target = mstMultiGraph.getEdgeTarget(edge);

            if (!visited.contains(source)) {
                visited.add(source);
                hamiltonianTour.add(source);
            }
            if (!visited.contains(target)) {
                visited.add(target);
                hamiltonianTour.add(target);
            }
        }

        hamiltonianTour.add(hamiltonianTour.get(0)); // add the starting vertex to the end of the tour

        System.out.println(hamiltonianTour);
	}
}