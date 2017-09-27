package edu.auburn;

import java.io.IOException;
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

public class HtmlWebNode implements Node<Document> {

  private static final Document DeadDocument = new Document("DeadDocument");

  private String url;
  private Document document = null;

  public HtmlWebNode(String url) {
    this.url = url;
  }

  public Document getDocument() throws IOException {
    try {
      if (document == null) {
        document = Jsoup.connect(url).get();

        // This is where we should perform the feature extraction,
        // since it is only executed once for each valid link.
        // However, we should verify (by checking a URL cache?) that
        // we haven't already performed the extraction for that URL
        // so that we don't repeat extractions for same URL multiple
        // times.
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

      if (linkUrl != null && linkUrl.length() != 0) {
        // We are hashing to avoid string comparisons unless there are collisions
        if (!children.containsKey(linkUrl.hashCode())
            || linkUrl.compareTo(children.get(linkUrl.hashCode()).getData().location()) != 0) {
          children.put(linkUrl.hashCode(), new HtmlWebNode(linkUrl));
        }
      }
    }
    return children.values();
  }

  @Override
  public String toString() {
    return url;
  }

}
