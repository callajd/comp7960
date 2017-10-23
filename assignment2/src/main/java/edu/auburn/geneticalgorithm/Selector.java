package edu.auburn.geneticalgorithm;

import java.util.List;

public interface Selector<T,V extends Comparable<V>> {
  List<FitnessRecord<T,V>> select(List<FitnessRecord<T,V>> population);
}
