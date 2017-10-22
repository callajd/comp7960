package edu.auburn.sigma;

import edu.auburn.neuralnetwork.GRNNResultRecord;

public class SigmaGRNNResultRecord implements GRNNResultRecord<Double> {

  private int truePositive = 0;
  private int trueNegative = 0;
  private int falsePositive = 0;
  private int falseNegative = 0;
  private double sigma = 0.0;

  public SigmaGRNNResultRecord(double sigma) {
    this.sigma = sigma;
  }

  // Only method that mutates
  public void accept(double expected, double actual) {
    if (actual > 0 && actual == expected)
      truePositive++;
    if (actual < 0 && actual == expected)
      trueNegative++;
    if (actual > 0 && actual != expected)
      falsePositive++;
    if (actual < 0 && actual != expected)
      falseNegative++;
  }

  @Override
  public Double getInput() {
    return sigma;
  }

  @Override
  public double getAccuracy() {
    return ((double) getSuccessCount()) / getTotalCount();
  }

  @Override
  public int getTotalCount() {
    return getSuccessCount() + getFailureCount();
  }

  @Override
  public int getSuccessCount() {
    return truePositive + trueNegative;
  }

  @Override
  public int getTruePositiveCount() {
    return truePositive;
  }

  @Override
  public int getTrueNegativeCount() {
    return trueNegative;
  }

  @Override
  public int getFailureCount() {
    return falsePositive + falseNegative;
  }

  @Override
  public int getFalsePositiveCount() {
    return falsePositive;
  }

  @Override
  public int getFalseNegativeCount() {
    return falseNegative;
  }

}
