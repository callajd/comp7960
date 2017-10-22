package edu.auburn.geneticalgorithm;

public interface FitnessRecord<T, V extends Comparable<V>> extends Comparable<FitnessRecord<T, V>> {

  public T getInput();

  public V getFitness();

  default public int compareTo(FitnessRecord<T, V> other) {
    return getFitness().compareTo(other.getFitness());
  }
}
