package edu.neu.csye6205.finalProject.Paurush.ACO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;


import edu.neu.csye6205.finalProject.Paurush.Node;


public class runACO {
	private static final int NUMBER_OF_ANTS = 500;
	private static final double PROCESSING_CYCLE_PROBABILITY = 0.8;
	private static ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
	private static ExecutorCompletionService<Ant> executorCompletionService = new ExecutorCompletionService<Ant>(getExecutorService());
	private  Route shortestRoute = null;
	private int activeAnts = 0;
	public static ArrayList<Node> initialRoute = new ArrayList<Node>();
	
	
	 public void processAnts() {
		while (getActiveAnts() > 0) {
			try { 
				Ant ant = getExecutorCompletionService().take().get();
				Route currentRoute =  ant.getRoute();
				if (getShortestRoute() == null || currentRoute.getDistance() < getShortestRoute().getDistance()) {
					setShortestRoute(currentRoute);
					StringBuffer distance = new StringBuffer("       "+String.format("%.2f", currentRoute.getDistance()));
					IntStream.range(0, 21 - distance.length()).forEach(k -> distance.append(" "));
					System.out.println(Arrays.toString(getShortestRoute().getCities().toArray()) + " |" + distance + "| "+ ant.getAntNumb());
				}
			} catch (Exception e) { e.printStackTrace(); }
			setActiveAnts(getActiveAnts() - 1);
		}
	}
	public static void printHeading() {
    	String headingColumn1 = "Route";
    	String remainingHeadingColumns = "Distance (in miles) | ant #";
    	int cityNamesLength = 0;
    	for (int x = 0; x < initialRoute.size(); x++) cityNamesLength += initialRoute.get(x).getName().length();
    	int arrayLength = cityNamesLength + initialRoute.size()*2;
    	int partialLength = (arrayLength - headingColumn1.length())/2;
    	for (int x=0; x < partialLength; x++)System.out.print(" ");
    	System.out.print(headingColumn1);
    	for (int x=0; x < partialLength; x++)System.out.print(" ");
    	if ((arrayLength % 2) == 0)System.out.print(" ");
    	System.out.println(" | "+ remainingHeadingColumns);
    	cityNamesLength += remainingHeadingColumns.length() + 3;
    	for (int x=0; x < cityNamesLength+initialRoute.size()*2; x++)System.out.print("-");
    	System.out.println("");
    }
	public int getNumberOfAnts() {
		return NUMBER_OF_ANTS;
	}
	public static double getProcessingCycleProbability() {
		return PROCESSING_CYCLE_PROBABILITY;
	}
	public int getActiveAnts() {
		return activeAnts;
	}
	public void setActiveAnts(int activeAnts) {
		this.activeAnts = activeAnts;
	}
	public ExecutorCompletionService<Ant> getExecutorCompletionService() {
		return executorCompletionService;
	}
	public static void setExecutorCompletionService(ExecutorCompletionService<Ant> executorCompletionService) {
		runACO.executorCompletionService = executorCompletionService;
	}
	public static ExecutorService getExecutorService() {
		return executorService;
	}
	public static void setExecutorService(ExecutorService executorService) {
		runACO.executorService = executorService;
	}
	public Route getShortestRoute() {
		return shortestRoute;
	}
	public void setShortestRoute(Route shortestRoute) {
		this.shortestRoute = shortestRoute;
	}
}
