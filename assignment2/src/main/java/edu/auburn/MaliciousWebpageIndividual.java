package edu.auburn;

import java.util.List;
import edu.auburn.neuralnetwork.Individual;

public class MaliciousWebpageIndividual extends Individual<List<Double>> {

  private final int index;

  public MaliciousWebpageIndividual(int index, Double classification, List<Double> data) {
    super(classification, data);
    this.index = index;
  }

  public int getIndex() {
    return index;
  }
  
  @Override
  public double distance(Individual<List<Double>> other) {

    assert other.getData().size() == getData().size();

    double sumOfSquaresOfScalarDistances = 0.0;
    for (int i = 0; i < getData().size(); i++) {
      double scalarDistance = getData().get(i) - other.getData().get(i);
      sumOfSquaresOfScalarDistances += scalarDistance * scalarDistance;
    }
    return Math.sqrt(sumOfSquaresOfScalarDistances);
  }

}
