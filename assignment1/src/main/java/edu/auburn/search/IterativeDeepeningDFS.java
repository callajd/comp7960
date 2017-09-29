package edu.auburn.search;

import java.util.Stack;
import java.util.function.Predicate;

public class IterativeDeepeningDFS {

  public static <T> T search(Node<T> root, Visitor<Node<T>> visitor, Predicate<Node<T>> isGoal, int maxDepth)
      throws NodeVisitException {

    for (int depth = 0; depth <= maxDepth; depth++) {
      Stack<SearchNode<T>> path = new Stack<SearchNode<T>>();
      path.push(new SearchNode<T>(root, 0));

      while (!path.isEmpty()) {
        SearchNode<T> current = path.pop();
        
        visitor.visit(current.node);

        if (isGoal.test(current.node)) {
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

    return null; // couldn't find a match
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


