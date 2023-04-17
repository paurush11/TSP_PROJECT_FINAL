package edu.neu.csye6205.finalProject.UnitTests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import edu.neu.csye6205.finalProject.Paurush.Node;
import edu.neu.csye6205.finalProject.Paurush.SimulatedAnnealing.SimulatedAnnealingOptimization;

public class SimulatedAnnealingUnitTests {

	    //@Test
	    public void testSimulatedAnnealingOptimization() {
	        // Create a sample tour
	        List<Node> tour = new ArrayList<>();
	        
			tour.add(new Node("CityA", -0.016542, 51.515192));
			tour.add(new Node("CityB", -0.236815, 51.406763));
			tour.add(new Node("CityC", -0.184411, 51.495871));
			tour.add(new Node("CityD", -0.268832, 51.464685));

	        // Run the optimization
	        List<Node> optimizedTour = SimulatedAnnealingOptimization.simulatedAnnealingOptimization(tour);

	        // Verify that the optimized tour has the same nodes as the original tour
	        assertEquals(tour.size(), optimizedTour.size());
	        for (int i = 0; i < tour.size(); i++) {
	            Node originalNode = tour.get(i);
	            Node optimizedNode = optimizedTour.get(i);
	            assertEquals(originalNode.getLatitude(), optimizedNode.getLatitude(), 0.001);
	            assertEquals(originalNode.getLongitude(), optimizedNode.getLongitude(), 0.001);
	        }

	        // Verify that the optimized tour has a lower cost than the original tour
	        double originalCost = SimulatedAnnealingOptimization.calculateTourCost(tour);
	        double optimizedCost = SimulatedAnnealingOptimization.calculateTourCost(optimizedTour);
	        
	        assertEquals(true, optimizedCost < originalCost);
	    }
	}
