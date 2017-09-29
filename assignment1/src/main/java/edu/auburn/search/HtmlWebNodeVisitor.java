package edu.auburn.search;

import java.io.Reader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import edu.auburn.extraction.FeatureMapFileWriter;
import edu.auburn.extraction.UnigramExtractor;

public class HtmlWebNodeVisitor implements Visitor<Node<String>> {

  private int totalNodesVisited = 0;
  private int uniqueNodesVisited = 0;
  private int totalErrorsDuringVisits = 0;

  private FeatureMapFileWriter featureWriter;
  private ChildUrlCache childUrlCache;

  public HtmlWebNodeVisitor(FeatureMapFileWriter featureWriter, ChildUrlCache childUrlCache) {
    this.featureWriter = featureWriter;
    this.childUrlCache = childUrlCache;
  }

  @Override
  public void visit(Node<String> item) throws NodeVisitException {

    totalNodesVisited++;
    String url = item.getData();
    Map<String, Node<String>> childUrls = new HashMap<String, Node<String>>();
    
    System.out.print(String.format("%d '%s': ", totalNodesVisited, url));
    
    if (childUrlCache.hasUrlInfo(url)) {
      System.out.println("previously seen.");
      return;
    }

    uniqueNodesVisited++;

    try {

      Document document = Jsoup.connect(url).get();

      // Perform the feature extraction, write data to file.
      Reader reader = new StringReader(document.outerHtml());
      featureWriter.write(url, UnigramExtractor.extractFeatures(reader));

      // Walk the HTML looking for linked-to pages.
      Elements links = document.select("a[href]");
      for (Element link : links) {

        String linkUrl = link.attr("abs:href");

        if (linkUrl != null && linkUrl.length() != 0 && !childUrls.containsKey(linkUrl)) {
          childUrls.put(linkUrl, new HtmlWebNode(linkUrl, childUrlCache));
        }
      }

      System.out
          .println(String.format("found '%d' children.", childUrls.size()));

    } catch (Exception e) {
      System.out.println(String.format("error visiting node: '%s'", e.getMessage()));
      e.printStackTrace();
      totalErrorsDuringVisits++;
    }

    childUrlCache.addChildUrls(url, childUrls.values());
  }

  public int getTotalNodesVisited() {
    return totalNodesVisited;
  }

  public int getUniqueNodesVisited() {
    return uniqueNodesVisited;
  }

  public int getTotalErrorsDuringVisits() {
    return totalErrorsDuringVisits;
  }
}
