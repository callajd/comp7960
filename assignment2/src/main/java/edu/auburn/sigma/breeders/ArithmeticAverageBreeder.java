package edu.auburn.sigma.breeders;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import edu.auburn.geneticalgorithm.Breeder;
import edu.auburn.geneticalgorithm.FitnessRecord;

public class ArithmeticAverageBreeder implements Breeder<Double, Double> {

  @Override
  public Double breed(Double parent1, Double parent2) {
    return (parent1 + parent2) / 2;
  }

  @Override
  public List<Double> breed(List<FitnessRecord<Double, Double>> selection) {
    
    Collections.shuffle(selection);
    List<Double> offspring = new ArrayList<Double>();
    
    for(int i = 0; i < selection.size() / 2; i += 2) {
      offspring.add(breed(selection.get(2*i).getFitness(), selection.get(2*i + 1).getFitness()));
    }
    
    return offspring;
  }

}
