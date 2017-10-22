package edu.auburn.geneticalgorithm;

import java.util.Collections;
import java.util.List;

public class GeneticAlgorithm<T, V extends Comparable<V>> {

  private int populationSize = 0;
  private FitnessEvaluator<T, V> fitnessEvaluator;
  private Crossover<T> crossover;

  // Allows us to quickly try various interesting combinations for the algorithm:
  // We can freely change the fitness function, the breeding function, selection technique,
  // etc by varying the inputs to the constructor and
  GeneticAlgorithm(int populationSize, FitnessEvaluator<T, V> fitnessEvaluator,
      Crossover<T> crossover) {
    this.populationSize = populationSize;
    this.fitnessEvaluator = fitnessEvaluator;
    this.crossover = crossover;
  }

  FitnessRecord<T, V> searchForBestIndividual(GeneticAlgorithmStrategy<T, V> strategy,
      int generations) {

    List<T> population = strategy.getRandomPopulation(populationSize);
    List<FitnessRecord<T, V>> fitnessRecords =
        strategy.getFitnessRecords(population, fitnessEvaluator);

    for (int i = 0; i < generations; i++) {

      List<FitnessRecord<T, V>> selection =
          strategy.selectIndividualsForReproduction(fitnessRecords);
      List<T> descendants = strategy.reproduce(selection, crossover);
      population = strategy.updatePopulation(population, descendants);
      fitnessRecords = strategy.getFitnessRecords(population, fitnessEvaluator);
    }

    return Collections.max(fitnessRecords);
  }
}
