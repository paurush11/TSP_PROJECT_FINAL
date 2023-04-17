package edu.neu.csye6205.finalProject.Paurush.util;

import java.util.ArrayList;
import java.util.List;

import edu.neu.csye6205.finalProject.Paurush.Node;



public class Nodes {
	public static List<Node> nodes;

	public static void addNode(Node n) {
		if (nodes == null) {
			nodes = new ArrayList<>();
		}
		nodes.add(n);
	}

	public static Node getNode(int index) {
		return nodes.get(index);
	}

	public static int numberOfNode() {
		return nodes.size();
	}

	public static List<Node> getNodes() {
		return nodes;
	}

	public static void remove(Node n) {
		for (int i = 0; i < nodes.size(); i++) {
			if (nodes.contains(n)) {
				nodes.remove(n);
			}
		}
	}
	
	

}
