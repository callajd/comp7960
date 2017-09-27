package edu.auburn;

import java.util.ArrayList;
import java.util.Collection;

import org.jsoup.nodes.Document;

public class Searcher {

  private static String Usage = "\nUsage is \"Searcher <start url> <max depth>\".\n\n"
      + "E.g., \"Searcher http://www.auburn.edu 4\"\n\n"
      + "To try the sample, use \"Searcher sample <goal> <max depth>\", where goal\n"
      + "and depth are integer values. This will search the below tree to max depth\n"
      + "for a node with value goal.\n\n" + "\t depth = 0\t        1\n"
      + "\t depth = 1\t   2         3\n" + "\t depth = 2\t4     5   6     7\n\n"
      + "So \"Searcher sample 5 1\" will return false, while \"Searcher sample 5 2\"\n"
      + "will return true.\n";

  public static void main(String[] args) throws NodeVisitException {
    if (args.length < 2 || args.length > 3) {
      System.out.println(Usage);
      return;
    }

    if (args.length == 3 && "sample".compareTo(args[0]) == 0) {
      try {
        runSample(Integer.parseInt(args[1]), Integer.parseInt(args[2]));
        return;
      } catch (NumberFormatException e) {
        System.out.println("\nBoth arguments to the sample must be integers.");
        System.out.println(Usage);
        return;
      }
    }

    Document result = IterativeDeepeningDFS.<Document>search(new HtmlWebNode(args[0]), (x) -> false,
        Integer.parseInt(args[1]), true);

    System.out.println(String.format("\nSearch result: %s",
        result != null ? result.location() : "no goal found."));
  }

  public static void runSample(Integer goal, Integer maxDepth) throws NodeVisitException {

    IntegerNode root = new IntegerNode(1);
    root.getChildren().add(new IntegerNode(2));
    root.getChildren().add(new IntegerNode(3));

    for (Node<Integer> child : root.getChildren()) {
      child.getChildren().add(new IntegerNode(2 * child.getData()));
      child.getChildren().add(new IntegerNode(1 + 2 * child.getData()));
    }

    IterativeDeepeningDFS.<Integer>search(root, (x) -> goal.compareTo(x) == 0, maxDepth, true);
  }

  public static class IntegerNode implements Node<Integer> {

    private Integer data;
    private Collection<Node<Integer>> children = new ArrayList<Node<Integer>>();

    public IntegerNode(int data) {
      this.data = data;
    }

    public Integer getData() {
      return data;
    }

    public Collection<Node<Integer>> getChildren() {
      return children;
    }

    @Override
    public String toString() {
      return String.format("[%d, %d children]", data, children.size());
    }

  }

}
