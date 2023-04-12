package edu.neu.csye6205.finalProject.Paurush.SimulatedAnnealing;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import edu.neu.csye6205.finalProject.Paurush.Node;
import edu.neu.csye6205.finalProject.Paurush.util.Distance;
public class SimulatedAnnealingOptimization {
	public static final double RATE_OF_COOLING = 0.95;
    public static final double INITIAL_TEMPERATURE = 1000;
    public static final int ITERATIONS = 100000;
	public static List<Node> simulatedAnnealingOptimization(List<Node> tour) {
//	    // Copy the input tour to avoid modifying the original tour
//	    List<Node> currentTour = new ArrayList<>(tour);
//	    
//	    // Calculate the initial cost of the tour
//	    double currentCost = calculateTourCost(currentTour);
//
//	    // Create a random number generator
//	    Random random = new Random();
//
//	    // Start the Simulated Annealing algorithm
//	    for (int i = 0; i < ITERATIONS; i++) {
//	        // Calculate the current temperature
//	        double currentTemperature = INITIAL_TEMPERATURE / (1 + RATE_OF_COOLING * i);
//
//	        // Generate two random indices for swapping nodes
//	        int index1 = random.nextInt(currentTour.size());
//	        int index2 = random.nextInt(currentTour.size());
//	        while (index1 == index2) {
//	            index2 = random.nextInt(currentTour.size());
//	        }
//
//	        // Swap the nodes at the two indices
//	        Collections.swap(currentTour, index1, index2);
//
//	        // Calculate the cost of the new tour
//	        double newCost = calculateTourCost(currentTour);
//
//	        // Calculate the probability of accepting the new tour
//	        double acceptanceProbability = Math.exp((currentCost - newCost) / currentTemperature);
//
//	        // Decide whether to accept the new tour or not
//	        if (newCost < currentCost || random.nextDouble() < acceptanceProbability) {
//	            currentCost = newCost;
//	        } else {
//	            // Revert the swap if the new tour is not accepted
//	            Collections.swap(currentTour, index1, index2);
//	        }
//	    }
//
//	    return currentTour;
		
		List<Node> currentSolution = new ArrayList<>(tour);
        List<Node> bestSolution = new ArrayList<>(currentSolution);
        double currentEnergy = calculateTourCost(currentSolution);
        double bestEnergy = currentEnergy;
        double temperature = INITIAL_TEMPERATURE;

        for (int i = 0; i < ITERATIONS; i++) {
            List<Node> newSolution = generateNeighbor(currentSolution);
            double newEnergy = calculateTourCost(newSolution);
            double deltaEnergy = newEnergy - currentEnergy;
            if (deltaEnergy < 0 || Math.exp(-deltaEnergy / temperature) > Math.random()) {
                currentSolution = newSolution;
                currentEnergy = newEnergy;
                if (currentEnergy < bestEnergy) {
                    bestSolution = currentSolution;
                    bestEnergy = currentEnergy;
                }
            }
            temperature *= RATE_OF_COOLING;
        }
        return bestSolution;

	}
	private static List<Node> generateNeighbor(List<Node> nodes) {
        int size = nodes.size();
        int i = (int) (Math.random() * size);
        int j = (int) (Math.random() * size);
        while (i == j) {
            j = (int) (Math.random() * size);
        }
        if (i > j) {
            int temp = i;
            i = j;
            j = temp;
        }
        List<Node> neighbor = new ArrayList<>(nodes.subList(0, i));
        neighbor.addAll(new ArrayList<>(nodes.subList(i, j + 1)));
        Collections.reverse(neighbor.subList(i, j + 1));
        neighbor.addAll(new ArrayList<>(nodes.subList(j + 1, size)));
        return neighbor;
    }

	private static double calculateTourCost(List<Node> tour) {
	    double cost = 0;
	    for (int i = 0; i < tour.size() - 1; i++) {
	        Node currentNode = tour.get(i);
	        Node nextNode = tour.get(i + 1);
	        cost += Distance.measureDistance(currentNode, nextNode);
	    }
	    // Add the distance from the last node to the first node to complete the tour
	    cost += Distance.measureDistance(tour.get(tour.size() - 1), tour.get(0));
	    return cost;
	}
}
