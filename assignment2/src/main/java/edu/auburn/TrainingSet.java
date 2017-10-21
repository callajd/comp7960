package edu.auburn;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TrainingSet {

  public static List<Instance> trainingData;
  public static String filePath;
  
  public static List<Instance> trainingData()  {
    if(trainingData == null) {
      trainingData = new ArrayList<Instance>();
      try {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        for(String line = reader.readLine(); line != null; line = reader.readLine()) {
          String [] split = line.split(" ");
          double output = Double.parseDouble(split[1]);
          List<Double> vector = new ArrayList<Double>();
          for(int i = 2; i < split.length; i++) {
            vector.add(Double.parseDouble(split[i]));
          }
          trainingData.add(new Instance(output, vector));
        }
        reader.close();
      } catch(IOException e) {
      }
    }
    return trainingData;
  }
}
