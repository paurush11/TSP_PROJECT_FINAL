package edu.neu.csye6205.finalProject.Paurush.GeneticAlgorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import edu.neu.csye6205.finalProject.Paurush.Node;

public class GeneticAlgorithm {
	public static final int populationSize = 1000;
	public static final double mutationRate = 0.5;
    public static final int tournamentSize = 5;
    public static final int elitismCount = 5;
    public static final int generations = 650;  
  
    public static List<Node> optimize(List<Node> nodes) {
        List<Chromosome> population = generatePopulation(nodes, populationSize);
        evaluatePopulation(population);

        for (int i = 0; i < generations; i++) {
            List<Chromosome> nextPopulation = new ArrayList<>();
            for (int j = 0; j < elitismCount; j++) {
                nextPopulation.add(getFittest(population));
            }

            while (nextPopulation.size() < populationSize) {
                Chromosome parent1 = tournamentSelection(population);
                Chromosome parent2 = tournamentSelection(population);
                Chromosome child = crossover(parent1, parent2);
                mutate(child);
                nextPopulation.add(child);
            }

            population = nextPopulation;
            evaluatePopulation(population);
        }

        return getFittest(population).getNodes();
    }

    private static List<Chromosome> generatePopulation(List<Node> nodes, int size) {
        List<Chromosome> population = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            Collections.shuffle(nodes);
            population.add(new Chromosome(nodes));
        }
        return population;
    }

    private static void evaluatePopulation(List<Chromosome> population) {
        for (Chromosome chromosome : population) {
            chromosome.calculateFitness();
        }
        Collections.sort(population);
    }

    private static Chromosome getFittest(List<Chromosome> population) {
        return population.get(0);
    }

    private static Chromosome tournamentSelection(List<Chromosome> population) {
        List<Chromosome> tournament = new ArrayList<>();
        for (int i = 0; i < tournamentSize; i++) {
            tournament.add(population.get(new Random().nextInt(population.size())));
        }
        return getFittest(tournament);
    }

    private static Chromosome crossover(Chromosome parent1, Chromosome parent2) {
        List<Node> childNodes = new ArrayList<>(parent1.getNodes());
        int startPos = new Random().nextInt(parent1.getNodes().size());
        int endPos = new Random().nextInt(parent1.getNodes().size());
        if (startPos > endPos) {
            int temp = startPos;
            startPos = endPos;
            endPos = temp;
        }
        for (int i = startPos; i < endPos; i++) {
            childNodes.set(i, parent2.getNodes().get(i));
        }
        return new Chromosome(childNodes);
    }

    private static void mutate(Chromosome chromosome) {
        for (int i = 0; i < chromosome.getNodes().size(); i++) {
            if (new Random().nextDouble() < mutationRate) {
                int j = new Random().nextInt(chromosome.getNodes().size());
                Collections.swap(chromosome.getNodes(), i, j);
            }
        }
    }
}
