package edu.auburn.search;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ChildUrlCache {
  
  private Map<String, Collection<Node<String>>> urlToChildUrlsMap = new HashMap<String, Collection<Node<String>>>();
  
  public boolean hasUrlInfo(String url) {
    return urlToChildUrlsMap.containsKey(url);
  }
  
  public Set<String> getAllUrls() {
    return urlToChildUrlsMap.keySet();
  }
  
  public Collection<Node<String>> getChildUrls(String url) {
    return urlToChildUrlsMap.get(url);
  }

  public void addChildUrls(String url, Collection<Node<String>> values) {
    if(!urlToChildUrlsMap.containsKey(url)) {
      urlToChildUrlsMap.put(url, values);
    } else {
      urlToChildUrlsMap.get(url).addAll(values);
    }
  }
}
