package edu.auburn;

import edu.auburn.geneticalgorithm.GeneticAlgorithm;
import edu.auburn.geneticalgorithm.SteadyStatePropagator;
import edu.auburn.sigma.SigmaFitnessEvaluator;
import edu.auburn.sigma.SigmaPopulationGenerator;
import edu.auburn.sigma.breeders.UniformCrossoverBreeder;
import edu.auburn.sigma.selectors.SigmaRouletteWheelSelector;

public class SigmaSearcher {

  public static void main(String [] args) {
     
     double maxSigma = 2;
     
     GeneticAlgorithm<Double, Double> algorithm = 
         new GeneticAlgorithm<Double, Double>(
             new SigmaPopulationGenerator(100, maxSigma),
             new SigmaFitnessEvaluator(),
             new SigmaRouletteWheelSelector(),
             new UniformCrossoverBreeder(maxSigma, 3 /* decimal precision */),
             new SteadyStatePropagator<Double, Double>(20));
     
     System.out.println(algorithm.searchForBestIndividual(100));
   }
}
