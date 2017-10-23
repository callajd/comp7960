package edu.auburn.sigma.breeders;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import edu.auburn.geneticalgorithm.Breeder;
import edu.auburn.geneticalgorithm.FitnessRecord;

public class UniformCrossoverBreeder implements Breeder<Double, Double> {

  private BinaryEncoder encoder;

  public UniformCrossoverBreeder(double maxValue, int decimalCount) {
    encoder = new BinaryEncoder(maxValue, decimalCount);
  }
  
  @Override
  public List<Double> breed(List<FitnessRecord<Double, Double>> selection) {
    
    Collections.shuffle(selection);
    
    List<Double> offspring = new ArrayList<Double>();
    for(int i =0; i < selection.size() / 2; i++) {
      offspring.add(breed(selection.get(2*i).getFitness(), selection.get(2*i +1).getFitness()));
    }
    
    return offspring;
  }

  @Override
  public Double breed(Double parent1, Double parent2) {
    BigInteger encodedParent1 = encoder.encode(parent1);
    BigInteger encodedParent2 = encoder.encode(parent2);
    
    BigInteger child = BigInteger.ZERO;
    
    for(int i = 0; i < encoder.bitsInEncoding(); i++) {
      double value = Math.random();
      boolean copiedBit = value < 0.5 ? encodedParent1.testBit(i) 
                                      : encodedParent2.testBit(i);
      if(copiedBit) child.setBit(i);
    }
    
    return encoder.decode(child);
  }

}
