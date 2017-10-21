package edu.auburn;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Classifier {
  
  public static void main(String [] args) throws IOException {
    TrainingSet.filePath = args[0];
    
    System.out.println(runSigma(1.41));
    System.out.println(runSigma(0.0013853));
  }
  
  public static ClassificationStats runSigma(double sigma) throws IOException {
    
    ClassificationStats stats = new ClassificationStats(sigma);
    
    for(int i = 0; i < TrainingSet.trainingData().size(); i++) {
      
      List<Instance> subSet = new ArrayList<Instance>(TrainingSet.trainingData());
      Instance instance = subSet.remove(i);
    
      double flattenedOutput = classify(instance, subSet, sigma) < 0 ? -1.0 : 1.0;
      
      stats.accept(instance.getOutput(), flattenedOutput);
    }
    return stats;
  }
  
  public static double classify(Instance instance, List<Instance> trainingData, double sigma) {
    
    double cumulativeWeightedOutput = 0.0; 
    double cumulativeWeight = 0.0; 
    
    for(Instance trainingInstance : trainingData) {
      
      double distance = instance.distance(trainingInstance);
      double weight = Math.exp(-(distance*distance) / (2*sigma*sigma));
      
      cumulativeWeightedOutput += weight * trainingInstance.getOutput();
      cumulativeWeight += weight;
    }
    
    return cumulativeWeightedOutput / cumulativeWeight;
  }

}
