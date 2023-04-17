package edu.neu.csye6205.finalProject.UnitTests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import edu.neu.csye6205.finalProject.Paurush.Driver;
import edu.neu.csye6205.finalProject.Paurush.Node;

public class HamaltonianUnitTests {
	
	@Test
	public void testGetHamiltonianTour() {
	    // Create a sample Eulerian tour
	    List<Node> eulerianTour = new ArrayList<>();
	    Node node1 = new Node("A", 0, 0);
	    Node node2 = new Node("B", 1, 0);
	    Node node3 = new Node("C", 2, 0);
	    Node node4 = new Node("D", 3, 0);
	    eulerianTour.add(node1);
	    eulerianTour.add(node2);
	    eulerianTour.add(node3);
	    eulerianTour.add(node4);
	    eulerianTour.add(node1);
	    eulerianTour.add(node3);
	    eulerianTour.add(node2);

	    // Get the Hamiltonian tour
	    List<Node> hamiltonianTour = Driver.getHamiltonianTour(eulerianTour);

	    // Check that the tour has the correct size and order
	    assertEquals(5, hamiltonianTour.size());
	    assertEquals(node1, hamiltonianTour.get(0));
	    assertEquals(node2, hamiltonianTour.get(1));
	    assertEquals(node3, hamiltonianTour.get(2));
	    assertEquals(node4, hamiltonianTour.get(3));
	    assertEquals(node1, hamiltonianTour.get(4));
	}
}
