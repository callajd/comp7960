package edu.auburn.extraction;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

public class FeatureMap<T> {

  private int totalCount = 0;
  private Map<T, Integer> featureDistribution = new HashMap<T, Integer>();
  
  public FeatureMap() {
  }
  
  public FeatureMap(Set<T> initialFeatureSet) {
    for(T feature : initialFeatureSet) {
      featureDistribution.put(feature, 0);
    }
  }
  
  public int incrementFeature(T feature) {
    if(!featureDistribution.containsKey(feature)) {
      featureDistribution.put(feature, 0);
    }
    int newCount = 1 + featureDistribution.get(feature);
    featureDistribution.put(feature, newCount);
    totalCount++;
    return newCount;
  }
  
  public Set<T> getFeatures() {
    return featureDistribution.keySet();
  }
  
  public boolean hasFeature(T feature) {
    return featureDistribution.containsKey(feature);
  }
  
  public int getTotalFeatureCount() {
    return totalCount;
  }
  
  public float getFeatureFraction(T feature) {
    return new Float(featureDistribution.get(getFeatureOrThrow(feature))) / totalCount;
  }
  
  public int getFeatureCount(T feature) {
    return featureDistribution.get(getFeatureOrThrow(feature));
  }
  
  private T getFeatureOrThrow(T feature) {
    if(!featureDistribution.containsKey(feature)) {
      throw new NoSuchElementException("No matching feature found.");
    }
    return feature;
  }
  
  @Override
  public String toString() {
    StringBuilder mapStringBuilder = new StringBuilder();
    mapStringBuilder.append("char\tcount\tfraction\n");
    for (T feature : getFeatures()) {
      Integer featureCount = featureDistribution.get(feature);
      double featureFraction = ((double) featureCount) / getTotalFeatureCount();
      mapStringBuilder.append(String.format("'%s'\t%d\t%.4f\n", feature, featureCount, featureFraction));
    }
    mapStringBuilder.append(String.format("%d total features.", getTotalFeatureCount()));
    
    return mapStringBuilder.toString();
  }
  +  
+  public String toStringVector() {
+	StringBuilder mapStringBuilder = new StringBuilder();
+    for (T feature : getFeatures()) {
+      Integer featureCount = featureDistribution.get(feature);
+      double featureFraction = ((double) featureCount) / getTotalFeatureCount();
+      mapStringBuilder.append(String.format("%f ", featureFraction));
+    }    
+    return mapStringBuilder.toString();
+}
}
