package edu.neu.csye6205.finalProject.Paurush.tactical;
import java.util.Collections;
import java.util.List;

import edu.neu.csye6205.finalProject.Paurush.Node;
import edu.neu.csye6205.finalProject.Paurush.util.Distance;
public class optimization2opt {
	public static List<Node> twoOpt(List<Node> tour) {
	    int size = tour.size();
	    boolean improved = true;
	    while (improved) {
	        improved = false;
	        for (int i = 0; i < size - 2; i++) {
	            for (int j = i + 2; j < size - 1; j++) {
	                double dist1 = Distance.measureDistance(tour.get(i), tour.get(i + 1));
	                double dist2 = Distance.measureDistance(tour.get(j), tour.get(j + 1));
	                double dist3 = Distance.measureDistance(tour.get(i), tour.get(j));
	                double dist4 = Distance.measureDistance(tour.get(i + 1), tour.get(j + 1));
	                if (dist1 + dist2 > dist3 + dist4) {
	                    Collections.reverse(tour.subList(i + 1, j + 1));
	                    improved = true;
	                }
	            }
	        }
	    }
	    return tour;
	}
}
