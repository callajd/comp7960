package edu.auburn.geneticalgorithm;

public interface Evaluator<T, V extends Comparable<V>> {
  FitnessRecord<T, V> evaluateFitness(T individual);
}
