package edu.auburn.extraction;

import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class FeatureMapFileWriter {
  
  private String file;
  private FileWriter fileWriter = null;

  public FeatureMapFileWriter(String file) {
    this.file = file;
  }
  
  public void write(String url, FeatureMap<Character> featureMap) throws IOException {
    getFileWriter().write(url);
    getFileWriter().write("\n");
    getFileWriter().write(featureMap.toString());
    getFileWriter().write("\n");
  }
  
  public void writeVectors(String url, FeatureMap<Character> featureMap) throws IOException {
    getFileWriter().write(featureMap.toString());
    getFileWriter().write("\n");
  }
  
  public void close() throws IOException {
    getFileWriter().flush();
    getFileWriter().close();
  }

  private OutputStreamWriter getFileWriter() throws IOException {
    if(fileWriter == null) {
      fileWriter = new FileWriter(file);
    }
    return fileWriter;
  }
}
