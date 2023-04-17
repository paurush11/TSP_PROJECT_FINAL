package edu.neu.csye6205.finalProject.Paurush.util;

import edu.neu.csye6205.finalProject.Paurush.Node;

public class Distance {

	private static final double EARTH_EQUATORIAL_RADIUS = 6378.1370D;
   
    private static final double CONVERT_KM_TO_METERS = 1000;
    //Haversine formula
    public static double measureDistance(Node x, Node y) { 
		 double deltaLongitude = x.getLongitude() - y.getLongitude();
	     double deltaLatitude = x.getLatitude() - y.getLatitude();
	     double a = Math.pow(Math.sin(deltaLatitude / 2D), 2D) + 
     		   Math.cos(y.getLatitude()) * Math.cos(x.getLatitude()) * Math.pow(Math.sin(deltaLongitude / 2D), 2D);
	     return CONVERT_KM_TO_METERS * EARTH_EQUATORIAL_RADIUS * 2D * Math.atan2(Math.sqrt(a), Math.sqrt(1D - a));
	 }
}
