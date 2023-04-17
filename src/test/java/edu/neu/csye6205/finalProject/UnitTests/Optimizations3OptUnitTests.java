package edu.neu.csye6205.finalProject.UnitTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import edu.neu.csye6205.finalProject.Paurush.Node;
import edu.neu.csye6205.finalProject.Paurush.tactical.optimization3opt;

public class Optimizations3OptUnitTests {

	//@Test
	void testThreeOpt() {
		// Create a list of nodes representing a route
		List<Node> route = new ArrayList<Node>();
		route.add(new Node("CityA", 0, 0));
		route.add(new Node("CityB", 1, 1));
		route.add(new Node("CityC", 2, 2));
		route.add(new Node("CityD", 3, 3));
		route.add(new Node("CityE", 4, 4));
		route.add(new Node("CityF", 5, 5));

		// Apply the 3-opt algorithm to the route
		List<Node> optimizedRoute = optimization3opt.threeOpt(route);

		// Verify that the optimized route has the same number of nodes as the original
		// route
		assertEquals(route.size(), optimizedRoute.size());

		// Verify that the optimized route has a shorter distance than the original
		// route
		double originalDistance = optimization3opt.calculateDistance(route);
		double optimizedDistance = optimization3opt.calculateDistance(optimizedRoute);
		assertTrue(optimizedDistance < originalDistance);
	}
}
