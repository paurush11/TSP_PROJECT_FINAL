package edu.neu.csye6205.finalProject.Paurush.ACO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.stream.IntStream;

import edu.neu.csye6205.finalProject.Paurush.Driver;
import edu.neu.csye6205.finalProject.Paurush.Node;
import edu.neu.csye6205.finalProject.Paurush.util.Distance;



public class AntColonyOptimization {
	private AtomicDouble[][] phermoneLevelsMatrix = null;
	private double[][] distancesMatrix = null;
	private ArrayList<Node>cities = (ArrayList<Node>) Driver.graph.getNodes();
	private int citiesSize = Driver.graph.getNodes().size();
	public AntColonyOptimization() throws IOException {
		initializeDistances();
		initializePhermoneLevels();
	}
	public AtomicDouble[][] getPhermoneLevelsMatrix() { return phermoneLevelsMatrix; }
	public double[][] getDistancesMatrix() { return distancesMatrix; }
	private void initializeDistances() throws IOException {
        distancesMatrix = new double[citiesSize][citiesSize];
        IntStream.range(0, citiesSize).forEach(x -> {
        	Node cityY = cities.get(x);
        	IntStream.range(0, citiesSize).forEach(y -> distancesMatrix[x][y] = Distance.measureDistance(cityY, cities.get(y)));
        });
	}
	private void initializePhermoneLevels() {
		phermoneLevelsMatrix = new AtomicDouble[citiesSize][citiesSize];
		Random random = new Random();
		IntStream.range(0, citiesSize).forEach(x -> {
			IntStream.range(0, citiesSize).forEach(y -> phermoneLevelsMatrix[x][y] = new AtomicDouble(random.nextDouble()));
		});
	}
}
