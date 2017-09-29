package edu.auburn.search;

public interface Visitor<T> {
  void visit(T item) throws NodeVisitException;
}
