package edu.auburn.search;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.SSLHandshakeException;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.UnsupportedMimeTypeException;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import edu.auburn.extraction.HtmlExtractionStore;
import edu.auburn.extraction.UnigramExtractor;

public class HtmlWebNode implements Node<Document> {

  private static final Document DeadDocument = new Document("DeadDocument");

  private String url;
  private Document document = null;
  private HtmlExtractionStore store;

  public HtmlWebNode(String url, HtmlExtractionStore store) {
    this.url = url;
    this.store = store;
  }

  public Document getDocument() throws IOException {
    try {
      if (document == null && !store.hasExtractionInfo(url)) {
        document = Jsoup.connect(url).get();
        
        // Perform the extraction and store the results since 
        // we just loaded document for the first time.
        Reader reader = new StringReader(document.outerHtml());
        store.addExtractionInfo(url, UnigramExtractor.extractFeatures(reader));
      }
    } catch (UnsupportedMimeTypeException e) {
      document = DeadDocument;
    } catch (SSLHandshakeException e) {
      document = DeadDocument;
    } catch (MalformedURLException e) {
      document = DeadDocument;
    } catch (HttpStatusException e) {
      document = DeadDocument;
    } catch (UnknownHostException e) {
      document = DeadDocument;
    }
    return document;
  }

  @Override
  public Document getData() throws NodeVisitException {
    try {
      return getDocument();
    } catch (IOException e) {
      throw new NodeVisitException(String.format("Error retrieving HTML document from '%s'.", url),
          e);
    }
  }

  @Override
  public Collection<Node<Document>> getChildren() throws NodeVisitException {

    Map<Integer, Node<Document>> children = new HashMap<Integer, Node<Document>>();
    Elements links = getData().select("a[href]");

    for (Element link : links) {

      String linkUrl = link.attr("abs:href");

      // If the store already has info for this url, we will "prune" it from this
      // search tree so that we don't repeat same work.
      if (linkUrl != null && linkUrl.length() != 0 && 
          !store.hasExtractionInfo(linkUrl) && !children.containsKey(linkUrl)) {
        children.put(linkUrl.hashCode(), new HtmlWebNode(linkUrl, store));
      }
    }
    
    return children.values();
  }

  @Override
  public String toString() {
    return url;
  }

}
