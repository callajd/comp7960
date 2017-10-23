package edu.auburn.sigma;

import java.util.ArrayList;
import java.util.List;
import edu.auburn.geneticalgorithm.PopulationGenerator;

public class SigmaPopulationGenerator implements PopulationGenerator<Double> {

  private int populationSize = 0;
  private double maxSigma = 0;
  
  public SigmaPopulationGenerator(int populationSize, double maxSigma) {
    this.populationSize = populationSize;
    this.maxSigma = maxSigma;
  }
  
  @Override
  public List<Double> generatePopulation() {
    
    List<Double> population = new ArrayList<Double>(populationSize);
    for(int i = 0; i < populationSize; i++) {
      population.add(Math.random() * maxSigma);
    }
    return population;
  }

}
