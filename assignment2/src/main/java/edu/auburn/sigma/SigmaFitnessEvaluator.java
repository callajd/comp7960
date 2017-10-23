package edu.auburn.sigma;

import java.util.List;
import edu.auburn.MaliciousWebpageIndividual;
import edu.auburn.TrainingSet;
import edu.auburn.geneticalgorithm.Evaluator;
import edu.auburn.neuralnetwork.GeneralRegressionNeuralNetwork;

public class SigmaFitnessEvaluator implements Evaluator<Double, Double> {

  @Override
  public SigmaFitnessRecord evaluateFitness(Double sigma) {

    SigmaGRNNResultRecord stats = new SigmaGRNNResultRecord(sigma);

    GeneralRegressionNeuralNetwork<List<Double>, MaliciousWebpageIndividual> network =
        new GeneralRegressionNeuralNetwork<List<Double>, MaliciousWebpageIndividual>();
    
    for (int i = 0; i < TrainingSet.trainingData().size(); i++) {

      MaliciousWebpageIndividual instance = TrainingSet.trainingData().get(i);
      double output = network.classify(instance, TrainingSet.trainingData(), sigma);
      double flattenedOutput = output < 0 ? -1.0 : 1.0;

      stats.accept(instance.getClassification(), flattenedOutput);
    }
    return new SigmaFitnessRecord(sigma, stats.getAccuracy());
  }
  
  

}
