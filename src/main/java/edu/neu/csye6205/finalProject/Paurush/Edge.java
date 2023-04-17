package edu.neu.csye6205.finalProject.Paurush;

public class Edge {
    private Node a;
    private Node b;
    private double edgeWeight;

    public Edge(Node a, Node b, double edgeWeight) {
        this.a = a;
        this.b = b;
        this.edgeWeight = edgeWeight;
    }

    public Node getA() {
        return this.a;
    }

    public Node getB() {
        return this.b;
    }

    public double getEdgeWeight() {
        return this.edgeWeight;
    }

	public String getId() {
		// TODO Auto-generated method stub
		return this.a.getName()+"-"+this.b.getName();
	}
}
