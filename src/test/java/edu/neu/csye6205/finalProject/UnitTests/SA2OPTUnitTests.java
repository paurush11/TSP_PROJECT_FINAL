package edu.neu.csye6205.finalProject.UnitTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import edu.neu.csye6205.finalProject.Paurush.Node;
import edu.neu.csye6205.finalProject.Paurush.SimulatedAnnealing.SA2opt;

public class SA2OPTUnitTests {

    @Test
    public void testSimulatedAnnealingOptimization() {
        // Create a tour of nodes
        List<Node> tour = new ArrayList<>();
        tour.add(new Node("CityA", 0, 0)); // add starting node
        tour.add(new Node("CityB", 1, 2));
        tour.add(new Node("CityC", 3, 1));
        tour.add(new Node("CityD", 4, 5));
        tour.add(new Node("CityE", 2, 3));
        tour.add(new Node("CityF", 0, 6)); // add ending node

        // Run simulated annealing optimization
        List<Node> optimizedTour = SA2opt.simulatedAnnealingOptimization(tour);

        // Check that the optimized tour has the same number of nodes as the original tour
        assertEquals(tour.size(), optimizedTour.size());

        // Check that the optimized tour has a lower cost than the original tour
        double originalTourCost = SA2opt.calculateTourCost(tour);
        double optimizedTourCost = SA2opt.calculateTourCost(optimizedTour);
        assertTrue(optimizedTourCost < originalTourCost);
    }
}
