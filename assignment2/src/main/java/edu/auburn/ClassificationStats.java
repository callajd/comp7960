package edu.auburn;

public class ClassificationStats {
  
  public int truePositive = 0;
  public int trueNegative = 0;
  public int falsePositive = 0;
  public int falseNegative = 0;
  public double sigma = 0;
  
  public ClassificationStats(double sigma) {
    this.sigma = sigma;
  }
  
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append(String.format("sigma : %f\n", sigma));
    builder.append("---------------------------\n");
    builder.append("TP\tTN\tFP\tFN\n");
    builder.append(String.format("%d\t%d\t%d\t%d\n", truePositive, trueNegative, falsePositive, falseNegative));
    builder.append("---------------------------\n");
    builder.append(String.format("matches / total : %d / %d\n", truePositive + trueNegative, TrainingSet.trainingData().size()));
    builder.append(String.format("accuracy : %f\n", ((double) truePositive + trueNegative)/ TrainingSet.trainingData().size()));
    return builder.toString();
  }

  public void accept(double expected, double actual) {
    if(actual > 0 && actual == expected) truePositive++;
    if(actual < 0 && actual == expected) trueNegative++;
    if(actual > 0 && actual != expected) falsePositive++;
    if(actual < 0 && actual != expected) falseNegative++;
  }
}
