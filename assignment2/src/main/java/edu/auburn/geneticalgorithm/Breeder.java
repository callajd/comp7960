package edu.auburn.geneticalgorithm;

import java.util.List;

public interface Breeder<T, V extends Comparable<V>> {
  
  public List<T> breed(List<FitnessRecord<T, V>> selection);
  
  public T breed(T parent1, T parent2);
}
