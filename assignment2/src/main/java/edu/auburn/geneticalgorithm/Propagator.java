package edu.auburn.geneticalgorithm;

import java.util.List;

public interface Propagator<T, V extends Comparable<V>> {
  
  List<FitnessRecord<T, V>> propagate(List<FitnessRecord<T,V>> population, List<FitnessRecord<T,V>> offspring);
}
