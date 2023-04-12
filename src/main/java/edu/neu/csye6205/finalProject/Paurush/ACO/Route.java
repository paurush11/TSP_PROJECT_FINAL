package edu.neu.csye6205.finalProject.Paurush.ACO;
import java.util.ArrayList;
import java.util.Arrays;

import edu.neu.csye6205.finalProject.Paurush.Node;
public class Route {
	private ArrayList<Node> cities;
    private double distance;
    public Route(ArrayList<Node> cities, double distance) {
    	this.cities = cities;
        this.distance = distance;
    }
    public ArrayList<Node> getCities() { return cities; }
	public double getDistance() { return distance; }
	public String toString(){ return Arrays.toString(cities.toArray()) +" | "+distance; }
}
