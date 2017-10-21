package edu.auburn;

import java.util.List;

public class Instance {

  private double output;
  private List<Double> vector;
  
  public Instance(double output, List<Double> vector) {
    this.output = output;
    this.vector = vector;
  }
  
  public void setOuput(double output) {
    this.output = output;
  }
  
  public double getOutput() {
    return output;
  }
  
  public List<Double> getVector() {
    return vector;
  }
  
  public double distance(Instance trainingInstance) {
    
    assert trainingInstance.getVector().size() == vector.size();
    
    double sumOfSquaresOfScalarDistances = 0.0;
    for(int i = 0; i < vector.size(); i++) {
      double scalarDistance = vector.get(i) - trainingInstance.getVector().get(i);
      sumOfSquaresOfScalarDistances += scalarDistance * scalarDistance;
    }
    return Math.sqrt(sumOfSquaresOfScalarDistances);
  }

}
