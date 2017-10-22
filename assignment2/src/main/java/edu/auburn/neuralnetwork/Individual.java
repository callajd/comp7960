package edu.auburn.neuralnetwork;

public abstract class Individual<T> {

  private double classification;
  private T data;
  
  public Individual(double classification, T data) {
    this.classification = classification;
    this.data = data;
  }
  
  public double getClassification() {
    return classification;
  }
  
  public T getData() {
    return data;
  }
  
  public abstract double distance(Individual<T> other);

}
