package edu.auburn;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TrainingSet {

  public static final List<MaliciousWebpageIndividual> trainingData;
  public static final String filePath = System.getenv("DATA_FILE");
  private static final double [][] mutualDistances;
  private static final double [] classifications;
  
  static {
    trainingData = new ArrayList<MaliciousWebpageIndividual>();
    int count = 0;
    try {
      BufferedReader reader = new BufferedReader(new FileReader(filePath));
      for (String line = reader.readLine(); line != null; line = reader.readLine()) {
        String[] split = line.split(" ");
        double output = Double.parseDouble(split[1]);
        List<Double> vector = new ArrayList<Double>();
        for (int i = 2; i < split.length; i++) {
          vector.add(Double.parseDouble(split[i]));
        }
        trainingData.add(new MaliciousWebpageIndividual(count++, output, vector));
      }
      reader.close();
    } catch(IOException e)  {
    }
    mutualDistances = new double[trainingData.size()][trainingData.size()];
    classifications = new double[trainingData.size()];
    for(int i = 0; i < trainingData.size(); i++) {
      classifications[i] = trainingData.get(i).getClassification();
      for(int j = 0; j < trainingData.size(); j++) {
        if(i == j) continue;
        mutualDistances[i][j] = trainingData.get(i).distance(trainingData.get(j));
      }
    }
  }
  
  public static List<MaliciousWebpageIndividual> trainingData() {

    return trainingData;
  }
  
  public static double getDistance(int i, int j) {

    return mutualDistances[i][j];
  }
  
  public static double[][] getDistanceMemo() {
    return mutualDistances;
  }
  
  public static double[] getClassifications() {
    return classifications;
  }

}
