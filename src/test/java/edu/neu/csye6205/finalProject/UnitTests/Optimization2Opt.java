package edu.neu.csye6205.finalProject.UnitTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import edu.neu.csye6205.finalProject.Paurush.Node;
import edu.neu.csye6205.finalProject.Paurush.SimulatedAnnealing.SimulatedAnnealingOptimization;
import edu.neu.csye6205.finalProject.Paurush.tactical.optimization2opt;

public class Optimization2Opt {
	//@Test
	public void testTwoOpt() {
		// Create a tour with 5 nodes
		List<Node> tour = new ArrayList<>();
		tour.add(new Node("CityA", 0, 0)); // node 1
		tour.add(new Node("CityB", 0, 1)); // node 2
		tour.add(new Node("CityC", 1, 1)); // node 3
		tour.add(new Node("CityD", 1, 0)); // node 4
		tour.add(new Node("CityE", 0.5, 0.5)); // node 5
		// Calculate the initial tour cost
		double initialCost = SimulatedAnnealingOptimization.calculateTourCost(tour);
		// Apply 2-opt optimization to the tour
		List<Node> optimizedTour = optimization2opt.twoOpt(tour);
		// Calculate the optimized tour cost
		double optimizedCost = SimulatedAnnealingOptimization.calculateTourCost(optimizedTour);
		// Verify that the optimized tour is different from the initial tour
		assertEquals(tour, optimizedTour);
		// Verify that the optimized tour is valid (i.e., visits each node exactly once)
		assertEquals(tour.size(), optimizedTour.size());
		assertTrue(optimizedTour.containsAll(tour));
		// Verify that the optimized tour is shorter than the initial tour
		assertTrue(optimizedCost < initialCost);
	}
}
