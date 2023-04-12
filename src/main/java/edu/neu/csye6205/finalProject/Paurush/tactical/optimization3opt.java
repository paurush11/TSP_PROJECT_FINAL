package edu.neu.csye6205.finalProject.Paurush.tactical;

import java.util.ArrayList;
import java.util.List;

import edu.neu.csye6205.finalProject.Paurush.Node;
import edu.neu.csye6205.finalProject.Paurush.util.Distance;

public class optimization3opt {
	public static List<Node> threeOpt(List<Node> route) {
	    boolean improved = true;
	    while (improved) {
	        improved = false;
	        for (int i = 0; i < route.size() - 5; i++) {
	            for (int j = i + 2; j < route.size() - 3; j++) {
	                for (int k = j + 2; k < route.size() - 1; k++) {
	                    List<Node> newRoute = new ArrayList<Node>();
	                    // First segment of the route remains the same
	                    for (int x = 0; x <= i; x++) {
	                        newRoute.add(route.get(x));
	                    }
	                    // 2-opt swap
	                    for (int x = j; x >= i + 1; x--) {
	                        newRoute.add(route.get(x));
	                    }
	                    // Second segment of the route remains the same
	                    for (int x = k; x >= j + 1; x--) {
	                        newRoute.add(route.get(x));
	                    }
	                    // 2-opt swap
	                    for (int x = k + 1; x < route.size(); x++) {
	                        newRoute.add(route.get(x));
	                    }
	                    // Calculate the distance of the new route
	                    double newDistance = calculateDistance(newRoute);
	                    // If the new route is shorter, replace the old route with the new one
	                    if (newDistance < calculateDistance(route)) {
	                        route = newRoute;
	                        improved = true;
	                    }
	                }
	            }
	        }
	    }
	    return route;
	}
	public static double calculateDistance(List<Node> route) {
	    double distance = 0;
	    for (int i = 0; i < route.size() - 1; i++) {
	        distance += Distance.measureDistance(route.get(i), route.get(i + 1));
	    }
	    return distance;
	}

}
