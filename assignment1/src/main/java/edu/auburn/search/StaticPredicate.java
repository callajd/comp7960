package edu.auburn.search;

import java.util.function.Predicate;

public class StaticPredicate<T> implements Predicate<T> {

  private boolean valueToReturn;
  
  public StaticPredicate(boolean valueToReturn) {
    this.valueToReturn = valueToReturn;
  }

  @Override
  public boolean test(T t) {
    return valueToReturn;
  }

}
