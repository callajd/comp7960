package edu.auburn.sigma;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import edu.auburn.MaliciousWebpageIndividual;
import edu.auburn.TrainingSet;
import edu.auburn.geneticalgorithm.FitnessEvaluator;
import edu.auburn.neuralnetwork.GeneralRegressionNeuralNetwork;

public class SigmaFitnessEvaluator implements FitnessEvaluator<Double, Double> {

  public static void main(String[] args) throws IOException {
    TrainingSet.filePath = args[0];
    SigmaFitnessEvaluator evaluator = new SigmaFitnessEvaluator();
    System.out.println(evaluator.evaluateFitness(0.7));
    System.out.println(evaluator.evaluateFitness(1.41));
    System.out.println(evaluator.evaluateFitness(0.0013853));
  }

  @Override
  public SigmaFitnessRecord evaluateFitness(Double sigma) {

    SigmaGRNNResultRecord stats = new SigmaGRNNResultRecord(sigma);
    GeneralRegressionNeuralNetwork<List<Double>, MaliciousWebpageIndividual> network =
        new GeneralRegressionNeuralNetwork<List<Double>, MaliciousWebpageIndividual>();

    for (int i = 0; i < TrainingSet.trainingData().size(); i++) {

      List<MaliciousWebpageIndividual> subSet =
          new ArrayList<MaliciousWebpageIndividual>(TrainingSet.trainingData());
      MaliciousWebpageIndividual instance = subSet.remove(i);

      double output = network.classify(instance, subSet, sigma);
      double flattenedOutput = output < 0 ? -1.0 : 1.0;

      stats.accept(instance.getClassification(), flattenedOutput);
    }
    return new SigmaFitnessRecord(sigma, stats.getAccuracy());
  }

}
