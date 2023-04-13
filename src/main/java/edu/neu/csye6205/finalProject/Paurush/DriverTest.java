//package edu.neu.csye6205.finalProject.Paurush;
//
//import org.junit.Test;
//
//import java.util.List;
//import java.util.ArrayList;
//import static org.junit.Assert.*;
//
//
//public class DriverTest {
//	 @Test
//	    public void testCalculatePathDistance() {
//	        List<Node> path = new ArrayList<>();
//	        path.add(new Node(0, 0, 0));
//	        path.add(new Node(1, 3, 4));
//	        path.add(new Node(2, 6, 8));
//
//	        double expectedDistance = 10.0;
//	        double actualDistance = Driver.calculatePathDistance(path);
//
//	        assertEquals(expectedDistance, actualDistance, 1e-9);
//	    }
//
//	    @Test
//	    public void testCreateGraph() {
//	        List<Node> nodes = new ArrayList<>();
//	        nodes.add(new Node(0, 0, 0));
//	        nodes.add(new Node(1, 3, 4));
//	        nodes.add(new Node(2, 6, 8));
//
//	        CustomGraph graph = Driver.createGraph(nodes);
//
//	        assertTrue(graph.getNodes().containsAll(nodes));
//	        assertEquals(3, graph.getEdges().size());
//	    }
//}
