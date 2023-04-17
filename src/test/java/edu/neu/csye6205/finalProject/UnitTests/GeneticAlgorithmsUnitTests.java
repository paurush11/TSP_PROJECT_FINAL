package edu.neu.csye6205.finalProject.UnitTests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import edu.neu.csye6205.finalProject.Paurush.Node;
import edu.neu.csye6205.finalProject.Paurush.GeneticAlgorithm.GeneticAlgorithm;

public class GeneticAlgorithmsUnitTests {
	
    @Test
    public void optimize_GivenSingleNode_ReturnsSingleNode() {
        List<Node> nodes = new ArrayList<>();
        nodes.add(new Node("A", -0.016542, 51.515192));
        List<Node> optimizedNodes = GeneticAlgorithm.optimize(nodes);
        assertEquals(nodes, optimizedNodes);
    }

//    @Test
//    public void optimize_GivenTwoNodes_ReturnsCorrectOrder() {
//        List<Node> nodes = new ArrayList<>();
//        nodes.add(new Node("A", -0.016542, 51.515192));
//        nodes.add( new Node("B", -0.236815, 51.406763));
//        List<Node> optimizedNodes = GeneticAlgorithm.optimize(nodes);
//    }

//    @Test
//    public void optimize_GivenThreeNodes_ReturnsCorrectOrder() {
//        List<Node> nodes = new ArrayList<>();
//        nodes.add(new Node("A", -0.016542, 51.515192));
//        nodes.add(new Node("B", -0.236815, 51.406763));
//        nodes.add(new Node("C", -0.184411, 51.495871));
//        List<Node> optimizedNodes = GeneticAlgorithm.optimize(nodes);
//        assertEquals(nodes, optimizedNodes);
//    }
//
//    @Test
//    public void optimize_GivenFourNodes_ReturnsCorrectOrder() {
//        List<Node> nodes = new ArrayList<>();
//        nodes.add(new Node("A", -0.016542, 51.515192));
//        nodes.add(new Node("B", -0.236815, 51.406763));
//        nodes.add(new Node("C", -0.184411, 51.495871));
//        nodes.add(new Node("D", -0.268832, 51.464685));
//        List<Node> optimizedNodes = GeneticAlgorithm.optimize(nodes);
//        assertEquals(nodes, optimizedNodes);
//    }
//
//    @Test
//    void optimize_GivenTenNodes_ReturnsCorrectOrder() {
//        List<Node> nodes = new ArrayList<>();
//        nodes.add(new Node(0, 0));
//        nodes.add(new Node(1, 1));
//        nodes.add(new Node(2, 2));
//        nodes.add(new Node(3, 3));
//        nodes.add(new Node(4, 4));
//        nodes.add(new Node(5, 5));
//        nodes.add(new Node(6, 6));
//        nodes.add(new Node(7, 7));
//        nodes.add(new Node(8, 8));
//        nodes.add(new Node(9, 9));
//        List<Node> optimizedNodes = GeneticAlgorithm.optimize(nodes);
//        assertEquals(nodes, optimizedNodes);
//    }
}
