package edu.auburn;

import java.io.IOException;

import edu.auburn.extraction.FeatureMapFileWriter;
import edu.auburn.search.ChildUrlCache;
import edu.auburn.search.HtmlWebNode;
import edu.auburn.search.HtmlWebNodeVisitor;
import edu.auburn.search.IterativeDeepeningDFS;
import edu.auburn.search.Node;
import edu.auburn.search.NodeVisitException;
import edu.auburn.search.StaticPredicate;

public class Searcher {

  private static String Usage = "\nUsage is \"Searcher <start url> <max depth>\".\n\n"
      + "E.g., \"Searcher http://www.auburn.edu 4\"\n";

  public static void main(String[] args) throws IOException, NodeVisitException {
    if (args.length < 2 || args.length > 2) {
      System.out.println(Usage);
      return;
    }

    ChildUrlCache childUrlCache = new ChildUrlCache();
    FeatureMapFileWriter featureWriter = new FeatureMapFileWriter("htmlExtraction.store");
    HtmlWebNodeVisitor nodeVisitor = new HtmlWebNodeVisitor(featureWriter, childUrlCache);
    
    try {
    
      IterativeDeepeningDFS.<String>search(new HtmlWebNode(args[0], childUrlCache),
        nodeVisitor, new StaticPredicate<Node<String>>(false), Integer.parseInt(args[1]));
    } finally {
      
      featureWriter.close();
      System.out.println(
          String.format("\nVisited '%d' total nodes, '%d' unique nodes. '%d' errors when visiting nodes.", 
                        nodeVisitor.getTotalNodesVisited(), nodeVisitor.getUniqueNodesVisited(), nodeVisitor.getTotalErrorsDuringVisits()));
    }
  }

}
