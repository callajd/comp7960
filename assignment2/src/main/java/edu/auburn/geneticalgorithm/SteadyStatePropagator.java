package edu.auburn.geneticalgorithm;

import java.util.Collections;
import java.util.List;

public class SteadyStatePropagator<T, V extends Comparable<V>> implements Propagator<T, V> {
  
  int deathsPerGeneration = 0;
  
  public SteadyStatePropagator(int deathsPerGeneration) {
    this.deathsPerGeneration = deathsPerGeneration;
  }

  @Override
  public List<FitnessRecord<T, V>> propagate(List<FitnessRecord<T, V>> population,
      List<FitnessRecord<T, V>> offspring) {
   
    Collections.sort(offspring);
    Collections.sort(population);
    
    List<FitnessRecord<T, V>> survivors = population.subList(deathsPerGeneration, population.size());
    List<FitnessRecord<T, V>> bestOffspring = offspring.subList(offspring.size() - deathsPerGeneration, offspring.size());
    
    survivors.addAll(bestOffspring);
    
    return survivors;
  }

}
