package edu.auburn;

import java.util.Stack;
import java.util.function.Predicate;

public class IterativeDeepeningDFS {

  public static <T> T search(Node<T> root, Predicate<T> isGoal, int maxDepth, boolean verbose)
      throws NodeVisitException {

    for (int depth = 0; depth <= maxDepth; depth++) {
      Stack<SearchNode<T>> path = new Stack<SearchNode<T>>();
      path.push(new SearchNode<T>(root, 0));

      while (!path.isEmpty()) {
        SearchNode<T> current = path.pop();

        if (verbose)
          System.out.print(
              String.format("\nEvaluating node '%s' at depth '%s'", current.node, current.depth));

        if (isGoal.test(current.node.getData())) {
          System.out.println("...goal.");
          return current.node.getData();
        }

        // need to descend further
        if (current.depth < depth) {
          for (Node<T> child : current.node.getChildren()) {
            path.push(new SearchNode<T>(child, current.depth + 1));
          }
        }
      }
    }

    if (verbose)
      System.out.println(String.format("\nCouldn't find goal within depth '%s'.", maxDepth));

    return null; // couldn't find a match
  }

  public static <T> T search(Node<T> root, Predicate<T> isGoal, int maxDepth)
      throws NodeVisitException {
    return search(root, isGoal, maxDepth, false);
  }

  private static class SearchNode<V> {
    public Node<V> node;
    public int depth;

    public SearchNode(Node<V> node, int depth) {
      this.node = node;
      this.depth = depth;
    }
  }

}


