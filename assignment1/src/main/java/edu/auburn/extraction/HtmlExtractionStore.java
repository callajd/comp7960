package edu.auburn.extraction;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class HtmlExtractionStore {

  private Map<String, FeatureMap<Character>> urlToFeatureDataMap = new HashMap<String, FeatureMap<Character>>();
  
  public boolean hasExtractionInfo(String url) {
    return urlToFeatureDataMap.containsKey(url);
  }

  public void addExtractionInfo(String url, FeatureMap<Character> featureMap) {
    urlToFeatureDataMap.put(url, featureMap);
  }
  
  public Set<String> getAllUrls() {
    return urlToFeatureDataMap.keySet();
  }
  
  public FeatureMap<Character> getExtractionInfo(String url) {
    return urlToFeatureDataMap.get(url);
  }

  public void persist(Writer writer) throws IOException {
    for(String url : getAllUrls()) {
      writer.write(url);
      writer.write("\n");
      writer.write(getExtractionInfo(url).toString());
      writer.write("\n");
    }
  }
}
