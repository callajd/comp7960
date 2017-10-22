package edu.auburn.sigma;

import edu.auburn.geneticalgorithm.FitnessRecord;

public class SigmaFitnessRecord implements FitnessRecord<Double, Double> {

  private double sigma;
  private double fitness;

  public SigmaFitnessRecord(double sigma, double fitness) {
    this.sigma = sigma;
    this.fitness = fitness;
  }

  public Double getInput() {
    return sigma;
  }

  public Double getFitness() {
    return fitness;
  }

}
