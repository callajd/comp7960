package edu.auburn;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UnigramExtractor {

  // Currently works on a file path: it loads the contents of the
  // file at the given path, and then performs the feature extraction.
  // Simple improvement to make this an API that other classes can
  // consume within our application, without requiring the trip to
  // disk with the html (i.e., the extraction would be done entirely in
  // memory).
  public static void main(String[] args) {
    if (args.length == 0) {
      System.err.println("Error: must pass source file path as command line argument.");
    }

    Map<Character, Integer> features = new HashMap<Character, Integer>();
    FileReader f = null;
    try {
      f = new FileReader(args[0]);
      long count = 0;
      for (int c = f.read(); c != -1; c = f.read()) {
        // Ignore characters outside of our set.
        if (c < 32 || c > 126)
          continue;

        Character character = Character.valueOf((char) c);
        if (!features.containsKey(character)) {
          features.put(character, new Integer(0));
        }
        Integer incremented = new Integer(features.get(character).intValue() + 1);
        features.put(character, incremented);
        count++;
      }
      f.close();

      System.out.println("char\tcount\tfraction");
      for (int i = 32; i < 127; i++) {
        Character c = Character.valueOf((char) i);
        Integer featureCount = features.get(c) == null ? 0 : features.get(c);
        double featureFraction = ((double) featureCount) / count;
        System.out.println(String.format("'%s'\t%d\t%.4f", c, featureCount, featureFraction));
      }
      System.out.println(String.format("%d total characters.", count));

    } catch (FileNotFoundException e) {
      System.err.println(String.format("Error: %s", e.getMessage()));
    } catch (IOException e) {
      System.err.println(String.format("Error: %s", e.getMessage()));
    }
  }

}
