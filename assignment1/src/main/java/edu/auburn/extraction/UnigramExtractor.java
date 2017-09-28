package edu.auburn.extraction;

import java.io.IOException;
import java.io.Reader;
import java.util.HashSet;
import java.util.Set;

public class UnigramExtractor {

  private static Set<Character> alphabet = null;

  public static FeatureMap<Character> extractFeatures(Reader reader) throws IOException {

    FeatureMap<Character> featureMap = new FeatureMap<Character>(getAlphabet());

    for (int c = reader.read(); c != -1; c = reader.read()) {
      // Ignore characters outside of our 'alphabet'.
      if (featureMap.hasFeature(Character.valueOf((char) c))) {
        featureMap.incrementFeature(Character.valueOf((char) c));
      }
    }

    return featureMap;
  }

  private static Set<Character> getAlphabet() {
    if (alphabet == null) {
      alphabet = new HashSet<Character>();
      for (int i = 32; i < 127; i++) {
        alphabet.add(Character.valueOf((char) i));
      }
    }
    return alphabet;
  }

}
