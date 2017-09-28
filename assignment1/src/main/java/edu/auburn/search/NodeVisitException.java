package edu.auburn.search;

public class NodeVisitException extends Exception {

  public NodeVisitException() {
  }

  public NodeVisitException(String message) {
    super(message);
  }

  public NodeVisitException(Throwable cause) {
    super(cause);
  }

  public NodeVisitException(String message, Throwable cause) {
    super(message, cause);
  }

  public NodeVisitException(String message, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }

}
