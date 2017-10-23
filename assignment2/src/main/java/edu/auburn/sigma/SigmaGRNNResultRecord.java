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
  
  public SigmaGRNNResultRecord combine(SigmaGRNNResultRecord other) {
    if(other.sigma != sigma) throw new IllegalArgumentException();
    
    SigmaGRNNResultRecord combined = new SigmaGRNNResultRecord(sigma);
    combined.truePositive = truePositive + other.truePositive;
    combined.trueNegative = trueNegative + other.trueNegative;
    combined.falsePositive = falsePositive + other.falsePositive;
    combined.falseNegative = falseNegative + other.falseNegative;
    
    return combined;
  }

  // Only method that mutates
  public void accept(double expected, double actual) {
    double flattenedActual = actual < 0 ? -1 : 1;
    if (actual > 0 && flattenedActual == expected)
      truePositive++;
    if (actual < 0 && flattenedActual == expected)
      trueNegative++;
    if (actual > 0 && flattenedActual != expected)
      falsePositive++;
    if (actual < 0 && flattenedActual != expected)
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
