package edu.auburn.geneticalgorithm;

public interface Crossover<T> {
	public T crossover(T parent1, T parent2);
}
