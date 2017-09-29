package edu.auburn.search;

import java.util.Collection;

public class HtmlWebNode implements Node<String> {

  private String url;
  private ChildUrlCache childUrlCache;

  public HtmlWebNode(String url, ChildUrlCache childUrlCache) {
    this.url = url;
    this.childUrlCache = childUrlCache;
  }

  @Override
  public String getData() throws NodeVisitException {
    return url;
  }

  @Override
  public Collection<Node<String>> getChildren() throws NodeVisitException {
    return childUrlCache.getChildUrls(url);
  }

  @Override
  public String toString() {
    return url;
  }

}
