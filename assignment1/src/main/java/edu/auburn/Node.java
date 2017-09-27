package edu.auburn;

import java.util.Collection;

public interface Node<T> {
  T getData() throws NodeVisitException;

  Collection<Node<T>> getChildren() throws NodeVisitException;
}
