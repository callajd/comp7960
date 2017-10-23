package edu.auburn.neuralnetwork;

import java.util.List;
import edu.auburn.TrainingSet;

public class GeneralRegressionNeuralNetwork<T, V extends Individual<T>> {

  public double classify(V individual, List<V> trainingData, double sigma) {

    Pair fraction = trainingData.parallelStream()
                                .map((V other) -> getFractionParts(individual, other, sigma))
                                .reduce(new Pair(0,0), (p1, p2) -> new Pair(p1.a + p2.a, p1.b + p2.b));
    return fraction.a / fraction.b;
  }
  
  private Pair getFractionParts(V first, V second, double sigma) {
    int i = first.getIndex();
    int j = second.getIndex();
    double distance = TrainingSet.getDistance(i, j);
    double weight = Math.exp(-(distance * distance) / (2 * sigma * sigma));
    return new Pair(weight * first.getClassification(), weight);
  }
  
  private class Pair {
    public final double a;
    public final double b;
    
    public Pair(double a, double b) {
      this.a = a;
      this.b = b;
    }
  }
}
