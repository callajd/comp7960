package edu.auburn.geneticalgorithm;

import java.util.List;

// no mutation. yet.
public interface GeneticAlgorithmStrategy<T, V extends Comparable<V>> {
	
	public List<T> getRandomPopulation(int size);
	
	public List<FitnessRecord<T, V>> getFitnessRecords(List<T> population, FitnessEvaluator<T, V> fitnessEvaluator);
	
	public List<FitnessRecord<T, V>> selectIndividualsForReproduction(List<FitnessRecord<T, V>> fitnessRecords);
	
	public List<T> reproduce(List<FitnessRecord<T,V>> parentRecords, Crossover<T> crossover);
	
	public List<T> updatePopulation(List<T> population, List<T> children);
}
