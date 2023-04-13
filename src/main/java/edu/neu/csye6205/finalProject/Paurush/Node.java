package edu.neu.csye6205.finalProject.Paurush;

import java.awt.Point;

public class Node {
	private String id;
	private double latitude;
	private double longitude;
	 private static final double CONVERT_DEGREES_TO_RADIANS = Math.PI/180D;
	public Node(String ID, double parseDouble, double parseDouble2) {
		// TODO Auto-generated constructor stub
		this.id = ID;
		this.latitude = parseDouble*CONVERT_DEGREES_TO_RADIANS ;
		this.longitude = parseDouble2 *CONVERT_DEGREES_TO_RADIANS;
		
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	public String toString() {
		String s = "";
		s+= this.id;
		return s;
		
	}
	public String getName() {
		// TODO Auto-generated method stub
		return id;
	}
	public Point getPosition() {
		// TODO Auto-generated method stub
		return new Point((int)this.getLatitude(),(int)this.getLongitude());
	}
	
	
	
}

