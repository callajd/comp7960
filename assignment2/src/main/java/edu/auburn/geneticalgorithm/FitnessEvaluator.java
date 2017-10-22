package edu.auburn.geneticalgorithm;

public interface FitnessEvaluator<T, V extends Comparable<V>> {
  FitnessRecord<T, V> evaluateFitness(T individual);
}
