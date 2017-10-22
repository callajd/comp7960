package edu.auburn.neuralnetwork;

import java.util.List;

public class GeneralRegressionNeuralNetwork<T, V extends Individual<T>> {

	public double classify(V individual, List<V> trainingData, double sigma) {
	    
	    double cumulativeWeightedOutput = 0.0; 
	    double cumulativeWeight = 0.0; 
	    
	    for(V trainingIndividual : trainingData) {
	      
	      double distance = individual.distance(trainingIndividual);
	      double weight = Math.exp(-(distance*distance) / (2*sigma*sigma));
	      
	      cumulativeWeightedOutput += weight * trainingIndividual.getClassification();
	      cumulativeWeight += weight;
	    }
	    
	    return cumulativeWeightedOutput / cumulativeWeight;
	}
}
