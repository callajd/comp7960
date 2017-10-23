package edu.auburn.sigma.selectors;

import java.util.ArrayList;
import java.util.List;
import edu.auburn.geneticalgorithm.FitnessRecord;
import edu.auburn.geneticalgorithm.Selector;

public class SigmaRouletteWheelSelector implements Selector<Double, Double> {

  @Override
  public List<FitnessRecord<Double, Double>> select(List<FitnessRecord<Double, Double>> population) {
    
    List<FitnessRecord<Double, Double>> selection = new ArrayList<FitnessRecord<Double, Double>>();
    
    double fitnessSum = 0;
    for(FitnessRecord<Double, Double> record : population) {
      fitnessSum += record.getFitness();
    }
    List<FitnessRecord<Double, Double>> availablePopulation = new ArrayList<FitnessRecord<Double, Double>>(population);
    
    // This technique randomly re-orders the entire population with individual probability
    // proportional to individual fitness.
    while(availablePopulation.size() > 0) {
      
      double random = Math.random() * fitnessSum;
      double position = 0;
      
      for(int i = 0; i < availablePopulation.size(); i++) {
      
        position = position + availablePopulation.get(i).getFitness();
        
        if(position > random) {
          
          FitnessRecord<Double, Double> individual = availablePopulation.remove(i);
          selection.add(individual);
          fitnessSum = fitnessSum - individual.getFitness();
        }
      }
    }
    
    return selection;
  }

}
