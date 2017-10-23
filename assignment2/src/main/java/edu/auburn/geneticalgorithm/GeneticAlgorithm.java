package edu.auburn.geneticalgorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GeneticAlgorithm<T, V extends Comparable<V>> {

  private PopulationGenerator<T> populationGenerator;
  private Evaluator<T, V> fitnessEvaluator;
  private Selector<T, V> selector;
  private Breeder<T, V> breeder;
  private Propagator<T, V> propagator;

  // Allows us to quickly try various interesting combinations for the algorithm:
  // We can freely change the fitness function, selection strategy, the breeding function, 
  // propagation technique etc by varying the inputs to the constructor
 
  public GeneticAlgorithm(PopulationGenerator<T> populationGenerator, Evaluator<T, V> fitnessEvaluator,
      Selector<T, V> selector, Breeder<T, V> breeder, Propagator<T,V> propagator) {
    
    this.populationGenerator = populationGenerator;
    this.fitnessEvaluator = fitnessEvaluator;
    this.selector = selector;
    this.breeder = breeder;
    this.propagator = propagator;
  }

  public FitnessRecord<T, V> searchForBestIndividual(int generations) {

    List<T> rawPopulation = populationGenerator.generatePopulation(); 
    
    List<FitnessRecord<T, V>> population = new ArrayList<FitnessRecord<T, V>>();
    
    for(T individual : rawPopulation) population.add(fitnessEvaluator.evaluateFitness(individual));
    
    long totalSelectionTime = 0;
    long totalBreedingTime = 0;
    long totalFitnessEvaluationTime = 0;
    long totalPropagationTime = 0;
    long totalTime = 0;
    
    for (int i = 0; i < generations; i++) {
      System.out.print(String.format("Starting generation %d", i+1));
      long startTime = System.currentTimeMillis();
      
      List<FitnessRecord<T, V>> selection = selector.select(population);
      
      long selectionTime = System.currentTimeMillis();
      
      List<T> rawOffspring = breeder.breed(selection);
      
      long breedingTime = System.currentTimeMillis();
      
      List<FitnessRecord<T, V>> offspring = new ArrayList<FitnessRecord<T, V>>();
      
      for(T individualOffspring : rawOffspring) offspring.add(fitnessEvaluator.evaluateFitness(individualOffspring));
      
      long fitnessEvaluationTime = System.currentTimeMillis();
      
      population = propagator.propagate(population, offspring);
      
      long propagationTime = System.currentTimeMillis();
      long time = System.currentTimeMillis() - startTime;
      
      System.out.println(String.format(" : finished in %f seconds.", time / 1000.0));
      System.out.println(String.format("selection time %f seconds.", (selectionTime - startTime) / 1000.0));
      System.out.println(String.format("breeding time %f seconds.", (breedingTime - selectionTime) / 1000.0));
      System.out.println(String.format("fitness evaluation time %f seconds.", (fitnessEvaluationTime - breedingTime) / 1000.0));
      System.out.println(String.format("propagation time %f seconds.", (propagationTime - fitnessEvaluationTime) / 1000.0));
      
      totalSelectionTime += selectionTime - startTime;
      totalBreedingTime += breedingTime - selectionTime;
      totalFitnessEvaluationTime += fitnessEvaluationTime - breedingTime;
      totalPropagationTime += propagationTime - fitnessEvaluationTime;
      totalTime += time;
    }

    System.out.println(String.format(" : finished in %f total seconds.", totalTime / 1000.0));
    System.out.println(String.format("selection time %f total seconds.", totalSelectionTime / 1000.0));
    System.out.println(String.format("breeding time %f total seconds.", totalBreedingTime / 1000.0));
    System.out.println(String.format("fitness evaluation time %f total seconds.", totalFitnessEvaluationTime / 1000.0));
    System.out.println(String.format("propagation time %f total seconds.", totalPropagationTime / 1000.0));
    
    return Collections.max(population);
  }
}
